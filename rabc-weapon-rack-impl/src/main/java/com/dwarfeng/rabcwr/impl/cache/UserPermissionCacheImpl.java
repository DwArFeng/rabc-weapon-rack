package com.dwarfeng.rabcwr.impl.cache;

import com.dwarfeng.rabcwr.sdk.bean.entity.FastJsonPermission;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.cache.UserPermissionCache;
import com.dwarfeng.subgrade.impl.cache.RedisKeyListCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public class UserPermissionCacheImpl implements UserPermissionCache {

    @Autowired
    private RedisKeyListCache<StringIdKey, Permission, FastJsonPermission> redisKeyListDelegate;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(StringIdKey key) throws CacheException {
        return redisKeyListDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public int size(StringIdKey key) throws CacheException {
        return redisKeyListDelegate.size(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<Permission> get(StringIdKey key) throws CacheException {
        return redisKeyListDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<Permission> get(StringIdKey key, int beginIndex, int maxEntity) throws CacheException {
        return redisKeyListDelegate.get(key, beginIndex, maxEntity);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<Permission> get(StringIdKey key, PagingInfo pagingInfo) throws CacheException {
        return redisKeyListDelegate.get(key, pagingInfo);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void set(StringIdKey key, Collection<Permission> entities, long timeout) throws CacheException {
        redisKeyListDelegate.set(key, entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void leftPush(StringIdKey key, Collection<Permission> entities, long timeout) throws CacheException {
        redisKeyListDelegate.leftPush(key, entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void rightPush(StringIdKey key, Collection<Permission> entities, long timeout) throws CacheException {
        redisKeyListDelegate.rightPush(key, entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(StringIdKey key) throws CacheException {
        redisKeyListDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void clear() throws CacheException {
        redisKeyListDelegate.clear();
    }
}
