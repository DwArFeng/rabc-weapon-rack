package com.dwarfeng.rabcwr.impl.cache;

import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.cache.RoleCache;
import com.dwarfeng.rabcwr.stack.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleCacheImpl implements RoleCache {

    @Autowired
    private RoleCacheDelegate delegate;

    @Override
    public boolean exists(IdKey key) throws CacheException {
        return delegate.exists(key);
    }

    @Override
    public Role get(IdKey key) throws CacheException {
        return delegate.get(key);
    }

    @Override
    public void push(IdKey key, Role value, long timeout) throws CacheException {
        delegate.push(key, value, timeout);
    }

    @Override
    public void delete(IdKey key) throws CacheException {
        delegate.delete(key);
    }
}
