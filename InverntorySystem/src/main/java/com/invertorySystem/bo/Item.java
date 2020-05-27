package com.invertorySystem.bo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="item")
@JsonInclude(Include.NON_NULL)
public class Item {

	@Id
	@GeneratedValue
	private long itemId;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="categoryId",nullable = false)
	private Category category;
	
	@Column(name="name")
	private String name;
	
	@Column(name="quantity_available")
	private int quantityAvailable;
	
	@Column(name="selling_price")
	private double sellingPrice;
	
	
	@Column(name="cost_price")
	private double costPrice;

	public long getItemId() {
		
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	
	
}
