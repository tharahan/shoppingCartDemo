package com.jprism.ethocademo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jprism.ethocademo.model.Product;
import com.jprism.ethocademo.model.Purchase;
import com.jprism.ethocademo.util.CommonUtil;



@Repository
public class ShoppingDAOImpl implements ShoppingDAO,ApplicationContextAware {
	
	
	private JdbcTemplate jdbcTemplate;
	
	private static Logger logger = LoggerFactory.getLogger(ShoppingDAOImpl.class);
	
	@Autowired
    public void init(DataSource dbcpdataSource) {
        jdbcTemplate = new JdbcTemplate(dbcpdataSource);       
    }

	public void setApplicationContext(ApplicationContext ctx) 	throws BeansException {
		logger.debug("Context:"+ ctx.toString());		
		
	}

		
	public List<Product> findAllProducts(String userId) {
		List<Product> products = new ArrayList<Product>();
		
		String sql = "SELECT * FROM products ORDER BY name";
		
		try {
			RowMapper<Product> mapper = new RowMapper<Product>() {

				public Product mapRow(ResultSet rs, int rowNumber) 
						throws SQLException {
					Product product = new Product();
					
					product.setCode(rs.getString("code"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					
					return product;
				}
				
				
				
			};
			products = jdbcTemplate.query(sql,mapper);
		}
		catch(DataAccessException dae ) {
			logger.error(dae.getMessage());
			dae.printStackTrace();
			
		}
		
		return products;
		
		
	}

	@Transactional
	public String persist(final List<Purchase> purchases, String userId) {
		
		
		
		try {
			
			final String refCode = RandomStringUtils.randomAlphanumeric(20).toUpperCase();
			
			logger.info("Insert transaction record");
			String sql = "INSERT INTO transactions " +
					"(reference, completed, completedBy, completedDate) "
					+ "VALUES (?, ?, ?, ?)";
			
			
			jdbcTemplate.update(sql, new Object[] {
											refCode,
											1,
											userId,
											CommonUtil.convertToMySqlDate(new Date())
										
											} );
			
			logger.info("Inserting purchase records");
			sql = "INSERT INTO purchases " +
					"(code, price, quantity, reference) VALUES (?, ?, ?,?)";

			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

				
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Purchase purchase = purchases.get(i);
					ps.setString(1,purchase.getCode());
					ps.setDouble(2, purchase.getPrice());
					ps.setInt(3, purchase.getQuantity());
									
					ps.setString(4, refCode);
								
				}
				
				public int getBatchSize() {
					return purchases.size();
				}

				
			});

			
			
			return refCode;
		}
		catch(DataAccessException dae ) {
			dae.printStackTrace();
			logger.error(dae.getMessage());
			dae.printStackTrace();
			return null;
		}
	}
	
	

	

}
