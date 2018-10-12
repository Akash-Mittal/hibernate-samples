package com.am.innovations.hibernate.jpa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.am.innovations.entities.base.AuditModel;

@Entity
@Table(name = "wallet")
public class Wallet extends AuditModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9189082357635718615L;

	@EmbeddedId
	private WalletPK walletPK;
	@NotNull
	private BigDecimal balance;

	public Wallet(WalletPK walletPK, BigDecimal balance) {
		super();
		this.walletPK = walletPK;
		this.balance = balance;
	}

	public Wallet() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((walletPK == null) ? 0 : walletPK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (walletPK == null) {
			if (other.walletPK != null)
				return false;
		} else if (!walletPK.equals(other.walletPK))
			return false;
		return true;
	}

	public WalletPK getWalletPK() {
		return walletPK;
	}

	public void setWalletPK(WalletPK walletPK) {
		this.walletPK = walletPK;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public static class Builder {
		private WalletPK walletPK;
		private BigDecimal balance;

		public Builder walletPK(WalletPK walletPK) {
			this.walletPK = walletPK;
			return this;
		}

		public Builder balance(BigDecimal balance) {
			this.balance = balance;
			return this;
		}

		public Wallet build() {
			return new Wallet(this);
		}
	}

	private Wallet(Builder builder) {
		this.walletPK = builder.walletPK;
		this.balance = builder.balance;
	}
}
