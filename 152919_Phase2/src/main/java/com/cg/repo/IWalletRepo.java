package com.cg.repo;



import com.cg.bean.Customer;
import com.cg.bean.Wallet;
import com.cg.exception.WalletException;

public interface IWalletRepo {
	boolean save(Customer customer) throws WalletException;
	Customer find(String mobile) throws WalletException;
	boolean createAccount(Customer customer)throws WalletException;
	
	
	
	
}
