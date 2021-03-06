package com.am.innovations.hibernate.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.am.innovations.hibernate.entities.Balance;
import com.am.innovations.hibernate.enums.CURRENCY;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
	@Query("select bl from Balance bl where bl.user.userID =:userID and bl.currency =:currency")
	Optional<Balance> findByUserAndCurrency(@Param("userID") Long userID, @Param("currency") CURRENCY currency);

}
