package in.kp.service;

import java.util.List;

import in.kp.model.Book;
import in.kp.model.Management;

public interface IManagement {

	String mangReg(Management mang);
	String mangLogin(String mid,String mpwd);
	
	String mangIssueBookStud(String sid,String bid);
	
	String mangAddBookLib(Book book);
	String mangUpdateBookLib(Book book);
	String mangRemoveBookLib(Book book);
	List<Book> mangSearchBook(String attr,String val);

}
