package in.kp.service;

import java.util.List;

import in.kp.model.Book;
import in.kp.model.StudBookTracker;
import in.kp.model.Student;

public interface IStudent {
	
	String studReg(Student stud);
	String studLogin(String sid,String pwd);
	
	List<Book> studAvilBooks();
	String studSubmitBook(String sid,String bid);
	
	List<StudBookTracker> studMyFines(String sid);
	List<Book> studMyBooks(String sid);

}
