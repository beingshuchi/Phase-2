package com.cg.bean;


import java.util.List;

/**
 * @author SHUCHITA
 *
 */
public class Transactions {
	private int id=(int) Math.random()*100;
	private Wallet wallet;
	private String transactionType;
	private BigDecimal amt;
	private String amtType;
		public int getId() {
		return id;
	}
	
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public String getAmtType() {
		return amtType;
	}
	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", wallet=" + wallet+ ", transactionType=" + transactionType + ", amtType=" + amtType +"amount="+amt +"]";

				+ "]";
	}
	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
