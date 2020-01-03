package com.dwarfeng.rabcwr.api.dubbo.api.impl;

import com.dwarfeng.rabcwr.api.dubbo.api.RegisterApi;
import com.dwarfeng.rabcwr.stack.bean.dto.ForcePasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.UserInfo;
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

    @Override
    public User updatePassword(PasswordInfo passwordInfo) throws ServiceException {
        return delegate.updatePassword(passwordInfo);
    }

    @Override
    public User forceUpdatePassword(ForcePasswordInfo forcePasswordInfo) throws ServiceException {
        return delegate.forcePasswordInfo(forcePasswordInfo);
    }

    @Override
    public User updateUserInfo(UserInfo userInfo) throws ServiceException {
        return delegate.updateUserInfo(userInfo);
    }

    @Override
    public boolean checkPassword(String userId, String password) throws ServiceException {
        return delegate.checkPassword(userId, password);
    }
}
