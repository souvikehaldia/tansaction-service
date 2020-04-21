package com.tcs.bankclient.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.bankclient.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
