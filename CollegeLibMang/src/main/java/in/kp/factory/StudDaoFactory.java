package in.kp.factory;

import in.kp.dao.IStudent;
import in.kp.dao.StudentDAO;

public class StudDaoFactory {

	public static IStudent studDao=null;
	
	public static IStudent getStudDao() {
		
		if(studDao==null) {
			studDao=new StudentDAO();
			
		}
		return studDao;
	}
}
