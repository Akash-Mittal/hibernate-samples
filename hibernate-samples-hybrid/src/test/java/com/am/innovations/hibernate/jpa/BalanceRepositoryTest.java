package com.am.innovations.hibernate.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

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
public class BalanceRepositoryTest {
	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private UserRepository userRepository;

	private static final Long userID = 1L;
	private User user1;

	@Before
	public void setUp() throws Exception {
		userRepository.deleteAll();
		user1 = new User("Mike");
		Balance balance = new Balance(CURRENCY.EUR, BigDecimal.TEN);
		user1.getBalance().add(balance);
		balance.setUser(user1);

	}

	@Test
	public void testFindByUserAndCurrency() throws Exception {
		Optional<Balance> balance = balanceRepository.findByUserAndCurrency(userRepository.save(user1).getUserID(),
				CURRENCY.EUR);
		assertNotNull(balance.get());
		assertThat(balance.get().getCurrency(), is(CURRENCY.EUR));
		assertTrue(balance.get().getBalance().compareTo(BigDecimal.TEN) == 0);
	}

}
