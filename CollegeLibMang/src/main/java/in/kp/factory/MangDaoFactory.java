package in.kp.factory;

import in.kp.dao.IManagement;
import in.kp.dao.ManagementDAO;

public class MangDaoFactory {
	
	public static IManagement mangDao=null;
	
	public static IManagement getMangDao() {
		if(mangDao == null) {
			mangDao=new ManagementDAO();
		}
		return mangDao;
	}

}
