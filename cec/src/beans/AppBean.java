package beans;

import util.MyUtil;


public class AppBean {
	
	public AppBean(){
		
	}
	
	public String getBaseUrl(){
		return MyUtil.baseUrl();
    }

	public String getBasePath(){
		return MyUtil.basePath();
		
    }
  
}
