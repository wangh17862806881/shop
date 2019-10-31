package com.fh.shop.admin.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtil {

    //String 新增数据方法  set
     public static void set(String key,String value){
         Jedis jedis = null;
         try {
             jedis =  RedisPool.getJedis();
             jedis.set(key, value);
         } catch (Exception e) {
             e.printStackTrace();
             new RuntimeException(e.getMessage());
         } finally {
             if(null != jedis){
                 // 如果使用 JedisPool ， close 操作不是关闭连接，代表归还连接池
                 jedis.close();
             }
         }

     }


     //String   获取数据方法  get
     public static String get(String key){
         Jedis jedis  = null;
         String s = null;
         try {
             jedis = RedisPool.getJedis();
             s = jedis.get(key);
         } catch (Exception e) {
             e.printStackTrace();
             //重新抛出异常
             new RuntimeException(e.getMessage());
         }finally{
             if(null != jedis){
                 //在连接池里  close 是代表归还的意思  并不是关闭连接  在close()方法中
                 //有判断他是不是连接池里的jedis  处理方式不一样  不使用连接池直接关闭连接
                 jedis.close();
             }
         }
        return s;
     }


     //String   删除数据  del
    public static void del(String... key){
        //获取jedis
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e.getMessage());
        } finally {
            if(null != jedis){
                jedis.close();
            }
        }

    }

    //String    新增数据  setex
    public static void setEx(String key,int seconds,String value){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.setex(key,seconds,value);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e.getMessage());
        } finally {
            if(null != jedis){
                jedis.close();
            }
        }

    }

    //设置过期时间
    public static void expire(String key,int seconds){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.expire(key,seconds);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e.getMessage());
        }finally{
            if(null !=jedis){
               jedis.close();
            }
        }
    }

}
