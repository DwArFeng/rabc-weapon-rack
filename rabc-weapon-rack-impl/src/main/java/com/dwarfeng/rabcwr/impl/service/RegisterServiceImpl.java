package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.dto.ForcePasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.UserInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterServiceDelegate delegate;

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
        return delegate.forceUpdatePassword(forcePasswordInfo);
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
