package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.dao.PexpDao;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PexpDaoImpl implements PexpDao {

    @Autowired
    private PexpDaoDelegate delegate;

    @Override
    public boolean exists(GuidKey key) throws DaoException {
        return delegate.exists(key);
    }

    @Override
    public Pexp get(GuidKey key) throws DaoException {
        return delegate.get(key);
    }

    @Override
    public GuidKey insert(Pexp pexp) throws DaoException {
        return delegate.insert(pexp);
    }

    @Override
    public GuidKey update(Pexp pexp) throws DaoException {
        return delegate.update(pexp);
    }

    @Override
    public void delete(GuidKey key) throws DaoException {
        delegate.delete(key);
    }
}
