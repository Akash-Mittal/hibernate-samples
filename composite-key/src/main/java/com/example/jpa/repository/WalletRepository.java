package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.model.Wallet;
import com.example.jpa.model.WalletPK;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, WalletPK> {

}
