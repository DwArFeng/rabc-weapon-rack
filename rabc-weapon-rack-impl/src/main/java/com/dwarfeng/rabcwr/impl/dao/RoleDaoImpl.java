package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.dao.RoleDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBaseDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private HibernateBaseDao<StringIdKey, HibernateStringIdKey, Role, HibernateRole> delegate;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insert(Role element) throws DaoException {
        return delegate.insert(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey update(Role element) throws DaoException {
        return delegate.update(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(StringIdKey key) throws DaoException {
        delegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(StringIdKey key) throws DaoException {
        return delegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Role get(StringIdKey key) throws DaoException {
        return delegate.get(key);
    }
}
