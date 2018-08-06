package com.cg.bean;


import java.util.List;

/**
 * @author SHUCHITA
 *
 */
public class Transactions {
	private int id=(int) Math.random()*100;
	private Wallet wallet;
	
	
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	public int getId() {
		return id;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", wallet=" + wallet 
				+ "]";
	}
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
