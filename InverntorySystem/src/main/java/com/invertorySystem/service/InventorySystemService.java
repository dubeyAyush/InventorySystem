package com.invertorySystem.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.invertorySystem.bo.Cart;
import com.invertorySystem.bo.Category;
import com.invertorySystem.bo.Item;
import com.invertorySystem.bo.User;
import com.invertorySystem.dto.CategoryAdditionDTO;
import com.invertorySystem.dto.ItemAdditionDto;
import com.invertorySystem.dto.UserLoginDTO;
import com.invertorySystem.exception.ISException;

public interface InventorySystemService {

	/**
	 * This method will return the list of all categories 
	 * @return list of all categories
	 */
	public List<Category> getAllCategories();
	
	/**
	 * Adds new category
	 * @param categoryDto
	 * @return added category
	 */
	public Category addCategory(CategoryAdditionDTO categoryDto);
	
	/**
	 * TO fetch all items related to a category
	 * @param categoryId
	 * @return set of items related to category
	 */
	public Set<Item> getCategoryItems(long categoryId);
	
	/**
	 * adding new user 
	 * @param userDto
	 * @return The persisted user
	 */
	public User addUser(UserLoginDTO userDto) throws ISException;
	
	/**
	 * Fetches the Cart of current user of no cart is assigned to user then empty cart is created
	 * @return Cart of user
	 */
	public Cart getUserCart();
	
	
	/**
	 * Adds to Item to cart
	 * @param itemId
	 * @return Updated Cart
	 */
	public Cart addItemToCart(long itemId);
	
	/**
	 * Check outs the user cart 
	 * @return Total amount in User cart
	 */
	public double checkoutCart() throws ISException;
	
	/**
	 * Adds item to repository
	 * @param itemDto
	 * @return Item added to repository
	 */
	public Item addItem(ItemAdditionDto itemDto);
	
	/**
	 * Utility method to fetch User name from current request
	 * @return UserName
	 */
	public static String getCurrentUserName() {
		AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext()
				.getAuthentication();
		String username = (String) auth.getName();
		return username;
	}
}
