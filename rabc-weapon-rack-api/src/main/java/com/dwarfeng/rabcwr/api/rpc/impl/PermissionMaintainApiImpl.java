package com.dwarfeng.rabcwr.api.rpc.impl;

import com.dwarfeng.rabcwr.api.rpc.PermissionMaintainApi;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.service.PermissionMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionMaintainApiImpl implements PermissionMaintainApi {

    @Autowired
    private PermissionMaintainService delegate;

    @Override
    @BehaviorAnalyse
    public boolean exists(LongIdKey longIdKey) throws ServiceException {
        return delegate.exists(longIdKey);
    }

    @Override
    @BehaviorAnalyse
    public Permission get(LongIdKey longIdKey) throws ServiceException {
        return delegate.get(longIdKey);
    }

    @Override
    @BehaviorAnalyse
    public LongIdKey insert(Permission permission) throws ServiceException {
        return delegate.insert(permission);
    }

    @Override
    @BehaviorAnalyse
    public LongIdKey update(Permission permission) throws ServiceException {
        return delegate.update(permission);
    }

    @Override
    @BehaviorAnalyse
    public void delete(LongIdKey longIdKey) throws ServiceException {
        delegate.delete(longIdKey);
    }
}
