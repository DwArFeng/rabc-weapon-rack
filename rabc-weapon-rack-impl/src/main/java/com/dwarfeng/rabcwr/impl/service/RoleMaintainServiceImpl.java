package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import com.dwarfeng.rabcwr.stack.service.RoleMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleMaintainServiceImpl implements RoleMaintainService {

    @Autowired
    private RoleMaintainServiceDelegate delegate;

    @Override
    public Role get(IdKey key) throws ServiceException {
        return delegate.get(key);
    }

    @Override
    public IdKey insert(Role role) throws ServiceException {
        return delegate.insert(role);
    }

    @Override
    public IdKey update(Role role) throws ServiceException {
        return delegate.update(role);
    }

    @Override
    public void delete(IdKey key) throws ServiceException {
        delegate.delete(key);
    }
}
