package com.am.innovations.hibernate.jpa;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private UserRepository userRepository;

	public boolean deposit(final Long userID, final CURRENCY currency, final BigDecimal amount) {
		final AtomicBoolean result = new AtomicBoolean(true);
		balanceRepository.findByUserAndCurrency(userID, currency).ifPresentOrElse(balance -> {
			logger.info("Balance Found For User: {} and Currency: {}", userID, currency.name());
			balance.setBalance(balance.getBalance().add(amount));
			balanceRepository.save(balance);
		}, () -> {
			logger.info("Balance Not Found For User: {} and Currency: {}", userID, currency.name());
			Optional<User> optionalUser = userRepository.findById(userID);
			optionalUser.ifPresentOrElse(user -> {
				Balance balance = new Balance(currency, amount);
				balance.setUser(user);
				user.getBalance().put(currency, balance);
				userRepository.save(user);
			}, () -> result.set(false));
		});

		return result.get();
	}

	public String getBalance(final Long userID) {
		StringBuilder response = new StringBuilder();
		userRepository.findById(userID).ifPresentOrElse(user -> {
			logger.info("Found User:" + user);
			response.append(user.toString());
		}, () -> {
			logger.info("User Not Found: " + userID);
			response.append("Not Found for: " + userID);
		});
		return response.toString();
	}

}
