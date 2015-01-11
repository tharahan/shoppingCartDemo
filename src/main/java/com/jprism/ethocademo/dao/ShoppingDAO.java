package com.jprism.ethocademo.dao;

import java.util.List;

import com.jprism.ethocademo.model.Product;
import com.jprism.ethocademo.model.Purchase;

public interface ShoppingDAO {

	List<Product> findAllProducts(String userId);
	String persist(List<Purchase> purchases,String userId);

}
