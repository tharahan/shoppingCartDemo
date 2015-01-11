package com.jprism.ethocademo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jprism.ethocademo.model.Purchase;
import com.jprism.ethocademo.TestUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:demo-spring-context-servlet.xml")
@WebAppConfiguration
public class ActionControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired WebApplicationContext wac; 
	@Autowired MockHttpSession session;
	@Autowired MockHttpServletRequest request;

	
	public static final MediaType APPLICATION_JSON = new MediaType(MediaType.APPLICATION_JSON.getType(),
																		MediaType.APPLICATION_JSON.getSubtype() );
	public static final MediaType APPLICATION_JSON_UTTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
																		MediaType.APPLICATION_JSON.getSubtype(),
																		Charset.forName("utf8"));

	@Before
	public void setUp() throws Exception {		 
		 
		 MockitoAnnotations.initMocks(this);		
		 this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();		 
	}
	
	
	@Test
	public void checkGetProducts() throws Exception {			
		
		this.mockMvc.perform(get("/shopping/list.action")
		        .accept(MediaType.ALL))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(APPLICATION_JSON))
		        .andExpect(jsonPath("$.total", Matchers.is(9)))
		        .andExpect(jsonPath("$.products", Matchers.hasSize(9)));
		      

	}
	
	@Test
	public void checkSaveProducts() throws Exception {			
		
		Purchase p1 = new Purchase("PRG0001","Advanced Java Programming",39.95,5);
		Purchase p2 = new Purchase("WEB0001","AngularJS Web Application Development",32.95,10);
		List<Purchase> purchases = Arrays.asList(p1,p2);
		
		String postData = TestUtil.asJsonString(purchases);
		System.out.println(postData);
		      
		this.mockMvc.perform(post("/shopping/save.action").session(session)
				.param("data", postData)
		        .accept(MediaType.ALL))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(APPLICATION_JSON_UTTF8))
		        .andExpect(jsonPath("$.success", Matchers.is(true)));
		
		List<Purchase> retItems =  (List<Purchase>) session.getAttribute("purchases");
		
		assertEquals(true, retItems.size() > 0);
		
	}
	
	

}
