package com.fh.shop.api.util;

import redis.clients.jedis.JedisCluster;

import java.util.Map;

public class RedisUtil {

    public static void set(String key, String value) {
        JedisCluster jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    public static String get(String key) {

        JedisCluster jedis = null;
        String s = null;
        try {
            jedis = RedisPool.getJedis();
            s = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e.getMessage());
        }
        return s;
    }

    public static Long del(String key) {
        JedisCluster jedis = null;
        Long del = 0L;
        try {
            jedis = RedisPool.getJedis();
             del = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e.getMessage());
        }
        return del;
    }

    public static void setEx(String key, int seconds, String value) {
        JedisCluster jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException();
        }
    }


    public static boolean exist(String key) {

        Boolean exists = null;
        JedisCluster jedis = null;
        try {
            jedis = RedisPool.getJedis();
            exists = jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return exists;
    }


    public static void expire(String key, int seconds) {
        JedisCluster jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }


    public static void hset(String key, String field, String value) {
        JedisCluster jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static String hget(String key, String field) {
        JedisCluster jedis = null;
        String hget = "";
        try {
            jedis = RedisPool.getJedis();
            hget = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return hget;
    }


    public static void hsetnx(String key, String field, String value) {
        JedisCluster jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.hsetnx(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static Map<String,String> hgetAll(String key) {
        JedisCluster jedis = null;
        Map<String, String> stringStringMap = null;
        try {
            jedis = RedisPool.getJedis();
            stringStringMap = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return stringStringMap;
    }



    public static void hdel(String key, String field) {
        JedisCluster jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.hdel(key,field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}