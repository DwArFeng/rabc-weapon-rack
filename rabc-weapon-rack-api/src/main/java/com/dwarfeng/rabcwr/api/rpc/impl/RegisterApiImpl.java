package com.dwarfeng.rabcwr.api.rpc.impl;

import com.dwarfeng.rabcwr.api.rpc.RegisterApi;
import com.dwarfeng.rabcwr.stack.bean.dto.ForcePasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.UserInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RegisterApiImpl implements RegisterApi {

    @Autowired
    @Qualifier("registerService")
    private RegisterService service;

    @Override
    @BehaviorAnalyse
    public User register(RegisterInfo registerInfo) throws ServiceException {
        return service.register(registerInfo);
    }

    @Override
    @BehaviorAnalyse
    public User updatePassword(PasswordInfo passwordInfo) throws ServiceException {
        return service.updatePassword(passwordInfo);
    }

    @Override
    @BehaviorAnalyse
    public User forceUpdatePassword(ForcePasswordInfo forcePasswordInfo) throws ServiceException {
        return service.forceUpdatePassword(forcePasswordInfo);
    }

    @Override
    @BehaviorAnalyse
    public User updateUserInfo(UserInfo userInfo) throws ServiceException {
        return service.updateUserInfo(userInfo);
    }

    @Override
    @BehaviorAnalyse
    public boolean checkPassword(String userId, String password) throws ServiceException {
        return service.checkPassword(userId, password);
    }
}
