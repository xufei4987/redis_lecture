package com.youxu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/10 14:06
 * pipeline 一次网络传输进行批量操作，并返回所有的结果
 **/
public class Demo02 {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool("10.1.101.217", 6380);

        try(Jedis jedis = jedisPool.getResource()){
            Pipeline pipeline = jedis.pipelined();
            for (int i = 0; i < 100; i++){
                pipeline.hset("hashkey:" + i, "field" + i, "value" + i);
            }
            pipeline.sync();
        }
    }
}
