package com.cg.bean;

import java.math.BigDecimal;
import java.util.List;

public class Wallet {
	private BigDecimal balance;
	List<Transactions>transaction;
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Wallet [balance=" + balance + "]";
	}

	public List<Transactions> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transactions> transaction) {
		this.transaction = transaction;
	}
	
	
	
}
