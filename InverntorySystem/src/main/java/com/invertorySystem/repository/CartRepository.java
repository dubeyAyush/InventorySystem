package com.invertorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invertorySystem.bo.Cart;
import com.invertorySystem.bo.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	public Cart findById(long id);
	
	public Cart findByUser(User user);
	
	

}
