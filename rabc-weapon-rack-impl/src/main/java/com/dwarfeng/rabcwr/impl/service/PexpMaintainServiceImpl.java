package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.service.PexpMaintainService;
import com.dwarfeng.subgrade.impl.service.GeneralCrudService;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PexpMaintainServiceImpl implements PexpMaintainService {

    @Autowired
    private GeneralCrudService<LongIdKey, Pexp> delegate;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(LongIdKey key) throws ServiceException {
        return delegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Pexp get(LongIdKey key) throws ServiceException {
        return delegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public LongIdKey insert(Pexp element) throws ServiceException {
        return delegate.insert(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public LongIdKey update(Pexp element) throws ServiceException {
        return delegate.update(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(LongIdKey key) throws ServiceException {
        delegate.delete(key);
    }
}
