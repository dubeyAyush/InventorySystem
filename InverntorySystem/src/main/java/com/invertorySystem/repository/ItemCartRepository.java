package com.invertorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invertorySystem.bo.Cart;
import com.invertorySystem.bo.ItemInCart;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemInCart, Long> {

	public ItemInCart findByCart(Cart cart);
}
