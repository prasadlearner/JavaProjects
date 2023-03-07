package in.kp.factory;

import in.kp.service.IManagement;
import in.kp.service.ManagementService;

public class MangSrvFactory {
	
	public static IManagement mangSrv=null;
	
	public static IManagement getMangSrv() {
		if(mangSrv==null) {
			mangSrv=new ManagementService();
			
		}
		return mangSrv;
	}

}
