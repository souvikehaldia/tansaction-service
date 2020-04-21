package com.tcs.bankclient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.bankclient.dao.AccountRepository;
import com.tcs.bankclient.dao.TransactionRepository;
import com.tcs.bankclient.model.Account;
import com.tcs.bankclient.model.Response;
import com.tcs.bankclient.model.Transaction;
import com.tcs.bankclient.utitlities.AppUtilities;

@Service
public class TransactionServiceImpl {
	
	@Autowired
	private TransactionRepository repo;
	@Autowired
	private AccountRepository accountrepo;
	
	public Response getTransaction() throws Exception {
		List<Transaction> record=new ArrayList<Transaction>();
		repo.findAll().stream().forEach(record::add);
		////////////////////////////json to string//////////////////////////////
		

			String jsonStr=AppUtilities.jsonToString(record);
			
	    ////////////////////////////////////////////////////////////////////////
	        return new Response(true, jsonStr); 
	}

	public Response saveTransaction(Transaction transaction) 
	{
		double newBalance1=0.0;
		double newBalance2=0.0;
		
		Optional<Account> toAccountOpt = accountrepo.findById(transaction.getToaccountid());
		Optional<Account> fromAccountOpt = accountrepo.findById(transaction.getFromaccountid());
		
		if(!fromAccountOpt.isPresent() || !toAccountOpt.isPresent() )
		{
			return new Response(false, "Account not found");
		}
		
		Account toAccount= toAccountOpt.get();
		Account fromAccount= fromAccountOpt.get();
		
		String toAccountUserId=toAccount.getUser().getUserid();
		String fromAccountUserId=fromAccount.getUser().getUserid();
		
		if(!toAccountUserId.equals(fromAccountUserId))
		{
			if(transaction.getAmount()>0)
			{
				if(transaction.getModeofpayment().equals("cash"))
				{
			
					///////////////////////////Updating balance in accounts///////////////////////////////////////////////
					if(transaction.getUpdatetype().equalsIgnoreCase("debit"))
					{
						newBalance1=toAccount.getBalance()+transaction.getAmount();
						newBalance2=fromAccount.getBalance()-transaction.getAmount();				
					}
					if(transaction.getUpdatetype().equalsIgnoreCase("credit"))
					{
						newBalance1=toAccount.getBalance()-transaction.getAmount();
						newBalance2=fromAccount.getBalance()+transaction.getAmount();
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////////
				
					if(newBalance1<0 || newBalance2<0)
					{
						transaction.setResult("Transaction Failed");
						transaction.setStatus("failed");
					}
					else
					{
						toAccount.setBalance(newBalance1);
						fromAccount.setBalance(newBalance2);
						transaction.setResult("Transaction Complete");
						transaction.setStatus("success");
					}
				}
		
				else
				{
					transaction.setResult("Transaction to be completed at midnight");
					transaction.setStatus("pending");
				}
			
			
			if((repo.save(transaction))!=null)
				return new Response(true, "Transaction initiated with transaction id="+Integer.toString(transaction.getTransactionid())+" Status="+transaction.getStatus());
			else
				return new Response(false, "Transaction initiation failed");
		
		}
		else
		{
			return new Response(false,"Please enter an amount greater than 0");	
		}
	
		}
		else
		{
			return new Response(false,"Same user transaction invalid");
		}
	}
	
	
	public Response getTransactionById(Integer transactionid) throws Exception {
		Transaction testTran=new Transaction();
		String jsonString="";
		boolean success=false;
		Transaction tran=repo.findById(transactionid).orElse(testTran);
		if(tran==null)
		{
			jsonString="Transaction Not found";
		}
		else
		{
			jsonString=AppUtilities.jsonToString(tran);
			success=true;
		}
		
		return new Response(success, jsonString);
	}


	
	public Response updateTransaction(Transaction transaction) throws Exception
	{
		Transaction testTran=new Transaction();
		boolean success=false;
				
		Transaction oldTransaction=repo.findById(transaction.getTransactionid()).orElse(testTran);
		if(oldTransaction.getTransactionid().equals(transaction.getTransactionid()))
		{
			success=true;
		}
				
		if(success==true)
		{
			repo.save(transaction);
			return new Response (success,"Transaction with transaction id: "+Integer.toString(transaction.getTransactionid())+" is Updated");
		}
		else
		{
			return new Response(success,"Transaction not found");
		}
		
	}

}
