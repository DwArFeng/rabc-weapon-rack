package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.dao.RoleDao;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleDaoDelegate delegate;

    @Override
    public boolean exists(IdKey key) throws DaoException {
        return delegate.exists(key);
    }

    @Override
    public Role get(IdKey key) throws DaoException {
        return delegate.get(key);
    }

    @Override
    public IdKey insert(Role role) throws DaoException {
        return delegate.insert(role);
    }

    @Override
    public IdKey update(Role role) throws DaoException {
        return delegate.update(role);
    }

    @Override
    public void delete(IdKey key) throws DaoException {
        delegate.delete(key);
    }
}
