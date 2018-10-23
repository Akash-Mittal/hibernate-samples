package com.am.innovations.hibernate.jpa;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/V_1.0")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/balance")
	public String get(@RequestParam(value = "userID") Long userID) throws InterruptedException, ExecutionException {
		return userService.getBalance(userID);
	}

	@PostMapping("/deposit")
	public String add(@RequestParam(value = "userID") Long userID, @RequestParam(value = "currency") CURRENCY currency,
			@RequestParam(value = "amount") BigDecimal amount) {
		if (userService.deposit(userID, currency, amount)) {
			return userService.getBalance(userID);
		}
		return null;
	}

	@PostMapping("/withdraw")
	public String withdraw(@RequestParam(value = "userID") Long userID,
			@RequestParam(value = "currency") CURRENCY currency, @RequestParam(value = "amount") BigDecimal amount) {
		if (userService.withdraw(userID, currency, amount)) {
			return userService.getBalance(userID);
		}
		return null;
	}
}