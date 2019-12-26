package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.dao.UserDao;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserDaoDelegate delegate;

    @Override
    public boolean exists(IdKey key) throws DaoException {
        return delegate.exists(key);
    }

    @Override
    public User get(IdKey key) throws DaoException {
        return delegate.get(key);
    }

    @Override
    public IdKey insert(User element) throws DaoException {
        return delegate.insert(element);
    }

    @Override
    public IdKey update(User element) throws DaoException {
        return delegate.update(element);
    }

    @Override
    public void delete(IdKey key) throws DaoException {
        delegate.delete(key);
    }
}
