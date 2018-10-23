package com.am.innovations.hibernate;

import java.math.BigDecimal;

import com.am.innovations.hibernate.entities.Balance;
import com.am.innovations.hibernate.entities.User;
import com.am.innovations.hibernate.enums.CURRENCY;

public interface BaseTest {
	User user1 = new User(1l, "Adam");
	User user2 = new User(2l, "Mike");
	User user3 = new User(3l, "John");

	Balance balance = new Balance(CURRENCY.EUR, BigDecimal.TEN);

}
