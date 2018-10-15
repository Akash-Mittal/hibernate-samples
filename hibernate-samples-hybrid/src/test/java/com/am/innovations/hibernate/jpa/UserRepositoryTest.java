package com.am.innovations.hibernate.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WalletRepository walletRepository;

	@Before
	public void setUp() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void testSaveAndFind() throws Exception {
		User user1 = new User("Adam");
		User user2 = new User("Mike");
		User user3 = new User("John");

		assertNotNull(userRepository.save(user1));
		assertThat(userRepository.findAll().size()).isEqualTo(1);

	}
}
