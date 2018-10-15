package com.am.innovations.hibernate.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends AuditModel implements Serializable {

	private static final long serialVersionUID = 6730311371449786545L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userID;

	@Column(name = "user_name")
	@NotNull
	@Size(max = 120)
	private String userName;

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

}