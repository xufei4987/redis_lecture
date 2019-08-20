package com.youxu.springRedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/16 16:48
 **/
public class JedisClusterFactory {
    private JedisCluster jedisCluster;
    private Set<String> hostAndPorts;

    //单位是毫秒
    private int timeout;

    private void init(){
        //可以设置一些参数
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        Set<HostAndPort> sets = hostAndPorts.stream().map(hostAndPort -> {
            String[] split = hostAndPort.split(":");
            if (split.length != 2) {
                throw new RuntimeException("参数错误");
            }
            return new HostAndPort(split[0], Integer.valueOf(split[1]));
        }).collect(Collectors.toSet());

        try {
            jedisCluster = new JedisCluster(sets,timeout,jedisPoolConfig);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void destroy(){
        if(jedisCluster != null){
            try {
                jedisCluster.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setHostAndPorts(Set<String> hostAndPorts) {
        this.hostAndPorts = hostAndPorts;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
