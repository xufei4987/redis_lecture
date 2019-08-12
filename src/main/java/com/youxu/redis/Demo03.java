package com.youxu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/12 11:30
 * sentinel.conf内加入protected-mode no
 * master 填写实际IP
 **/
public class Demo03 {

    public static void main(String[] args) {
        int count = 0;
        HashSet<String> sentinels = new HashSet<>();
        sentinels.add("10.1.101.217:26380");
        sentinels.add("10.1.101.217:26381");
        sentinels.add("10.1.101.217:26382");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinels);
        while (true){
            count++;
            try (Jedis jedis = jedisSentinelPool.getResource()){
                int nextInt = new Random().nextInt(100000);
                String key = "key-" + nextInt;
                String value = "value-" + nextInt;
                jedis.set(key,value);
                if(count % 100 == 0){
                    System.out.println("key = " + key + " ,value = " + value);
                }
                TimeUnit.MILLISECONDS.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
