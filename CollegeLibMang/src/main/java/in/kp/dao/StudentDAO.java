package in.kp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.kp.model.Book;
import in.kp.model.StudBookTracker;
import in.kp.model.Student;
import in.kp.util.JDBCUtil;

public class StudentDAO implements IStudent {

	@Override
	public String studReg(Student student) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sqlQuery=null;
		int count=0;
		Boolean flag=true;
		try {
		conn=JDBCUtil.getConnection();
		String sid=student.getSid();
		String sname=student.getSname();
		String smail=student.getSmail();
		String spwd=student.getSpwd();
		
		sqlQuery="insert into college_lib_mang.student(sid,sname,smail,spwd) values(?,?,?,?)";
		
		if(conn!=null) {
			pstmt=conn.prepareStatement(sqlQuery);
			pstmt.setString(1, sid);
			pstmt.setString(2,sname);
			pstmt.setString(3, smail);
			pstmt.setString(4, spwd);
			if(pstmt!=null) {
				flag=pstmt.execute();
			}
		}
		
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		if(!flag) {
			return "Student added successfully!";
		}else
		return "Error in adding student to DB";

	}

	@Override
	public String studLogin(String sid, String pwd) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sqlQuery=null;
		ResultSet resultSet=null;
		String msg=null;
		try {
			conn=JDBCUtil.getConnection();
			String sqlQuey="select spwd from college_lib_mang.student where sid=?";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuey);
				pstmt.setString(1, sid);
				
				resultSet=pstmt.executeQuery();
				System.out.println("actual pwd:"+pwd);
				if(resultSet!=null) {
					
					String spwd=null;
					while(resultSet.next()) {
					spwd=resultSet.getString("spwd");}
					System.out.println("pwd from db:"+spwd);
					if(pwd.equals(spwd)) {
						msg="Login successful";
					}
					else
					msg="incorrect credentials";
				}
				else
					msg="enter correct ID";
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		
		return msg;

	}

	@Override
	public List<Book> studAvilBooks() {
		List<Book> books=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		List<String> bidList=new ArrayList();
		Book book=new Book();
		try {
			conn=JDBCUtil.getConnection();
			//String sqlQuery1="select bid from college_lib_mang.studentbooktracker where sid=?";
			String sqlQuery="select * from college_lib_mang.book";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery);
				
				if(pstmt!=null) {
					
						resultSet=pstmt.executeQuery();
						while(resultSet.next()) {
							System.out.println(resultSet);
							book.setBid(resultSet.getString("bid"));
							book.setBauthor(resultSet.getString("bauthor"));
							book.setBtitle(resultSet.getString("btitle"));
							book.setBcategory(resultSet.getString("bcategory"));
							System.out.println(book.toString());
							books.add(book);
						}
					}
				}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public String studSubmitBook(String sid, String bid) {
		String msg=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		int count;
		
		try {
			conn=JDBCUtil.getConnection();
			java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
			System.out.println(sid+":"+bid);
			String sqlQuery="update college_lib_mang.studentbooktracker set submitdate=? where sid=? and bid=?";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery);
				if(pstmt!=null) {
					pstmt.setDate(1, date);
					pstmt.setString(2, sid);
					pstmt.setString(3, bid);
					count=pstmt.executeUpdate();
					if(count==1) {
						msg="book submitted successully!";
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<StudBookTracker> studMyFines(String sid) {
		List<StudBookTracker> books=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		List<String> bidList=new ArrayList();
		StudBookTracker book=new StudBookTracker();
		try {
			conn=JDBCUtil.getConnection();
			//String sqlQuery1="select bid from college_lib_mang.studentbooktracker where sid=?";
			String sqlQuery="select * from college_lib_mang.studentbooktracker";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery);
				
				if(pstmt!=null) {
					
						resultSet=pstmt.executeQuery();
						while(resultSet.next()) {
							System.out.println(resultSet);
							book.setSid(resultSet.getString("sid"));
							book.setBid(resultSet.getString("bid"));
							book.setBorrowdate(resultSet.getDate("borrowdate"));
							book.setSubmitdate(resultSet.getDate("submitdate"));
							book.setFine(resultSet.getInt("fine"));
							System.out.println(book.toString());
							books.add(book);
						}
					}
				}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;

	}
	@Override
	public List<Book> studMyBooks(String sid){
		List<Book> books=new ArrayList();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		List<String> bidList=new ArrayList();
		Book book=new Book();
		try {
			conn=JDBCUtil.getConnection();
			String sqlQuery1="select bid from college_lib_mang.studentbooktracker where sid=?";
			String sqlQuery2="select * from college_lib_mang.book where bid=?";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery1);
				if(pstmt!=null) {
					pstmt.setString(1, sid);
					resultSet=pstmt.executeQuery();
					while(resultSet.next()) {
						String bid=resultSet.getString(1);
						bidList.add(bid);
					}
					pstmt=null;
					resultSet=null;
				}
				System.out.println(bidList);
				pstmt=conn.prepareStatement(sqlQuery2);
				if(pstmt!=null) {
					System.out.println("pstmt2 ::");
					for(String bid:bidList) {
						pstmt.setString(1,bid);
						System.out.println(bid);
						resultSet=pstmt.executeQuery();
						while(resultSet.next()) {
							System.out.println(resultSet);
							book.setBid(resultSet.getString("bid"));
							book.setBauthor(resultSet.getString("bauthor"));
							book.setBtitle(resultSet.getString("btitle"));
							book.setBcategory(resultSet.getString("bcategory"));
							System.out.println(book.toString());
							books.add(book);
						}
					}
				}
				
				
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

}
