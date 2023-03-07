package in.kp.factory;

import in.kp.service.IStudent;
import in.kp.service.StudentService;

public class StudSrvFactory {
	
	public static IStudent studService=null;
	
	public static IStudent getStudSrv() {
		if(studService == null) {
			studService=new StudentService();
		}
		
		return studService;
	}

}
