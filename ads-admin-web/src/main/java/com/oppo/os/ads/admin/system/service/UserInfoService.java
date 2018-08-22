package com.oppo.os.ads.admin.system.service;

import com.oppo.os.ads.admin.system.pojo.Handles;
import com.oppo.os.ads.admin.system.pojo.LeftMenu;
import com.oppo.os.ads.admin.system.pojo.TopTitle;
import com.oppo.os.ads.admin.system.pojo.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    public UserInfo setUserInfo(String uid){
        Handles handle = new Handles();
        handle.setId("100031");
        handle.setHandle_name("查询");
        handle.setLeftMenu_id("100003");
        handle.setContent("");
        handle.setSort("1");
        handle.setUrl("/select");


        LeftMenu leftMenu = new LeftMenu();
        leftMenu.setId("100003");
        leftMenu.setLeftmenu_name("导航一");
        leftMenu.setIcon("el-icon-message");
        leftMenu.setType("1");
        leftMenu.setUrl("/page1");
        leftMenu.setSort("1");
        leftMenu.setHandles(handle);





        TopTitle topTitle = new TopTitle();
        topTitle.setId("100001");
        topTitle.setType("1");
        topTitle.setToptitle_name("顶部标签1");
        topTitle.setIcon("el-icon-menu");
        topTitle.setSort("1");
        topTitle.setUrl("/page1");
        topTitle.setLeftMenu(leftMenu);




        UserInfo userInfo = new UserInfo();
        userInfo.setUser_id(uid);
        userInfo.setUser_email("17678341668@163.com");
        userInfo.setUser_name("王颖达");
        userInfo.setTopTitle(topTitle);

        return userInfo;
    };
}
