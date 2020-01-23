package com.dwarfeng.rabcwr.api.rpc.impl;

import com.dwarfeng.rabcwr.api.rpc.RoleMaintainApi;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.service.RoleMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMaintainApiImpl implements RoleMaintainApi {

    @Autowired
    private RoleMaintainService delegate;

    @Override
    @BehaviorAnalyse
    public boolean exists(StringIdKey stringIdKey) throws ServiceException {
        return delegate.exists(stringIdKey);
    }

    @Override
    @BehaviorAnalyse
    public Role get(StringIdKey stringIdKey) throws ServiceException {
        return delegate.get(stringIdKey);
    }

    @Override
    @BehaviorAnalyse
    public StringIdKey insert(Role role) throws ServiceException {
        return delegate.insert(role);
    }

    @Override
    @BehaviorAnalyse
    public StringIdKey update(Role role) throws ServiceException {
        return delegate.update(role);
    }

    @Override
    @BehaviorAnalyse
    public void delete(StringIdKey stringIdKey) throws ServiceException {
        delegate.delete(stringIdKey);
    }
}
