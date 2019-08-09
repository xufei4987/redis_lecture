package com.youxu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/9 9:56
 * 需要远程连接redis需要注意3点：
 * 1、注释bind 127.0.0.1
 * 2、将保护模式（protected-mode）设置为no
 * 3、如果设置了授权需要进行认证
 **/
public class Demo01 {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("10.1.101.217", 6380);
//        System.out.println(jedis.get("hello"));
//        System.out.println(jedis.lrange("mylist", 0, -1));
//        System.out.println(jedis.zrevrangeWithScores("player", 0, -1));

        JedisPool jedisPool = new JedisPool("10.1.101.217", 6380);
        Jedis jedis1 = null;
        try {
            jedis1 = jedisPool.getResource();
            System.out.println(jedis1.get("hello"));
            System.out.println(jedis1.lrange("mylist", 0, -1));
            System.out.println(jedis1.zrevrangeWithScores("player", 0, -1));
        }finally {
            if (jedis1 != null) {
                jedis1.close();
            }
        }


    }
}
