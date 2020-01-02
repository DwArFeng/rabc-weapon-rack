package com.dwarfeng.rabcwr.impl.bean.service;

import com.dwarfeng.rabcwr.sdk.crud.PexpCrudHelper;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import com.dwarfeng.rabcwr.stack.service.PexpMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PexpMaintainServiceImpl implements PexpMaintainService {

    @Autowired
    private PexpCrudHelper delegate;

    @Override
    public Pexp get(GuidKey key) throws ServiceException {
        return delegate.get(key);
    }

    @Override
    public GuidKey insert(Pexp pexp) throws ServiceException {
        return delegate.insert(pexp);
    }

    @Override
    public GuidKey update(Pexp pexp) throws ServiceException {
        return delegate.update(pexp);
    }

    @Override
    public void delete(GuidKey key) throws ServiceException {
        delegate.delete(key);
    }
}
