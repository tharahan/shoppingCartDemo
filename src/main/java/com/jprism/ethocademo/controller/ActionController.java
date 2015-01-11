package com.jprism.ethocademo.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jprism.ethocademo.dao.ShoppingDAOImpl;
import com.jprism.ethocademo.model.Product;
import com.jprism.ethocademo.model.Purchase;


@Controller
public class ActionController  {
	
	private static Logger logger = LoggerFactory.getLogger(ActionController.class);
	
	@Autowired
	private ShoppingDAOImpl shoppingDAO; 
	
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/shopping/list.action", produces = "application/json", method= RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getProducts(HttpSession session) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			MDC.put("userId","guest" );  // just a placeholder, will be used to log user id
			
			logger.info("Listing all Products");
			
			List<Product> products = shoppingDAO.findAllProducts("guest");
			
			response.put("products", products);
			response.put("total", products.size());
		}
		finally {
			MDC.remove("userId");
			return response;
		}
	}
	
	
	@RequestMapping(value = "/shopping/ajaxsave.action" , method = RequestMethod.POST, headers = {"Content-type=application/json"}, consumes="application/json")
	public @ResponseBody Map<String, Object> ajaxsave(@RequestBody List<Purchase> jsonString,HttpSession session) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		logger.info(jsonString.toString());
		
		for (Purchase transaction : jsonString) {
			logger.info(transaction.toString());
		}
		
	   
		return response;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	@RequestMapping(value = "/shopping/confirm.action", produces = "application/json", method= RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> confirm(HttpSession session) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			
			MDC.put("userId","guest" );  // just a placeholder, will be used to log user id
			
			logger.info("Persiste and generate confirmation code");
			List<Purchase> purchases =  (List<Purchase>) session.getAttribute("purchases");
			
			String refCode = shoppingDAO.persist(purchases, "guest");
		
			response.put("refCode", refCode);
			response.put("success", true);
		}
		finally {
			MDC.remove("userId");
			return response;
		}
	}
	
	
	


	@SuppressWarnings({ "unchecked", "finally" })
	@RequestMapping(value = "/shopping/save.action" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> save(@RequestParam(value="data", required=true) String jsonData,HttpSession session) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		logger.info(jsonData);
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		
		try
        {
			MDC.put("userId","guest" );  
			if ( jsonData.startsWith("[") && jsonData.endsWith("]") )
            {
                // array of transactions
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Purchase>> ref = new TypeReference<List<Purchase>>(){};
                purchases = (List<Purchase>) mapper.readValue(jsonData, ref);
               
            }
            else
            {
                // Single transaction
                ObjectMapper mapper = new ObjectMapper();            
                purchases.add( (Purchase) mapper.readValue(jsonData, Purchase.class) );
                
            }
            
            double grandTotal = 0.0;
            DecimalFormat df = new DecimalFormat("0.00");  
            for (Purchase transaction : purchases) {
            	 
            	 double value= transaction.getQuantity() * transaction.getPrice();
            	     
            	
            	transaction.setTotal(Double.parseDouble(df.format(value)) );  
            	grandTotal += transaction.getTotal();
				logger.info("Purchase : " + transaction);
			}
            
            // TODO : Persist the purchases with complete status = false if we need remember shopping cart beyond the session
            session.setAttribute("purchases", purchases);
            
            response.put("purchases", purchases);
			response.put("total", purchases.size());
			response.put("cost", df.format(grandTotal) );
			response.put("success", true);
        }
        catch (Exception ex)
        {
        	response.put("success", false);
            throw new RuntimeException("Invalid JSON");
        }
		finally {
			MDC.remove("userId");
			return response;
		}
		
		
		
		
		
	   
		
	}
	
	
	

}
