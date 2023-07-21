package com.example.Barebones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Barebones.service.UserServiceImpl;



@SpringBootTest
class BarebonesApplicationTests {
	
	private UserServiceImpl userSvc;
	
	@Autowired
	public BarebonesApplicationTests(
			UserServiceImpl userSvc
			) {
		this.userSvc = userSvc;
	}

	@Test
	void contextLoads() {
		assertNotNull(userSvc);
	}
	
	@Test
	void testUserServiceCurrentUser() {
		UserDetails ud = userSvc.loadUserByUsername("wroales@gmail.com");
		assertNotNull(ud);
		assertEquals("wroales@gmail.com", ud.getUsername());
	}

}
