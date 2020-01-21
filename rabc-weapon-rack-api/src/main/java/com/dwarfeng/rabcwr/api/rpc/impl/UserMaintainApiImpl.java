package com.dwarfeng.rabcwr.api.rpc.impl;

import com.dwarfeng.rabcwr.api.rpc.UserMaintainApi;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMaintainApiImpl implements UserMaintainApi {

    @Autowired
    private UserMaintainService delegate;

    @Override
    @BehaviorAnalyse
    public boolean exists(StringIdKey stringIdKey) throws ServiceException {
        return delegate.exists(stringIdKey);
    }

    @Override
    @BehaviorAnalyse
    public User get(StringIdKey stringIdKey) throws ServiceException {
        return delegate.get(stringIdKey);
    }

    @Override
    @BehaviorAnalyse
    public StringIdKey insert(User user) throws ServiceException {
        return delegate.insert(user);
    }

    @Override
    @BehaviorAnalyse
    public StringIdKey update(User user) throws ServiceException {
        return delegate.update(user);
    }

    @Override
    @BehaviorAnalyse
    public void delete(StringIdKey stringIdKey) throws ServiceException {
        delegate.delete(stringIdKey);
    }
}
