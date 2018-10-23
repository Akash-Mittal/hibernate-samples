package com.am.innovations.hibernate.jpa;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.innovations.validations.Validators;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private UserRepository userRepository;

	BiFunction<Balance, BigDecimal, Void> addBalance = (balance, amount) -> {
		balance.setBalance(Validators.Expression.ForBigDecimal.ADD.apply(balance.getBalance(), amount));
		balanceRepository.save(balance);
		return null;
	};

	BiFunction<Balance, BigDecimal, Void> withdrawBalance = (balance, amount) -> {
		if (balance.getBalance().compareTo(amount) >= 0) {
			balance.setBalance(Validators.Expression.ForBigDecimal.SUBTRACT.apply(balance.getBalance(), amount));
			balanceRepository.save(balance);
		} else {
			logger.info("Insufficient Balance, Requested {} Current {}", amount, balance.getBalance());
		}
		return null;
	};

	public boolean deposit(final Long userID, final CURRENCY currency, final BigDecimal amount) {
		final AtomicBoolean result = new AtomicBoolean(true);
		balanceRepository.findByUserAndCurrency(userID, currency).ifPresentOrElse(balance -> {
			logger.info("Balance Found For User: {} and Currency: {}", userID, currency.name());
			addBalance.apply(balance, amount);
		}, () -> {
			logger.info("Balance Not Found For User: {} and Currency: {}", userID, currency.name());
			Optional<User> optionalUser = userRepository.findById(userID);
			optionalUser.ifPresentOrElse(user -> {
				createBalance(currency, amount, user);
			}, () -> result.set(false));
		});

		return result.get();
	}

	public boolean withdraw(final Long userID, final CURRENCY currency, final BigDecimal amount) {
		final AtomicBoolean result = new AtomicBoolean(true);
		balanceRepository.findByUserAndCurrency(userID, currency).ifPresentOrElse(balance -> {
			logger.info("Balance Found For User: {} and Currency: {}", userID, currency.name());
			withdrawBalance.apply(balance, amount);
		}, () -> result.set(false));

		return result.get();
	}

	private void createBalance(final CURRENCY currency, final BigDecimal amount, final User user) {
		logger.info("Created Balance For User: {} and Currency: {}", user.getUserID(), currency.name());
		Balance balance = new Balance(currency, amount);
		balance.setUser(user);
		user.getBalance().put(currency, balance);
		userRepository.save(user);
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