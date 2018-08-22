package com.oppo.os.ads.admin.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oppo.os.ads.admin.system.pojo.UserInfo;
import com.oppo.os.ads.admin.system.service.HttpClientApiService;
import com.oppo.os.ads.admin.system.service.UserInfoService;

@RestController
public class HelloController {

	@Value("${static.html}")
	private String html;

	@Value("${api_key}")
	private String key;
	
	@Value("${api_secret}")
	private String secret;
	
	@Value("${time_stamp}")
	private String timeStamp;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private HttpServletResponse httpServletResponse;
	@Autowired
	private HttpClientApiService  httpClientApiService;

	@RequestMapping("/")
	public void hello() throws Exception{
		httpServletResponse.sendRedirect(html);

    }
    @RequestMapping("/getUser")
	public UserInfo getUser() throws Exception{
		AttributePrincipal userPrincipal = (AttributePrincipal) httpServletRequest.getUserPrincipal();
		String uid = userPrincipal.getName();
	    UserInfo userInfo = userInfoService.setUserInfo(uid);
	 //   System.out.print(userInfo.toString());
	    
		 System.out.println(httpClientApiService.doGet("http://openapi.wanyol.com/"));

		return userInfo;
	}

    
	@RequestMapping("/getdb")
	public void getDb()  throws Exception{
			
		}
     
}
