package in.kp.model;

import java.util.Date;

public class StudBookTracker {
	
	private String sid;
	private String bid;
	private Date borrowdate;
	private Date submitdate;
	private Integer fine;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public Date getBorrowdate() {
		return borrowdate;
	}
	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}
	public Date getSubmitdate() {
		return submitdate;
	}
	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
	}
	public Integer getFine() {
		return fine;
	}
	public void setFine(Integer fine) {
		this.fine = fine;
	}
	
	@Override
	public String toString() {
		return "StudBookTracker [sid=" + sid + ", bid=" + bid + ", borrowdate=" + borrowdate + ", submitdate="
				+ submitdate + ", fine=" + fine + "]";
	}
	
	

}
