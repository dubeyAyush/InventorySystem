package com.invertorySystem.dto;

import java.io.Serializable;

public class ItemAdditionDto implements Serializable {

	/**
	 * Ecllipse generated serial version ID
	 */
	private static final long serialVersionUID = 5536885329281824509L;

	private String name;
	
	private long categoryId;
	
	private double costPrice;
	
	private double sellingPrice;
	
	private int quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
