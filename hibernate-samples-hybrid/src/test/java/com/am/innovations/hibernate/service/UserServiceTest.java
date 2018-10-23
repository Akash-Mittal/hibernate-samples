package com.am.innovations.hibernate.service;

import java.math.BigDecimal;

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
import com.am.innovations.hibernate.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testDeposit() throws Exception {
		User user1 = new User(1l, "Adam");
		User user2 = new User(2l, "Mike");
		User user3 = new User(3l, "John");

		Balance balance = new Balance(CURRENCY.EUR, BigDecimal.TEN);
		user1.getBalance().put(CURRENCY.EUR, balance);
		userRepository.save(user1);
		userService.deposit(user1.getUserID(), balance.getCurrency(), balance.getBalance());

	}

}
