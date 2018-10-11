package com.am.innovations.hibernate.jpa;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application implements CommandLineRunner {

	@Autowired
	private WalletRepository walletRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		WalletPK walletPK = new WalletPK(123L, CURRENCY.EUR);
		Wallet wallet = new Wallet(walletPK, BigDecimal.TEN);
		walletRepository.save(wallet);

		System.out.println(
				walletRepository.findById(new WalletPK.Builder().currency(CURRENCY.EUR).userID(123L).build()).get());
	}

}
