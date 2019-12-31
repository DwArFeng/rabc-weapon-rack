package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.dao.PermissionDao;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private PermissionDaoDelegate delegate;

    @Override
    public boolean exists(GuidKey key) throws DaoException {
        return delegate.exists(key);
    }

    @Override
    public Permission get(GuidKey key) throws DaoException {
        return delegate.get(key);
    }

    @Override
    public GuidKey insert(Permission permission) throws DaoException {
        return delegate.insert(permission);
    }

    @Override
    public GuidKey update(Permission permission) throws DaoException {
        return delegate.update(permission);
    }

    @Override
    public void delete(GuidKey key) throws DaoException {
        delegate.delete(key);
    }
}
