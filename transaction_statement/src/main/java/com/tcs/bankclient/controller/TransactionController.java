package com.tcs.bankclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.bankclient.model.Response;
import com.tcs.bankclient.model.Transaction;
import com.tcs.bankclient.service.TransactionServiceImpl;

@RestController
public class TransactionController {
	
	@Autowired 
	private TransactionServiceImpl trans;
	
	@GetMapping("/getTransaction")
	public Response getTransaction() throws Exception
	{
		return trans.getTransaction();
	}
	
	@PostMapping("/transaction")
	public Response saveTransaction(@RequestBody Transaction transaction) throws Exception
	{
		return trans.saveTransaction(transaction);
	}
	
	@GetMapping("/getTransactionById")
	public Response getAccountById(@RequestParam Integer transactionid) throws Exception
	{
		return trans.getTransactionById(transactionid);
	}
	
	@PostMapping("/updateTransaction")
	public Response updateUser(@RequestBody Transaction transaction) throws Exception
	{
		return trans.updateTransaction(transaction);
	}

}
