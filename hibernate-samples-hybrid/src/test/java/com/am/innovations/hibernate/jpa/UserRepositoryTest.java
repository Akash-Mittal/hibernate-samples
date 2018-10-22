package com.am.innovations.hibernate.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BalanceRepository balanceRepository;

	@Before
	public void setUp() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void testSaveAndFind() throws Exception {
		User user1 = new User(1l, "Adam");
		User user2 = new User(2l, "Mike");
		User user3 = new User(3l, "John");

		Balance balance = new Balance(CURRENCY.EUR, BigDecimal.TEN);
		user1.getBalance().add(balance);
		balance.setUser(user1);

		assertNotNull(userRepository.save(user1));
		assertNotNull(userRepository.save(user2));
		assertNotNull(userRepository.save(user3));

		List<User> userList = userRepository.findAll();
		assertNotNull(userList);
		assertThat(userList.size(), is(3));
		assertNotNull(userList.get(0).getBalance());
		assertNotNull(userList.get(0).getBalance().get(0));

		assertThat(userList.get(0).getBalance().get(0).getCurrency(), is(CURRENCY.EUR));
		assertTrue(userList.get(0).getBalance().get(0).getBalance().compareTo(BigDecimal.TEN) == 0);

	}
}
