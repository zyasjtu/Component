package org.cora.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext-redis.xml"})
public class RedisDaoTest {
    @Autowired
    private RedisDao redisDao;

    @Test
    public void set() throws Exception {
        System.out.println(redisDao.set("redisKey", "redisValue"));
    }

    @Test
    public void get() throws Exception {
        System.out.println(redisDao.get("redisKey"));
    }

    @Test
    public void setex() throws Exception {
        System.out.println(redisDao.setex("tmp", "30", 30));
    }

    @Test
    public void del() throws Exception {
        System.out.println(redisDao.del("redisKey"));
    }

    @Test
    public void hmset() throws Exception {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("hashMapKey1", "1");
        hashMap.put("hashMapKey2", "2");
        System.out.println(redisDao.hmset("redisKey", hashMap));
    }

    @Test
    public void hgetall() throws Exception {
        System.out.println(redisDao.hgetall("redisKey"));
    }

    @Test
    public void hset() throws Exception {
        System.out.println(redisDao.hset("redisKey", "hashMapKey1", "0"));
    }

    @Test
    public void hget() throws Exception {
        System.out.println(redisDao.hget("redisKey", "hashMapKey1"));
    }

    @Test
    public void hincrBy() throws Exception {
        System.out.println(redisDao.hincrBy("redisKey", "hashMapKey2", 1L));
    }

    @Test
    public void hdel() throws Exception {
        System.out.println(redisDao.hdel("redisKey", "hashMapKey2"));
    }

    @Test
    public void expire() throws Exception {
        System.out.println(redisDao.expire("redisKey", 10));
    }
}