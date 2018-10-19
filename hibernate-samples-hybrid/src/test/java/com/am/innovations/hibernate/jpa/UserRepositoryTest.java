package com.am.innovations.hibernate.jpa;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
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
        User user1 = new User("Adam");
        User user2 = new User("Mike");
        User user3 = new User("John");

        Balance balance = new Balance(CURRENCY.EUR, BigDecimal.TEN);
        user1.getBalance().add(balance);
        balance.setUser(user1);

        assertNotNull(userRepository.save(user1));
        assertNotNull(balanceRepository.save(balance));

        assertNotNull(userRepository.save(user2));
        assertNotNull(userRepository.save(user3));

        List<User> userList = userRepository.findAll();
        assertNotNull(userList);
        Assert.assertThat(userList.size(), is(3));
        assertNotNull(userList.get(0).getBalance());
        assertNotNull(userList.get(0).getBalance().get(0));

        Assert.assertThat(userList.get(0).getBalance().get(0).getCurrency(), is(CURRENCY.EUR));
        Assert.assertTrue(userList.get(0).getBalance().get(0).getBalance().compareTo(BigDecimal.TEN) == 0);

    }
}
