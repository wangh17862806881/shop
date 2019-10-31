package com.fh.shop.api.util;

import redis.clients.jedis.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class RedisPool {

    private RedisPool (){}

    private static JedisCluster jedisCluster;

    private static void InitJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMinIdle(100);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        //普通jedis连接池
        /*jedisPool = new JedisPool(jedisPoolConfig,"192.168.150.129",7020);*/

        //jedis集群连接池   cluster
        Set<HostAndPort>  clusterConfigs = new LinkedHashSet<>();
        clusterConfigs.add(new HostAndPort("192.168.150.129",7001));
        clusterConfigs.add(new HostAndPort("192.168.150.129",7002));
        clusterConfigs.add(new HostAndPort("192.168.150.129",7003));
        clusterConfigs.add(new HostAndPort("192.168.150.129",7004));
        clusterConfigs.add(new HostAndPort("192.168.150.129",7005));
        clusterConfigs.add(new HostAndPort("192.168.150.129",7006));
        jedisCluster = new JedisCluster(clusterConfigs,jedisPoolConfig);

    }
    static{
        InitJedisPool();
    }

public static JedisCluster getJedis(){

return jedisCluster;
}


}
