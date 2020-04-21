package com.tcs.bankclient.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	@Id
	@Column(name = "transactionid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter @Setter
	private Integer transactionid;
	
	@Column(name = "time")
	@CreationTimestamp
	@Getter @Setter
	private Date time;
	
	@Column(name = "amount")  //no negative to be added
	@NotNull(message = "Transaction amount can not be null")
	@Getter @Setter
	private double amount;
	
	@Column(name = "updatetype")
	@Getter @Setter
	private String updatetype;
	
	@Column(name = "status")
	@Getter @Setter
	private String status;
	
	@Column(name = "result")
	@Getter @Setter
	private String result;
	
	@Column(name = "modeofpayment")
	@Getter @Setter
	private String modeofpayment;
	
	@Column(name = "fromaccountid")
	@Getter @Setter
	@NotNull(message = "Accountid can not be null")
	private int fromaccountid; 
	
	@Column(name = "toaccountid")
	@Getter @Setter
	@NotNull(message = "Accountid can not be null")
	private int toaccountid;
	
	
	
	
	
	
	
}
