package com.am.innovations.hibernate.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "balance")
public class Balance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "balance_id")
	private Long balanceID;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CURRENCY currency;

	@NotNull
	private BigDecimal balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;

}
