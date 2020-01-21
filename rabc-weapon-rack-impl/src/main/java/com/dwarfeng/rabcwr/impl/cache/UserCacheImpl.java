package com.dwarfeng.rabcwr.impl.cache;

import com.dwarfeng.rabcwr.impl.bean.entity.FastJsonUser;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.cache.UserCache;
import com.dwarfeng.subgrade.impl.cache.RedisStringJsonBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserCacheImpl implements UserCache {

    @Autowired
    private RedisStringJsonBaseCache<StringIdKey, User, FastJsonUser> delegate;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(StringIdKey key) throws CacheException {
        return delegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public User get(StringIdKey key) throws CacheException {
        return delegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void push(StringIdKey key, User value, long timeout) throws CacheException {
        delegate.push(key, value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(StringIdKey key) throws CacheException {
        delegate.delete(key);
    }
}
