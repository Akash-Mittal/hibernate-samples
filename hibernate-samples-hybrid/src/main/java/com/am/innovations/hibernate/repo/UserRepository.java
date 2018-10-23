package com.am.innovations.hibernate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.am.innovations.hibernate.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
