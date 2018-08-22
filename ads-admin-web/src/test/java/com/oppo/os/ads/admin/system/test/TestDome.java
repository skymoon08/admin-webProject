package com.oppo.os.ads.admin.system.test;

import org.junit.Test;


import com.oppo.os.ads.admin.system.service.HttpClientApiService;

public class TestDome {


	
	
	@Test
	public void getDb() throws Exception{
		HttpClientApiService httpClientApiService = new HttpClientApiService();
		//System.out.println(httpClientApiService.getClass().getName());
	   System.out.println(httpClientApiService.doGet("http://www.baidu.com"));	
	}
}
