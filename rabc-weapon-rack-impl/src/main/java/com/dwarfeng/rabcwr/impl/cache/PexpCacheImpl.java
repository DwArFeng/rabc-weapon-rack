package com.dwarfeng.rabcwr.impl.cache;

import com.dwarfeng.rabcwr.impl.bean.entity.FastJsonPexp;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.cache.PexpCache;
import com.dwarfeng.subgrade.impl.cache.RedisStringJsonBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PexpCacheImpl implements PexpCache {

    @Autowired
    private RedisStringJsonBaseCache<LongIdKey, Pexp, FastJsonPexp> delegate;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(LongIdKey key) throws CacheException {
        return delegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Pexp get(LongIdKey key) throws CacheException {
        return delegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void push(LongIdKey key, Pexp value, long timeout) throws CacheException {
        delegate.push(key, value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(LongIdKey key) throws CacheException {
        delegate.delete(key);
    }
}
