package in.kp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.kp.model.Book;
import in.kp.model.Management;
import in.kp.util.JDBCUtil;

public class ManagementDAO implements IManagement {

	@Override
	public String mangReg(Management management) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sqlQuery=null;
		int count=0;
		Boolean flag=true;
		try {
		conn=JDBCUtil.getConnection();
		String mid=management.getMid();
		String mname=management.getMname();
		String memail=management.getMemail();
		String mpwd=management.getMpwd();
		
		sqlQuery="insert into college_lib_mang.management(mid,mname,memail,mpwd) values(?,?,?,?)";
		
		if(conn!=null) {
			pstmt=conn.prepareStatement(sqlQuery);
			pstmt.setString(1, mid);
			pstmt.setString(2,mname);
			pstmt.setString(3, memail);
			pstmt.setString(4, mpwd);
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
			return "Management added successfully!";
		}else
		return "Error in adding management to DB";

	}

	@Override
	public String mangLogin(String mid, String mpwd) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sqlQuery=null;
		ResultSet resultSet=null;
		String msg=null;
		try {
			conn=JDBCUtil.getConnection();
			String sqlQuey="select mpwd from college_lib_mang.management where mid=?";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuey);
				pstmt.setString(1, mid);
				
				resultSet=pstmt.executeQuery();
				System.out.println("error in before if statement");
				if(resultSet!=null) {
					System.out.println("error in if statement");
					String mpwd2=null;
					while(resultSet.next()) {
					mpwd2=resultSet.getString("mpwd");}
					if(mpwd.equals(mpwd2)) {
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
	public String mangIssueBookStud(String sid, String bid) {
		String msg=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		Boolean flag=true;
		
		try {
			conn=JDBCUtil.getConnection();
			String sqlQuery="insert into college_lib_mang.studentbooktracker(sid,bid,borrowdate,submitdate,fine) values(?,?,?,?,?)";
			java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
			
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery);
				if(pstmt!=null) {
					pstmt.setString(1,sid);
					pstmt.setString(2,bid);
					pstmt.setDate(3,date);
					pstmt.setDate(4,null);
					pstmt.setInt(5, 0);
					
					flag=pstmt.execute();
					if(!flag) {
						msg= "requested boook:"+bid+" assigned to the student:"+sid;
					}
					else
						msg="error in assigning book to student";
					
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String mangAddBookLib(Book book) {
		String msg="error in adding book from management";
		Connection conn=null;
		PreparedStatement pstmt=null;
		Boolean flag=true;
		try {
			conn=JDBCUtil.getConnection();
			String sqlQuery="insert into college_lib_mang.book(bid,btitle,bauthor,bcategory) values(?,?,?,?)";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery);
				if(pstmt!=null) {
					pstmt.setString(1,book.getBid());
					pstmt.setString(2,book.getBtitle());
					pstmt.setString(3,book.getBauthor());
					pstmt.setString(4,book.getBcategory());
					
					flag=pstmt.execute();
					if(!flag) {
						msg="book added to db";
					}else
						msg="error in adding book to db";
				}
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
		return msg;

	}

	@Override
	public String mangUpdateBookLib(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mangRemoveBookLib(Book book) {
		String msg="error in deletebook";
		Connection conn=null;
		PreparedStatement pstmt=null;
		String bid=book.getBid();
		int count=0;
		try {
			conn=JDBCUtil.getConnection();
			String sqlQuery="delete from college_lib_mang.book where bid=?";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery);
				if(pstmt!=null) {
					pstmt.setString(1,bid);
					
					count=pstmt.executeUpdate();
					if(count == 1) {
						msg="book:"+bid+" deleted successfully from books db";
					}
					else {
						msg="error in deleting book"+bid;
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
	public List<Book> mangSearchBook(String attribute , String value) {
		List<Book> books=new ArrayList<Book>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		Book book=null;
		try {
			conn=JDBCUtil.getConnection();
			String sqlQuery="select * from college_lib_mang.book where "+attribute+"=?";
			if(conn!=null) {
				pstmt=conn.prepareStatement(sqlQuery);
				if(pstmt!=null) {
					//pstmt.setString(1,"btitle");
					pstmt.setString(1,value);
					resultSet=pstmt.executeQuery();
					
					if(resultSet!=null) {
							while(resultSet.next()) {
							
							book=new Book();
							book.setBid(resultSet.getString(1));
							book.setBtitle(resultSet.getString(2));
							book.setBauthor(resultSet.getString(3));
							book.setBcategory(resultSet.getString(4));
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
