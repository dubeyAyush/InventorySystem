package com.invertorySystem.bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="item_cart")
@JsonInclude(Include.NON_NULL)
public class ItemInCart {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	@JoinColumn(name="item_id")
	private Item item;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne(cascade =CascadeType.DETACH)
	@JoinColumn(name = "cart_id",nullable = false)
	private Cart cart;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
}
