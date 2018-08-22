package com.oppo.os.ads.admin.system.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 用于连接redis的工具类
 * @author yangfuren
 * @since 2017/11/21
 */
public class RedisUtil implements Serializable{

    private  final long serialVersionUID = 1L;
    //Redis服务器IP
    private static String addr="172.17.160.222";

    //Redis的端口号
    private static int port=30001;

    //访问密码
    private static String auth="ads#2017!";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int maxActive;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int maxIdle;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int maxWait;
    private static int timeOut;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean testOnBorrow;

    public static Jedis jedis;//非切片额客户端连接

    public static JedisPool jedisPool;//非切片连接池

    public static ShardedJedis shardedJedis;//切片额客户端连接

    public static ShardedJedisPool shardedJedisPool;//切片连接池

    /** 切片池和非切片池的区别主要在于有没有主从redis 主从redis主要用于分布式系统
     *  分布式系统使用切片连接池 一般系统使用非切片池
     * 初始化非切片池
     */
    private static void initialPool() 
    { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig();
        System.out.println(addr);
        config.setMaxTotal(maxActive); 
        config.setMaxIdle(maxIdle); 
        config.setMaxWaitMillis(maxWait); 
        config.setTestOnBorrow(testOnBorrow);
        jedisPool = new JedisPool(config, addr, port);
    }

    /** 
     * 初始化切片池 
     */ 
    private static  void initialShardedPool() 
    { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive); 
        config.setMaxIdle(maxIdle); 
        config.setMaxWaitMillis(maxWait); 
        config.setTestOnBorrow(testOnBorrow);
        // slave链接 
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        shards.add(new JedisShardInfo(addr, port, auth)); 
        // 构造池 
        shardedJedisPool = new ShardedJedisPool(config, shards); 
    }
    /**
     * 非切片池返回jedis
     * @return
     */
    public  static Jedis returnJedis(){
        initialPool(); 
        initialShardedPool();
        try {
             jedis = jedisPool.getResource();
             jedis.auth(auth);
        } catch (Exception e) {
            System.out.println("连接jedisPool失败!");
        }
        return jedis;
    }
    /**
     * 切片池返回ShardedJedis
     * @return
     */
    public  static ShardedJedis returnShareJedis(){
        initialPool(); 
        initialShardedPool();
        try {
              shardedJedis = shardedJedisPool.getResource(); 
        } catch (Exception e) {
            System.out.println("连接shardedJedisPool失败!");
        }
        return shardedJedis;
    }

    
    
    
    public static String getAddr() {
        return addr;
    }

    public static void setAddr(String addr) {
        RedisUtil.addr = addr;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        RedisUtil.port = port;
    }

    public static String getAuth() {
        return auth;
    }

    public static void setAuth(String auth) {
        RedisUtil.auth = auth;
    }

    public static int getMaxActive() {
        return maxActive;
    }

    public static void setMaxActive(int maxActive) {
        RedisUtil.maxActive = maxActive;
    }

    public static int getMaxIdle() {
        return maxIdle;
    }

    public static void setMaxIdle(int maxIdle) {
        RedisUtil.maxIdle = maxIdle;
    }

    public static int getMaxWait() {
        return maxWait;
    }

    public static void setMaxWait(int maxWait) {
        RedisUtil.maxWait = maxWait;
    }

    public static int getTimeOut() {
        return timeOut;
    }

    public static void setTimeOut(int timeOut) {
        RedisUtil.timeOut = timeOut;
    }

    public static boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public static void setTestOnBorrow(boolean testOnBorrow) {
        RedisUtil.testOnBorrow = testOnBorrow;
    }
   }