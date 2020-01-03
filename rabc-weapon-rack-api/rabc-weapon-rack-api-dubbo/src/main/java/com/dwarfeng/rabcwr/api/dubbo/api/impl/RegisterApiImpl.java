package com.dwarfeng.rabcwr.api.dubbo.api.impl;

import com.dwarfeng.rabcwr.api.dubbo.api.RegisterApi;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterApiImpl implements RegisterApi {

    @Autowired
    private RegisterApiDelegate delegate;

    @Override
    public User register(RegisterInfo registerInfo) throws ServiceException {
        return delegate.register(registerInfo);
    }
}
