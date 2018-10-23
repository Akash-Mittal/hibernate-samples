package com.am.innovations.hibernate.jpa;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AuditModel implements Serializable {

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", balance=" + balance + "]";
	}

	private static final long serialVersionUID = 6730311371449786545L;
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userID;

	@Column(name = "user_name")
	@NotNull
	@Size(max = 120)
	private String userName;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@MapKey(name = "currency")
	private Map<CURRENCY, Balance> balance = new ConcurrentHashMap<>();

	public User() {
		super();
	}

	public User(Long userID, String userName) {
		super();
		this.userID = userID;
		this.userName = userName;
	}

	public User(String userName) {
		super();
		this.userName = userName;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<CURRENCY, Balance> getBalance() {
		return balance;
	}

	public void setBalance(Map<CURRENCY, Balance> balance) {
		this.balance = balance;
	}

}
