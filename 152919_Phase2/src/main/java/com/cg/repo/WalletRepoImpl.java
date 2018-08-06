package com.cg.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.cg.bean.Customer;

import com.cg.bean.Wallet;
import com.cg.exception.WalletException;
import com.cg.util.DBUtil;

public class WalletRepoImpl implements IWalletRepo{
	
	@Override
	public boolean save(Customer customer) throws WalletException {
		boolean status=false;
		try
		{
			Connection con = DBUtil.getConnection();
			PreparedStatement stat = con.prepareStatement("UPDATE  CUSTOMER SET balance=? WHERE MOBILENO=?");
			stat.setBigDecimal(1, customer.getWallet().getBalance());
			stat.setString(2, customer.getMobileNo());
			int res=stat.executeUpdate();
			if(res==1) {
				status=true;
			}else {
				throw new WalletException("The entered mobile number does not exists.");
			}
		} 
		catch (SQLException e) {
			throw new WalletException(e.getMessage());
		}
		
		return status;
	}
	
	@Override
	public Customer find(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		try
		{
			Connection con = DBUtil.getConnection();
			PreparedStatement stat = con.prepareStatement("select * from customer where mobileNo = ?");
			stat.setString(1, mobile);
			ResultSet res = stat.executeQuery();
			
			if(res!=null) {
				Customer customer = new Customer();
				Wallet wallet = new Wallet();
				customer.setMobileNo(res.getString(1));
				wallet.setBalance(res.getBigDecimal(3));
				customer.setWallet(wallet);
				
				return customer;
			}
			else {
				throw new WalletException("The entered mobile number does not exists.");
			}
				}
		catch (SQLException e) {
			throw new WalletException(e.getMessage());
		}
			
		
	}

	@Override
	public boolean createAccount(Customer customer) throws WalletException {
		// TODO Auto-generated method stub
		boolean status=false;
		try
		{
			Connection con = DBUtil.getConnection();
			PreparedStatement stat = con.prepareStatement("insert into customer values(?,?,?)");
			stat.setString(1, customer.getName());
			stat.setString(2, customer.getMobileNo());
			stat.setBigDecimal(3, customer.getWallet().getBalance());
			status=stat.execute();
			
			PreparedStatement stat1 = con.prepareStatement("insert into Transactions values(?,?,?,?)");
			stat1.setDate(1, Date.valueOf(LocalDate.now()));;
			stat1.setBigDecimal(2, customer.getWallet().getBalance());
			stat1.setString(3,"deposit");
			stat1.setString(3,"credit");
			status=stat.execute();
		} 
		catch (SQLException e) {
			throw new WalletException(e.getMessage());
		}
		if(status) {
			status=true;
		}
		else {
			throw new WalletException("The customer already exists.");
		}
		return status;
	}
	

}
