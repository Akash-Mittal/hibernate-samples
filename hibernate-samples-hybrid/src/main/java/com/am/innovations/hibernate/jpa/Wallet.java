package com.am.innovations.hibernate.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class Wallet extends AuditModel implements Serializable {

	/*
	 * Wallet = Currency + Balance + userID
	 */

	private static final long serialVersionUID = 6158253008522228396L;

	@Id
	@Column(name = "wallet_id")
	private Long walletID;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId(value = "user_id")
	private User user;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Balance> balance = new ArrayList<>();

}
