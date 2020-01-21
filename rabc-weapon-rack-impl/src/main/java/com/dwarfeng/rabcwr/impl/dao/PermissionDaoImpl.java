package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePermission;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.dao.PermissionDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBaseDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private HibernateBaseDao<LongIdKey, HibernateLongIdKey, Permission, HibernatePermission> delegate;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public LongIdKey insert(Permission element) throws DaoException {
        return delegate.insert(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public LongIdKey update(Permission element) throws DaoException {
        return delegate.update(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(LongIdKey key) throws DaoException {
        delegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(LongIdKey key) throws DaoException {
        return delegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Permission get(LongIdKey key) throws DaoException {
        return delegate.get(key);
    }
}
