package com.am.innovations.hibernate.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.am.innovations.hibernate.entities.Balance;
import com.am.innovations.hibernate.entities.User;
import com.am.innovations.hibernate.enums.CURRENCY;
import com.am.innovations.hibernate.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testDeposit() throws Exception {
		User user1 = new User(1l, "Adam");
		Balance balance = new Balance(CURRENCY.EUR, BigDecimal.TEN);
		user1.getBalance().put(CURRENCY.EUR, balance);
		userRepository.save(user1);
		Assert.assertTrue(userService.deposit(user1.getUserID(), balance.getCurrency(), balance.getBalance()));
	}

}
