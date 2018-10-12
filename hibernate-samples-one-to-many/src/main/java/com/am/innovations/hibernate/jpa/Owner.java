package com.am.innovations.hibernate.jpa;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "owner")
	@Cascade(CascadeType.SAVE_UPDATE)
	private Collection<House> house = new ArrayList<House>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<House> getHouse() {
		return house;
	}

	public void setHouse(Collection<House> house) {
		this.house = house;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Owner() {
		// TODO Auto-generated constructor stub
	}

	public Owner(String name) {
		this.name = name;
	}
}
