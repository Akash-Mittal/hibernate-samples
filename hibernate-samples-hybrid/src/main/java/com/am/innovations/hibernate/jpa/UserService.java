package com.am.innovations.hibernate.jpa;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private BalanceRepository balanceRepository;

	@Autowired
	private UserRepository userRepository;

	public boolean deposit(final Long userID, final CURRENCY currency, final BigDecimal amount) {
		final AtomicBoolean result = new AtomicBoolean();
		Optional<User> optionalUser = userRepository.findById(userID);
		optionalUser.ifPresentOrElse(user -> {
			user.getBalance().stream().filter(balance -> balance.getCurrency().name().equals(currency.name()))
					.findFirst().ifPresentOrElse(b -> b.getBalance().add(amount),
							() -> user.getBalance().add(new Balance(currency, amount)));
			userRepository.save(user);
			result.set(true);
		}, () -> result.set(false));

		return result.get();
	}

	public String getBalance(final Long userID) {
		String response = "";
		userRepository.findById(userID).ifPresentOrElse(user -> response.concat(user.toString()),
				() -> response.concat("Not Found for: " + userID));
		return response;
	}

}
