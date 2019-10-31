import com.alibaba.fastjson.JSONObject;
import com.fh.shop.admin.po.user.User;
import com.fh.shop.admin.util.RedisPool;
import com.fh.shop.admin.util.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

public class test {
@Test
    public void test(){

        Map<String,String> test = new HashMap();
        test.put("aa","aa");
        test.put("aa1","aa");
        test.put("aa2","aa");
        test.put("aa3","aa");
        test.put("aa4","aa");

        Set<Map.Entry<String, String>> entries = test.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+"=="+entry.getValue());
        }

    }


    public static void main(String[] args) {
        String s = "{\"code\":200,\"msg\":\"12346\",\"obj\":\"123\"}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        Object code = jsonObject.get("code");
        Integer  a = 200;
        System.out.println(a .equals(code));
    }


@Test
 public void testJedisPool(){
    User user = new User();
  //  user= (User) JSONObject.parse("{\"age\":1}");
    System.out.println(user);
}







    //服务器IP地址
    private static String ADDR = "192.168.150.129";
    //端口
    private static int PORT = 7020;
    //密码
    private static String AUTH = "wanghao";
    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    //连接超时的时间　　
    private static int TIMEOUT = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    //数据库模式是16个数据库 0~15
    public static final int DEFAULT_DATABASE = 0;
    /**
     * 初始化Redis连接池
     */

    static {

        try {

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT,null,DEFAULT_DATABASE);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * 获取Jedis实例
     */
@Test
    public   void getJedis() {

        try {

            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                String aaaaaaaaaaaaaaaaaaaa = resource.get("b");
                System.out.println("redis--服务正在运行: "+aaaaaaaaaaaaaaaaaaaa);

            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /***
     *
     * 释放资源
     */

   /* public static void returnResource(final Jedis jedis) {
        if(jedis != null) {
            jedisPool.returnResource(jedis);
        }*/



















@Test
    // jedis 连接
    public void testJedis (){
     Jedis jedis = new Jedis("192.168.150.129",7020);
    jedis.set("lishihui","lishihui");
    String lishihui = jedis.get("lishihui");
    System.out.println(lishihui);
    //释放资源
    jedis.close();
    }








}
