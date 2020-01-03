package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import com.dwarfeng.rabcwr.stack.service.UserMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMaintainServiceImpl implements UserMaintainService {

    @Autowired
    private UserMaintainServiceDelegate delegate;

    @Override
    public User get(IdKey key) throws ServiceException {
        return delegate.get(key);
    }

    @Override
    public IdKey insert(User user) throws ServiceException {
        return delegate.insert(user);
    }

    @Override
    public IdKey update(User user) throws ServiceException {
        return delegate.update(user);
    }

    @Override
    public void delete(IdKey key) throws ServiceException {
        delegate.delete(key);
    }
}
