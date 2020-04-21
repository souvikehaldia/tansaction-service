package com.tcs.bankclient.service;

import com.tcs.bankclient.model.Response;
import com.tcs.bankclient.model.Transaction;

public interface TransactionService {
	public Response saveTransaction(Transaction transaction);
	public Response getTransaction() throws Exception;
	public Response getTransactionById(Integer transaction) throws Exception;
	public Response updateTransaction(Transaction transaction) throws Exception;

}
