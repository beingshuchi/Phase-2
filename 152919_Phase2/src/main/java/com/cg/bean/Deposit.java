package com.cg.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Deposit {
	private Date date;
	private BigDecimal credit;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "Deposit [date=" + date + ", credit=" + credit + "]";
	}
	
}
