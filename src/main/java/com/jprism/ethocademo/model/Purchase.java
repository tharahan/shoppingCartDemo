package com.jprism.ethocademo.model;

import java.io.Serializable;

public class Purchase extends Product implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private Integer quantity;
	
	private Double total;
	
	public Purchase() {
		
	}

	public Purchase(String code, String name, Double price, Integer quantity) {
		super(code, name, price);
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Purchase [quantity=" + quantity + ", getCode()=" + getCode()
				+ ", getName()=" + getName() + ", getPrice()=" + getPrice() + "]";
	}

	
	
	
	

}
