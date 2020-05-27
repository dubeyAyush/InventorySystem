package com.invertorySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invertorySystem.bo.Item;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long>{

	public Item findById(long itemId);
}
