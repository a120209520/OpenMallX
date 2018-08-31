package org.ppl.mall.test.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	//@Test
	//Jedis基本测试
	public void testJedis() throws Exception {
		Jedis jedis = new Jedis("192.168.25.133", 6379);
		jedis.set("jedis1", "xyz");
		String str = jedis.get("jedis1");
		System.out.println(str);
		jedis.close();
	}
	
	//@Test
	//Jedis连接池
	public void testJedisPool() throws Exception {
		JedisPool pool = new JedisPool("192.168.25.133", 6379);
		Jedis jedis = pool.getResource();
		jedis.set("jedis2", "pool");
		String str = jedis.get("jedis2");
		System.out.println(str);
		//一定要关闭，否则连接池无法回收资源
		jedis.close();
	}
	
	@Test
	//Jedis集群
	public void testJedisCluster() throws Exception {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.133", 7001));
		nodes.add(new HostAndPort("192.168.25.133", 7002));
		nodes.add(new HostAndPort("192.168.25.133", 7003));
		nodes.add(new HostAndPort("192.168.25.133", 7004));
		nodes.add(new HostAndPort("192.168.25.133", 7005));
		nodes.add(new HostAndPort("192.168.25.133", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("cluster1","hello redis");
		cluster.close();
	}
}
