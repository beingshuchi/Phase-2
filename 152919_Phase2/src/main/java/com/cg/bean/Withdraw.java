package com.cg.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Withdraw {
	private Date date;
	private BigDecimal debit;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	@Override
	public String toString() {
		return "Withdraw [date=" + date + ", debit=" + debit + "]";
	}
	
}
