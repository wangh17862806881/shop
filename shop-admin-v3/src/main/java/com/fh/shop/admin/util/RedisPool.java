package com.fh.shop.admin.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private RedisPool(){};

    //连接池对象
    private static JedisPool jedisPool;

//连接池配置
    public static void  InitJedisPool(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(1000);
        //最大空闲数
        jedisPoolConfig.setMaxIdle(100);
        //最小空闲数
        jedisPoolConfig.setMinIdle(100);

        //测试连接  #jedis调用borrowObject方法时，是否进行有效检查#
        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        jedisPoolConfig.setTestOnBorrow(true);

        //#jedis调用returnObject方法时，是否进行有效检查 #
        jedisPoolConfig.setTestOnReturn(true);

        //创建连接池
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.150.129",7020);

    }

//静态代码块   只会执行一次
    static {
        InitJedisPool();
}

//调用此方法返回jedis对象
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }


}
