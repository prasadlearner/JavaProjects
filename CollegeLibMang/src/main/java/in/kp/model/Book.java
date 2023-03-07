package in.kp.model;

public class Book {

	private String bid;
	private String btitle;
	private String bauthor;
	private String bcategory;
	
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public String getBcategory() {
		return bcategory;
	}
	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}
	
	@Override
	public String toString() {
		return "Book [bid=" + bid + ", btitle=" + btitle + ", bauthor=" + bauthor + ", bcategory=" + bcategory + "]";
	}
	
	
	
}
