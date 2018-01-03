package org.cora.dao;

import org.cora.constant.GlobalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Repository
public class RedisDao {
    @Autowired
    JedisPool jedisPool;

    public String get(String redisKey) {
        Jedis jedis = null;
        String redisValue = null;

        try {
            jedis = jedisPool.getResource();
            redisValue = jedis.get(redisKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return redisValue;
    }

    public Boolean set(String redisKey, String redisValue) {
        Jedis jedis = null;
        String result = null;

        try {
            jedis = jedisPool.getResource();
            result = jedis.set(redisKey, redisValue);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return GlobalConstant.OK.equals(result);
    }

    public Boolean setex(String redisKey, String redisValue, int exSeconds) {
        Jedis jedis = null;
        String result = null;

        try {
            jedis = jedisPool.getResource();
            result = jedis.setex(redisKey, exSeconds, redisValue);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return GlobalConstant.OK.equals(result);
    }

    public Boolean del(String redisKey) {
        Jedis jedis = null;
        Long updated = -1L;

        try {
            jedis = jedisPool.getResource();
            updated = jedis.del(redisKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return updated >= 0;
    }

    public Map<String, String> hgetall(String redisKey) {
        Jedis jedis = null;
        Map<String, String> redisHashMap = null;

        try {
            jedis = jedisPool.getResource();
            redisHashMap = jedis.hgetAll(redisKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return redisHashMap;
    }

    public Boolean hmset(String redisKey, Map<String, String> redisHashMap) {
        Jedis jedis = null;
        String result = null;

        try {
            jedis = jedisPool.getResource();
            result = jedis.hmset(redisKey, redisHashMap);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return GlobalConstant.OK.equals(result);
    }

    public String hget(String redisKey, String hashMapKey) {
        Jedis jedis = null;
        String hashMapValue = null;

        try {
            jedis = jedisPool.getResource();
            hashMapValue = jedis.hget(redisKey, hashMapKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return hashMapValue;
    }

    public Boolean hset(String redisKey, String hashMapKey, String hashMapValue) {
        Jedis jedis = null;
        Long updated = -1L;

        try {
            jedis = jedisPool.getResource();
            updated = jedis.hset(redisKey, hashMapKey, hashMapValue);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return updated >= 0;
    }

    public Boolean hdel(String redisKey, String hashMapKey) {
        Jedis jedis = null;
        Long updated = -1L;

        try {
            jedis = jedisPool.getResource();
            updated = jedis.hdel(redisKey, hashMapKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return updated >= 0;
    }

    public Long hincrBy(String redisKey, String hashMapKey, Long incrValue) {
        Jedis jedis = null;
        Long result = null;

        try {
            jedis = jedisPool.getResource();
            result = jedis.hincrBy(redisKey, hashMapKey, incrValue);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return result;
    }

    public Boolean expire(String redisKey, int expireSeconds) {
        Jedis jedis = null;
        Long result = -1L;

        try {
            jedis = jedisPool.getResource();
            result = jedis.expire(redisKey, expireSeconds);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return result >= 0;
    }
}

