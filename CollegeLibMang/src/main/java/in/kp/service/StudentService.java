package in.kp.service;

import java.util.List;

import in.kp.dao.StudentDAO;
import in.kp.factory.StudDaoFactory;
import in.kp.model.Book;
import in.kp.model.StudBookTracker;
import in.kp.model.Student;

public class StudentService implements IStudent {

	@Override
	public String studReg(Student stud) {
		in.kp.dao.IStudent  studDAO=StudDaoFactory.getStudDao();
		String msg=studDAO.studReg(stud);
		return msg;
	}

	@Override
	public String studLogin(String sid, String pwd) {
		in.kp.dao.IStudent  studDAO=StudDaoFactory.getStudDao();
		String msg=studDAO.studLogin(sid, pwd);
		return msg;
	}

	@Override
	public List<Book> studAvilBooks() {
		in.kp.dao.IStudent  studDAO=StudDaoFactory.getStudDao();
		List<Book> list=studDAO.studAvilBooks();
		return list;
	}

	@Override
	public String studSubmitBook(String sid, String bid) {
		in.kp.dao.IStudent  studDAO=StudDaoFactory.getStudDao();
		String msg=studDAO.studSubmitBook(sid, bid);
		return msg;
	}

	@Override
	public List<StudBookTracker> studMyFines(String sid) {
		in.kp.dao.IStudent  studDAO=StudDaoFactory.getStudDao();
		List<StudBookTracker> list=studDAO.studMyFines(sid);
		return list;
	}
	@Override
	public List<Book> studMyBooks(String sid){
		in.kp.dao.IStudent studDao=StudDaoFactory.getStudDao();
		List<Book> books=studDao.studMyBooks(sid);
		return books;
	}

}
