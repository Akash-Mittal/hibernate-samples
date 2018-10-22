package com.am.innovations.hibernate.jpa;

import java.io.Serializable;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "balance")
public class Balance extends AuditModel implements Serializable {

	private static final long serialVersionUID = 6730311371449786546L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "balance_id")
	private Long balanceID;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CURRENCY currency;

	@NotNull
	@Digits(integer = 6, fraction = 2, message = "{javax.validation.constraints.Digits.message}")
	private BigDecimal balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Balance() {
	}

	public Balance(@NotNull CURRENCY currency,
			@NotNull @Digits(integer = 6, fraction = 2, message = "{javax.validation.constraints.Digits.message}") BigDecimal balance) {
		super();
		this.currency = currency;
		this.balance = balance;
	}

	public Long getBalanceID() {
		return balanceID;
	}

	public void setBalanceID(Long balanceID) {
		this.balanceID = balanceID;
	}

	public CURRENCY getCurrency() {
		return currency;
	}

	public void setCurrency(CURRENCY currency) {
		this.currency = currency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Balance [balanceID=" + balanceID + ", currency=" + currency + ", balance=" + balance + "]";
	}

}
