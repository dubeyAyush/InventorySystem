package com.invertorySystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invertorySystem.bo.User;
import com.invertorySystem.dto.UserLoginDTO;
import com.invertorySystem.exception.ISException;
import com.invertorySystem.service.InventorySystemService;

@RestController
@RequestMapping(value="/api/v1/user")
public class UserController {
	
	@Autowired
	InventorySystemService inventorySystemService;
	
	@PostMapping("/signUp")
	public User signUp(@RequestBody UserLoginDTO userDto ) throws ISException {
	
		User user =inventorySystemService.addUser(userDto);
		return user;
	}

}
