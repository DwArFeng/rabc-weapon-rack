package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.sdk.crud.PermissionCrudHelper;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import com.dwarfeng.rabcwr.stack.service.PermissionMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionMaintainServiceImpl implements PermissionMaintainService {

    @Autowired
    private PermissionCrudHelper delegate;

    @Override
    public Permission get(GuidKey key) throws ServiceException {
        return delegate.get(key);
    }

    @Override
    public GuidKey insert(Permission permission) throws ServiceException {
        return delegate.insert(permission);
    }

    @Override
    public GuidKey update(Permission permission) throws ServiceException {
        return delegate.update(permission);
    }

    @Override
    public void delete(GuidKey key) throws ServiceException {
        delegate.delete(key);
    }
}
