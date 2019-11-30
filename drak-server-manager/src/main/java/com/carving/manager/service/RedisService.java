package com.carving.manager.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Li Songlin
 * @date 2019/11/14 15:36
 **/
public interface RedisService {

    /**
     * 给指定key设置缓存失效时间
     *
     * @param key
     * @param time
     * @return
     */
    boolean expire(String key, long time);

    /**
     * 过期时间
     *
     * @param key
     * @return
     */
    long getExpire(String key);

    /**
     * 判断key 存在
     *
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * 删除
     *
     * @param key
     */
    void del(String... key);

    //============================String=============================

    /**
     * 得到key对应的值
     *
     * @param key
     * @return
     */
    Object get(String key);

    boolean set(String key, Object value);

    boolean set(String key, Object value, long expire);

    long incr(String key, long n);

    long decr(String key, long n);

    //================================hash 存储对象=================================

    Object hget(String key,String item);

    Map<Object, Object> hmget(String key);

    boolean hmset(String key, Map<Object, Object> values);

    boolean hmset(String key, Map<Object, Object> values, long expire);

    boolean hset(String key, String item, Object value);

    boolean hset(String key, String item, Object value, long time);

    void hdel(String key, Object... item);

    boolean hHasKey(String key, String item);

    double hincr(String key, String item, double by);

    double hdecr(String key, String item, double by);

    //============================set=============================

    Set<Object> sGet(String key);

    boolean sHasKey(String key, Object value);

    long sSet(String key, Object... values);

    long sSet(String key, long time, Object... values);

    long sGetSetSize(String key);

    long setRemove(String key, Object... values);

    //===============================list=================================

    List<Object> lGet(String key, long start, long end);

    long lGetListSize(String key);

    Object lGetIndex(String key, long index);

    boolean lSet(String key, Object value, long time);

    boolean lSet(String key, List<Object> value);

    boolean lSet(String key, List<Object> value, long time);

    boolean lUpdateIndex(String key, long index, Object value);

    long lRemove(String key, long count, Object value);
}
