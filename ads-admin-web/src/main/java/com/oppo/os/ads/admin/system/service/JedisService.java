package com.oppo.os.ads.admin.system.service;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.oppo.os.ads.admin.system.tools.RedisUtil;

import redis.clients.jedis.Jedis;

@Service
public class JedisService {

	public String getRedisCon() {
		 Jedis jedis = new Jedis("10.80.248.24");
//	      jedis.select(1);
	      System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	      System.out.println("Server is running: "+jedis.ping());
	    return "success";
	}
//	private static RedisUtil redisUtil = new RedisUtil();
//	
//    public Jedis jedis = getJedis();
//    //返回jedis
//    public static Jedis getJedis(){
//        return redisUtil.returnJedis();
//    }
}
