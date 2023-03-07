package in.kp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.kp.factory.MangSrvFactory;
import in.kp.factory.StudSrvFactory;
import in.kp.model.Book;
import in.kp.model.Management;
import in.kp.model.StudBookTracker;
import in.kp.model.Student;
import in.kp.service.IManagement;
import in.kp.service.IStudent;

@WebServlet("/controller/*")
public class AppController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AppController() {
        super();
        System.out.println("controller instantiated..");
        
    }
   //Student related methods 
    public void studLogin(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Student Login***");
    	IStudent studSrv=StudSrvFactory.getStudSrv();
    	String sid=request.getParameter("sid");
    	String spwd=request.getParameter("spwd");
    	String msg=studSrv.studLogin(sid,spwd);
    	System.out.println(msg);
    }
    public void studRegister(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Student Registration***");
    	Student stud=new Student();
    	stud.setSid(request.getParameter("sid"));
    	stud.setSname(request.getParameter("sname"));
    	stud.setSmail(request.getParameter("smail"));
    	stud.setSpwd(request.getParameter("spwd"));
    	
    	IStudent studSrv=StudSrvFactory.getStudSrv();
    	String msg=studSrv.studReg(stud);
    	System.out.println(msg);
    }
    public void studMyBooks(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Student My Books***");
    	String sid=request.getParameter("sid");
    	IStudent studSrv=StudSrvFactory.getStudSrv();
    	List<Book> books=studSrv.studMyBooks(sid);
    	books.toString();
    }
    public void studAvilableBooks(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Student Avilable Books***");
    	
    	IStudent studSrv=StudSrvFactory.getStudSrv();
    	List<Book> books=studSrv.studAvilBooks();
    	
    	books.toString();
    }
    public void studSubmitBook(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Student Submit Book***");
    	String sid=request.getParameter("sid");
    	String bid=request.getParameter("bid");
    	
    	IStudent studSrv=StudSrvFactory.getStudSrv();
    	String msg=studSrv.studSubmitBook(sid,bid);
    	System.out.println(msg);
    }
    public void studMyFines(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Student My fines***");
    	String sid=request.getParameter("sid");
    	IStudent studSrv=StudSrvFactory.getStudSrv();
    	List<StudBookTracker> studFines=studSrv.studMyFines(sid);
    	
    	studFines.toString();
    }
    
    //management related methods
    public void mangLogin(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Management Login***");
    	
    	String mid=request.getParameter("mid");
    	String mpwd=request.getParameter("mpwd");
    	
    	IManagement mangSrv=MangSrvFactory.getMangSrv();
    	String msg=mangSrv.mangLogin(mid, mpwd);
    	System.out.println(msg);
    }
    public void mangRegister(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Management Register***");
    	
    	Management mang=new Management();
    	mang.setMid(request.getParameter("mid"));
    	mang.setMname(request.getParameter("mname"));
    	mang.setMemail(request.getParameter("memail"));
    	mang.setMpwd(request.getParameter("mpwd"));
    	
    	IManagement mangSrv=MangSrvFactory.getMangSrv();
    	String msg=mangSrv.mangReg(mang);
    	System.out.println(msg);
    }
    public void mangAddBookLib(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Management Adding Book to Library");
    	Book book=new Book();
    	book.setBid(request.getParameter("bid"));
    	book.setBtitle(request.getParameter("btitle"));
    	book.setBauthor(request.getParameter("bauthor"));
    	book.setBcategory(request.getParameter("bcategory"));
    	
    	IManagement mang=MangSrvFactory.getMangSrv();
    	String msg=mang.mangAddBookLib(book);
    	System.out.println(msg);
    }
    public void mangUpdateBookLib(HttpServletRequest request, HttpServletResponse response) {}
    public void mangRemoveBookLib(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Management Remove Book");
    	Book book=new Book();
    	String bid=request.getParameter("bid");
    	book.setBid(bid);
    	IManagement mangSrv=MangSrvFactory.getMangSrv();
    	String msg=mangSrv.mangRemoveBookLib(book);
    	
    	System.out.println(msg);
    	
    }
    public void mangSearchBookLib(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Management Search Book***");
    	String attr=request.getParameter("attribute");
    	String val=request.getParameter("value");
    	
    	IManagement mangSrv=MangSrvFactory.getMangSrv();
    	List<Book> books=mangSrv.mangSearchBook(attr,val);
    	
    	books.toString();
    	
    }
    public void mangIssueBookStud(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("***Management Issue Book");
    	String sid=request.getParameter("sid");
    	String bid=request.getParameter("bid");
    	
    	IManagement mangSrv=MangSrvFactory.getMangSrv();
    	String msg=mangSrv.mangIssueBookStud(sid, bid);
    	
    	System.out.println(msg);
    }
   
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String uri=request.getRequestURI();
    	System.out.println(uri);
    	//student Home Pages urls
    	if(uri.endsWith("studlogin")) { studLogin(request,response);}
    	if(uri.endsWith("studregister")) { studRegister(request,response);}
    	if(uri.endsWith("studavilbooks")) { studAvilableBooks(request,response);}
    	if(uri.endsWith("studmybooks")) {studMyBooks(request,response);}
    	if(uri.endsWith("studsubmitbook")) { studSubmitBook(request,response);}
    	if(uri.endsWith("studmyfines")) { studMyFines(request,response);}
    	
    	//management home page urls
    	if(uri.endsWith("manglogin")) { mangLogin(request,response);}
    	if(uri.endsWith("mangregister")) { mangRegister(request,response);}
    	if(uri.endsWith("mangaddbooklib")) { mangAddBookLib(request,response);}
    	if(uri.endsWith("mangupdatebooklib")) { mangUpdateBookLib(request,response);}
    	if(uri.endsWith("mangremovebooklib")) { mangRemoveBookLib(request,response);}
    	if(uri.endsWith("mangsearchbooklib")) { mangSearchBookLib(request,response);}
    	if(uri.endsWith("mangissuebookstud")) { mangIssueBookStud(request,response);}
    	

    	    	
    	
    	
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
