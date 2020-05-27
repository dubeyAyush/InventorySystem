package com.invertorySystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invertorySystem.bo.Cart;
import com.invertorySystem.bo.Category;
import com.invertorySystem.bo.Item;
import com.invertorySystem.dto.CategoryAdditionDTO;
import com.invertorySystem.dto.ItemAdditionDto;
import com.invertorySystem.exception.ISException;
import com.invertorySystem.service.InventorySystemService;

@RestController
@RequestMapping(value = "/api/v1/shop")
public class InventorySystemController {

	@Autowired
	private InventorySystemService inventorySystemService;
	
	@GetMapping("/listCategories")
	public Map<String,Object> getAllCategories(){
		
		Map<String,Object> responseMap = new HashMap<>();
		List<Category> categoryList = inventorySystemService.getAllCategories();
		responseMap.put("categoryList", categoryList);
		return  responseMap;
	}
	
	@PostMapping("/addCategory")
	public Map<String,Object> addCategory(@RequestBody CategoryAdditionDTO categoryDto) {
		
		Map<String,Object> responseMap = new HashMap<>();
		Category category = inventorySystemService.addCategory(categoryDto);
		responseMap.put("category", category);
		return responseMap; 
	}
	
	@GetMapping("/categoryItems/{categoryId}")
	public Map<String,Object> getCategoryItems(@PathVariable("categoryId") long categoryId){
		
		Map<String,Object> responseMap = new HashMap<>();
		Set<Item> items = inventorySystemService.getCategoryItems(categoryId);
		responseMap.put("items", items);
		return responseMap;
	}
	
	@PostMapping("/addItem")
	public Map<String,Object> addItem(@RequestBody ItemAdditionDto itemDto){
		Map<String,Object> responseMap = new HashMap<>();
		Item item = inventorySystemService.addItem(itemDto);
		responseMap.put("item", item);
		return responseMap;
	}
	
	@GetMapping("/getCart")
	public Map<String,Object> getUserCart() {
		
		Map<String,Object> responseMap = new HashMap<>();
		Cart cart = inventorySystemService.getUserCart();
		cart.setUser(null);
		responseMap.put("cart", cart);
		return  responseMap;
	}
	
	@PostMapping("/addItemToCart/{itemId}")
	public Map<String,Object> addItemInCart(@PathVariable("itemId") long itemId) {
		
		Map<String,Object> responseMap = new HashMap<>();
		Cart cart = inventorySystemService.addItemToCart(itemId);
		responseMap.put("cart",cart);
		return  responseMap;
	}
	
	@PostMapping("/checkoutCart")
	public Map<String,Object> checkoutCart() throws ISException {

		Map<String,Object> responseMap = new HashMap<>();
		double totalCartValue = inventorySystemService.checkoutCart();
		responseMap.put("totalCartValue", totalCartValue);
		return  responseMap;
	}
}
