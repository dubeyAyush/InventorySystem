package com.invertorySystem.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invertorySystem.bo.Cart;
import com.invertorySystem.bo.Category;
import com.invertorySystem.bo.ErrorCodes;
import com.invertorySystem.bo.Item;
import com.invertorySystem.bo.ItemInCart;
import com.invertorySystem.bo.User;
import com.invertorySystem.dto.CategoryAdditionDTO;
import com.invertorySystem.dto.ItemAdditionDto;
import com.invertorySystem.dto.UserLoginDTO;
import com.invertorySystem.exception.ISException;
import com.invertorySystem.repository.CartRepository;
import com.invertorySystem.repository.CategoryRepository;
import com.invertorySystem.repository.ItemCartRepository;
import com.invertorySystem.repository.ItemRepository;
import com.invertorySystem.repository.UserRepository;
import com.invertorySystem.service.InventorySystemService;

@Service
public class InventorySystemServiceImpl implements InventorySystemService {

	private static final Logger logger = LoggerFactory.getLogger(InventorySystemServiceImpl.class.getName());

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemCartRepository itemCartRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	EntityManager em;

	@Override
	public List<Category> getAllCategories() {

		List<Category> categories = null;
		try {

			categories = categoryRepository.findAll();
			categories.forEach(category -> category.setItems(null));

		} catch (Exception e) {
			logger.error("Exception while fetching Categories " + e, e);
		}
		return categories;
	}

	@Override
	public Set<Item> getCategoryItems(long categoryId) {

		Set<Item> items = null;
		try {
			items = categoryRepository.findById(categoryId).getItems();
			items.forEach(item -> item.getCategory().setItems(null));
		} catch (Exception e) {
			logger.error("Exception while fetching Category Items " + e, e);
		}
		return items;
	}

	@Override
	public Category addCategory(CategoryAdditionDTO categoryDto) {

		Category category = null;
		try {
			category = new Category();
			category.setCategoryCode(categoryDto.getCategoryCode());
			category.setCategoryName(categoryDto.getCategoryName());

			category = categoryRepository.save(category);
			category.setItems(null);
		} catch (Exception e) {
			logger.error("Exception while adding new Category " + e, e);
		}

		return category;
	}

	@Override
	public User addUser(UserLoginDTO userDto) throws ISException {

		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());

		try {
			user = userRepository.save(user);
			user.setCart(null);
		} catch (DataIntegrityViolationException ex) {
			logger.error("Error Occured whiele adding user to repo", ex);
			throw new ISException(ErrorCodes.USER_ALREADY_EXISTS);
		}catch(Exception ex) {
			logger.error("Error while adding user"+ex,ex);
		}
		return user;
	}

	@Override
	public Cart getUserCart() {

		Cart cart = null;
		try {
			String userName = InventorySystemService.getCurrentUserName();
			User user = userRepository.findByUserName(userName);
			cart = user.getCart();

			if (cart == null) {
				cart = new Cart();
				cart.setUser(user);
				cartRepository.save(cart);
				user.setCart(cart);
			}
			Set<ItemInCart> itemsInCart = cart.getItemsInCart();
			if(itemsInCart != null)
				itemsInCart.forEach(itemInCart -> itemInCart.setCart(null));
		
		} catch (Exception e) {
			logger.error("Exception while fetching User cart " + e, e);
		}

		return cart;
	}

	@Override
	public Cart addItemToCart(long itemId) {

		Cart cart = null;
		try {
			cart = getUserCart();
			Item item = itemRepository.findById(itemId);
			ItemInCart itemInCart = new ItemInCart();

			itemInCart.setCart(cart);
			itemInCart.setItem(item);
			itemInCart.setQuantity(1);

			itemInCart = itemCartRepository.save(itemInCart);

			cart.getItemsInCart();

			Set<ItemInCart> itemsInCart = cart.getItemsInCart();
			
			if(itemsInCart == null)
				itemsInCart = new HashSet<>();
			
			itemsInCart.add(itemInCart);
			
			/*
			 * updating the total amount in cart
			 */
			Map<Item, Integer> itemQuantityMap = itemsInCart.stream()
					.collect(Collectors.toMap(ItemInCart::getItem, ItemInCart::getQuantity));

			Double totalAmount = itemQuantityMap.entrySet().stream()
					.mapToDouble(entry -> entry.getKey().getSellingPrice() * entry.getValue())
					.reduce(0D, (sum, value) -> sum + value);

			cart.setTotalAmt(totalAmount);
			cart = cartRepository.save(cart);
			itemsInCart.forEach(itemCart -> itemCart.setCart(null));
			cart.setUser(null);

		} catch (Exception e) {
			logger.error("Exception while adding Item to Cart " + e, e);
		}

		return cart;
	}
	
	
	@Override
	@Transactional
	public double checkoutCart() throws ISException {

		Cart cart = null;
		double totalAmt = 0;
		try {
			String userName = InventorySystemService.getCurrentUserName();
			User user = userRepository.findByUserName(userName);
			cart = user.getCart();

			if (cart == null || cart.getItemsInCart() == null || cart.getItemsInCart().isEmpty() ) {
				logger.error("The user cart is null while checking out ");
				throw new ISException(ErrorCodes.EMPTY_CART);
			}

			Map<Item, Integer> itemQuantityMap = cart.getItemsInCart().stream()
					.collect(Collectors.toMap(ItemInCart::getItem, ItemInCart::getQuantity));

			/*
			 * checking for available quantity in repository
			 */
			for (Map.Entry<Item, Integer> entry : itemQuantityMap.entrySet()) {

				Item item = entry.getKey();
				int quantity = entry.getValue();
				int availableQuantity = item.getQuantityAvailable() - quantity;
				if (availableQuantity < 0) {
					logger.error("Quantity of Available item is less then required Quantity");
					throw new ISException(ErrorCodes.QUANTITY_NOT_AVAILABLE, new Object[] { item.getName() });
				}
				item.setQuantityAvailable(availableQuantity);
				itemRepository.save(item);
			}
			
			totalAmt =cart.getTotalAmt();
			
			/*
			 * clearing the cart after checkout
			 */
			cart.getItemsInCart().forEach(itemInCart -> itemCartRepository.delete(itemInCart));
			cart.setItemsInCart(null);
			cart.setTotalAmt(0);
			cartRepository.save(cart);
		
		}catch(ISException ex) {
			
			throw new ISException(ex.getErrorCodes(),ex.getArgs());
		
		}catch (Exception e) {
			logger.error("Exception while Checking out cart " + e, e);
		}

		return totalAmt;
	}

	@Override
	public Item addItem(ItemAdditionDto itemDto) {

		Item item = null;
		try {
			Category category = categoryRepository.findById(itemDto.getCategoryId());

			item = new Item();
			item.setName(itemDto.getName());
			item.setQuantityAvailable(itemDto.getQuantity());
			item.setCostPrice(itemDto.getCostPrice());
			item.setSellingPrice(itemDto.getSellingPrice());
			item.setCategory(category);

			item = itemRepository.save(item);
			item.setCategory(item.getCategory());
			item.getCategory().setItems(null);
			
		} catch (Exception e) {
			logger.error("Exception while adding new Item " + e, e);
		}

		return item;
	}

}
