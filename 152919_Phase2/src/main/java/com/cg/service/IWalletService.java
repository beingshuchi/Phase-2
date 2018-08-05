package com.cg.service;

import java.math.BigDecimal;
import java.util.List;

import com.cg.bean.Customer;
import com.cg.bean.Transactions;
import com.cg.bean.Wallet;
import com.cg.exception.WalletException;

public interface IWalletService {
	Customer createAccount(Customer customer)throws WalletException;
	Wallet showBal(String mobileNo)throws WalletException;
	Customer deposit(String mobileNo,BigDecimal credit) throws WalletException;
	Customer withdraw(String mobileNo,BigDecimal debit) throws WalletException;
	boolean fundTransfer(String srcMobileNo, String destMobileNo,BigDecimal amount) throws WalletException;
	public List<Transactions>printTransactionDetails(String mobileNo) throws WalletException ;
}
