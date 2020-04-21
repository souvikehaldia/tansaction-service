package com.tcs.bankclient.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.bankclient.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
