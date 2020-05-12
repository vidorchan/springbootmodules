package com.vidor.Cache;

import com.vidor.config.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//    private RedisTemplate redisTemplate;--有问题的实现
    private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间

    private RedisTemplate<Object,Object> redisTemplate = ApplicationContextHolder.getBean("mybatisRedisTemplate");

    private String id; // cache instance id

//    public RedisCache() {
//    }

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    //mybatis缓存操作对象的标识符。一个mapper对应一个mybatis的缓存操作对象。
    @Override
    public String getId() {
        return id;
    }

    //将查询结果塞入缓存
    @Override
    public void putObject(Object key, Object value) {
//        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForValue().set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
        logger.info("Put query result to Redis");

    }

    //从缓存中获取被缓存的查询结果。
    @Override
    public Object getObject(Object key) {
//        RedisTemplate redisTemplate = getRedisTemplate();
        logger.info("Get query result from Redis");
        return redisTemplate.opsForValue().get(key);
    }

    //从缓存中删除对应的key、value。
    @Override
    public Object removeObject(Object key) {
//        RedisTemplate redisTemplate = getRedisTemplate();
        logger.info("Delete query result in Redis");
        return redisTemplate.delete(key);
    }

    //发生更新时，清除缓存。
    @Override
    public void clear() {
//        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute(
                (RedisCallback)  redisConnection -> {
            redisConnection.flushDb();
            return null;
        });
        logger.info("Clear all the cached query result from redis");
    }

    //可选实现。返回缓存的数量。
    @Override
    public int getSize() {
        Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.dbSize();
            }
        });
        return size.intValue();
    }

    //可选实现。用于实现原子性的缓存操作。
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

//    private RedisTemplate getRedisTemplate() {
//        if (redisTemplate == null) {
//            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
//        }
//        return redisTemplate;
//    }
}
