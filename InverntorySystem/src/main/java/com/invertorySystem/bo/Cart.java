package com.invertorySystem.bo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="cart")
@JsonInclude(Include.NON_NULL)
public class Cart {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "cart",fetch = FetchType.EAGER)
	private Set<ItemInCart> itemsInCart;
	
	@Column(name="total_amount")
	private double totalAmt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ItemInCart> getItemsInCart() {
		return itemsInCart;
	}

	public void setItemsInCart(Set<ItemInCart> itemsInCart) {
		this.itemsInCart = itemsInCart;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}
	
	
}
