package com.lxg.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisCacheUtils
 * @Author ph
 * @Version 1.0
 * @Description redis 工具类
 * @Date 2020/3/27 10:06
 */
@Slf4j
@Configuration
public class RedisCacheUtils<T> {

    @Autowired
    private RedisTemplate redisTemplate;


    private static final Long SUCCESS = 1L;



    /**
     * 获取锁
     * @param lockKey
     * @param requestId 上锁人id
     * @param expireTime：单位-秒
     * @return
     */
    public boolean getLock(String lockKey, String requestId, int expireTime){
        try{
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";

            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);

            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey),requestId,expireTime);

            if(SUCCESS.equals(result)){
                return true;
            }

        }catch(Exception e){
            log.error("redis上锁异常，" + e);
        }

        return false;
    }



    /**
     * 释放锁
     * @param lockKey
     * @param requestId 解锁人id
     * @return
     */
    public  boolean releaseLock(String lockKey, String requestId){

        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

            RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey),requestId);
            if(SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            log.error("redis释放锁异常："+e);
        }

        return false;
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    /**
     * 设置超时的缓存
     *
     * @param key
     * @param value
     * @param expire
     * @param <T>
     * @return
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, int expire) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, expire, TimeUnit.SECONDS);
        return operation;
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void expire(String key, Integer time) {
        expire(key, time, TimeUnit.SECONDS);
    }

    public void expire(String key, Integer time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 自定义每次新增多少（例如用于点击次数）
     * @param key
     * @param num 自增的数
     * @return
     */
    public Long increment(String key, Integer num) {
        Long increment = redisTemplate.opsForValue().increment(key, num);
        return increment;
    }


    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.leftPush(key, dataList.get(i));
            }
        }
        return listOperation;
    }

    /**
     * 队列push
     *
     * @param key
     * @param entity
     * @return
     */
    public ListOperations<String, T> pushToQueue(String key, T entity) {
        ListOperations listOperation = redisTemplate.opsForList();
        listOperation.leftPush(key, entity);
        return listOperation;
    }

    /**
     * 不阻塞pop
     *
     * @param key
     * @return
     */
    public <T> T popFromQueue(String key) {
        ListOperations listOperation = redisTemplate.opsForList();
        return (T) listOperation.rightPop(key);
    }

    /**
     * 阻塞pop
     *
     * @param key
     * @return
     */
    public <T> T bRPopFromQueue(String key) {
        ListOperations listOperation = redisTemplate.opsForList();
        return (T) listOperation.rightPop(key, 0, TimeUnit.SECONDS);
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);
        for (int i = 0; i < size; i++) {
            dataList.add((T) listOperation.rightPop(key));
        }
        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        Long size = operation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     */
    public <T> HashOperations<String, Integer, T> setCacheIntegerMap(String key, Map<Integer, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Maph
     *
     * @param key
     * @return
     */
    public <T> Map<Integer, T> getCacheIntegerMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Redis消息发布
     *
     * @param channel
     * @param message
     */
    public void publish(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }


    /**
     * 队列push
     *
     * @param key
     * @param entity
     * @return
     */
    public ListOperations<String, T> leftPushAll(String key, List<T> entity) {
        ListOperations listOperation = redisTemplate.opsForList();
        listOperation.leftPushAll(key, entity);
        return listOperation;
    }

    /**
     * 队列push
     *
     * @param key
     * @param entity
     * @return
     */
    public ListOperations<String, T> leftPush(String key, T entity) {
        ListOperations listOperation = redisTemplate.opsForList();
        listOperation.leftPush(key, entity);
        return listOperation;
    }

    /**
     * 返回list的长度
     * @param key
     * @return
     */
    public long llen(String key) {
        ListOperations listOperation = redisTemplate.opsForList();
        return listOperation.size(key);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

}
