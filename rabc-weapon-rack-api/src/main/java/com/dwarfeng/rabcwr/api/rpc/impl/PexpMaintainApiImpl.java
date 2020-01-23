package com.dwarfeng.rabcwr.api.rpc.impl;

import com.dwarfeng.rabcwr.api.rpc.PexpMaintainApi;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.service.PexpMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PexpMaintainApiImpl implements PexpMaintainApi {

    @Autowired
    private PexpMaintainService delegate;

    @Override
    @BehaviorAnalyse
    public boolean exists(LongIdKey longIdKey) throws ServiceException {
        return delegate.exists(longIdKey);
    }

    @Override
    @BehaviorAnalyse
    public Pexp get(LongIdKey longIdKey) throws ServiceException {
        return delegate.get(longIdKey);
    }

    @Override
    @BehaviorAnalyse
    public LongIdKey insert(Pexp pexp) throws ServiceException {
        return delegate.insert(pexp);
    }

    @Override
    @BehaviorAnalyse
    public LongIdKey update(Pexp pexp) throws ServiceException {
        return delegate.update(pexp);
    }

    @Override
    @BehaviorAnalyse
    public void delete(LongIdKey longIdKey) throws ServiceException {
        delegate.delete(longIdKey);
    }
}
