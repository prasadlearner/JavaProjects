package in.kp.service;

import java.util.List;

import in.kp.factory.MangDaoFactory;
import in.kp.model.Book;
import in.kp.model.Management;

public class ManagementService implements IManagement {

	@Override
	public String mangReg(Management mang) {
		in.kp.dao.IManagement mangDAO=MangDaoFactory.getMangDao();
		String msg=mangDAO.mangReg(mang);
		return msg;
	}

	@Override
	public String mangLogin(String mid, String mpwd) {
		in.kp.dao.IManagement mangDAO=MangDaoFactory.getMangDao();
		String msg=mangDAO.mangLogin(mid, mpwd);
		return msg;
	}

	@Override
	public String mangIssueBookStud(String sid, String bid) {
		in.kp.dao.IManagement mangDAO=MangDaoFactory.getMangDao();
		String msg=mangDAO.mangIssueBookStud(sid, bid);
		return msg;
	}

	@Override
	public String mangAddBookLib(Book book) {
		in.kp.dao.IManagement mangDAO=MangDaoFactory.getMangDao();
		String msg=mangDAO.mangAddBookLib(book);
		return msg;
	}

	@Override
	public String mangUpdateBookLib(Book book) {
		in.kp.dao.IManagement mangDAO=MangDaoFactory.getMangDao();
		String msg=mangDAO.mangUpdateBookLib(book);
		return msg;
	}

	@Override
	public String mangRemoveBookLib(Book book) {
		in.kp.dao.IManagement mangDAO=MangDaoFactory.getMangDao();
		String msg=mangDAO.mangRemoveBookLib(book);
		
		return msg;
	}

	@Override
	public List<Book> mangSearchBook(String attr,String val) {
		in.kp.dao.IManagement mangDAO=MangDaoFactory.getMangDao();
		List<Book> books=mangDAO.mangSearchBook(attr,val);
		return books;
	}

}
