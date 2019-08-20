package com.youxu.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Protocol;

import java.util.HashSet;

/**
 * @description TODO
 * @Author YouXu
 * @Date 2019/8/16 16:41
 * jedis-cluster
 **/
public class Demo04 {
    public static void main(String[] args) {
        HashSet<HostAndPort> hostAndPorts = new HashSet<>();
        hostAndPorts.add(new HostAndPort("10.1.101.217",6379));
        hostAndPorts.add(new HostAndPort("10.1.101.217",6380));
        hostAndPorts.add(new HostAndPort("10.1.101.217",6381));
        hostAndPorts.add(new HostAndPort("10.1.101.217",6383));
        hostAndPorts.add(new HostAndPort("10.1.101.217",6382));
        hostAndPorts.add(new HostAndPort("10.1.101.217",6384));
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts);
        String result = (String) jedisCluster.sendCommand("hello", Protocol.Command.GET);
        System.out.println(result);
    }
}
