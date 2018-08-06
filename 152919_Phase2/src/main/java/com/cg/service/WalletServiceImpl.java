package com.cg.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.Customer;
import com.cg.bean.Transaction;
import com.cg.bean.Wallet;
import com.cg.exception.WalletException;
import com.cg.repo.IWalletRepo;
import com.cg.repo.WalletRepoImpl;

public class WalletServiceImpl implements IWalletService{
	private IWalletRepo repo= new WalletRepoImpl();
	
	@Override
	public Customer createAccount(Customer customer) throws WalletException {
		// TODO Auto-generated method stub
				if (customer.getMobileNo() == null || customer.getMobileNo().isEmpty()) {
					throw new WalletException("The mobile number cannot be left empty.");
				}else {
				if (!customer.getMobileNo().matches("\\d{10}")) {
					throw new WalletException("The mobile number must contain only 10 digits");
				}
				}
				if (customer.getName() == null || customer.getName().isEmpty()) {
					throw new WalletException("The name cannot be left empty.");
				} else {
					if (!customer.getName().matches("[A-Za-z]{2,}")) {
						throw new WalletException(
								"The name should contain only alphabets,minimum of 2 and maximum of 12");
					}
				}
				int res=customer.getWallet().getBalance().compareTo(new BigDecimal("0"));
				if(res==0 ||  res==-1){
					throw new WalletException("The balance cannot be less than or zero");
				}
				
				if(!customer.getWallet().getBalance().toString().matches("\\d")){
					throw new WalletException("Please Enter only didgits");
				}
				
				if(repo.createAccount(customer)) {
					return customer;
				}
				else {
					throw new WalletException("Could not create the account");
				}
	}

	@Override
	public Wallet showBal(String mobileNo) throws WalletException {
		// TODO Auto-generated method stub
		if (mobileNo.equals("") ) {
			throw new WalletException("The mobile number cannot be left empty.");
		}else {
		if (!mobileNo.matches("\\d{10}")) {
			throw new WalletException("The mobile number must contain only 10 digits");
		}
		}
		return repo.find(mobileNo).getWallet();
	}

	@Override
	public Customer deposit(String mobileNo,BigDecimal credit) throws WalletException {
		if (mobileNo.equals("") ) {
			throw new WalletException("The mobile number cannot be left empty.");
		}else {
		if (!mobileNo.matches("\\d{10}")) {
			throw new WalletException("The mobile number must contain only 10 digits");
		}
		}
		int res=credit.compareTo(new BigDecimal("0"));
		if(res==0 ||  res==-1){
			throw new WalletException("The deposited amount must be greater than 0.");
		}
		Customer customer = repo.find(mobileNo);
		if (customer != null && customer.getWallet() != null) {
			Wallet wallet = customer.getWallet();
			wallet.setBalance(wallet.getBalance().add(credit));
			customer.setWallet(wallet);
			Transaction transaction = new Transaction();
			transaction.setDate(Date.valueOf(LocalDate.now()));
			transaction.setWallet(wallet);
			transaction.setTransactionType("deposit");
			transaction.setAmt(credit);
			transaction.setAmtType("credit");
			List<Transaction> transactionList=new ArrayList<Transaction>();
			transactionList.add(transaction);
			wallet.setTransaction(transactionList);
			
			if(repo.save(customer)) {
				return customer;
			}else {
				throw new WalletException("No entity found for query");
			}
			
		}
		else {
			throw new WalletException("Cannot deposit");
		}
	}

	@Override
	public Customer withdraw(String mobileNo, BigDecimal debit) throws WalletException {
		// TODO Auto-generated method stub
				if (mobileNo.equals("") ) {
					throw new WalletException("The mobile number cannot be left empty.");
				}else {
				if (!mobileNo.matches("\\d{10}")) {
					throw new WalletException("The mobile number must contain only 10 digits");
				}
				}
				int res=debit.compareTo(new BigDecimal("0"));
				if(res==0 ||  res==-1){
					throw new WalletException("The withdraw amount must be greater than 0.");
				}
				Customer customer =repo.find(mobileNo);
				
				if (customer != null && customer.getWallet() != null) {
					int res2= debit.compareTo(customer.getWallet().getBalance());
					if(res2==1) {
						throw new WalletException("The withdraw amount must be less than available balance.");
					}
					else {
						Wallet wallet = customer.getWallet();
						wallet.setBalance(wallet.getBalance().subtract(debit));
						customer.setWallet(wallet);
						Transaction transaction = new Transaction();
					transaction.setDate(Date.valueOf(LocalDate.now()));
					transaction.setWallet(wallet);
					transaction.setTransactionType("withdraw");
					transaction.setAmt(debit);
					transaction.setAmtType("debit");
						List<Transaction> transactionList=new ArrayList<Transaction>();
						transactionList.add(transaction);
						wallet.setTransaction(transactionList);
						repo.save(customer);
						return customer;
					}
					
				}else {
					throw new WalletException("Cannot Withdraw");
				}
		
	}

	@Override
	public boolean fundTransfer(String srcMobileNo, String destMobileNo, BigDecimal amount)
			throws WalletException {
		// TODO Auto-generated method stub

		IWalletService service= new WalletServiceImpl();
		Customer src= service.withdraw(srcMobileNo, amount);
		Customer dest= service.deposit(destMobileNo, amount);
		
		if (src != null && dest != null ) {
			
			return true;
		}
		else {
			throw new WalletException("Cannot transfer funds");
		}
	
	}

	@Override
	public List<Transaction> printTransactionDetails(String mobileNo) throws WalletException {
		// TODO Auto-generated method stub
				if (mobileNo.equals("") ) {
					throw new WalletException("The mobile number cannot be left empty.");
				}else {
				if (!mobileNo.matches("\\d{10}")) {
					throw new WalletException("The mobile number must contain only 10 digits");
				}
				}
				
				List<Transaction> transaction = new ArrayList<Transaction>();
				Customer customer = repo.find(mobileNo);
				Wallet wallet =customer.getWallet();
				transaction=wallet.getTransaction();
				return transaction;
	
	}

}
