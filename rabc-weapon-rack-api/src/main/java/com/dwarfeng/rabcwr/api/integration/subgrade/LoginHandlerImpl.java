package com.dwarfeng.rabcwr.api.integration.subgrade;

import com.dwarfeng.rabcwr.stack.service.LoginService;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录处理器的实现。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class LoginHandlerImpl implements LoginHandler {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;

    @Override
    public boolean checkPassword(StringIdKey userKey, String password) throws HandlerException {
        try {
            return registerService.checkPassword(userKey.getStringId(), password);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public LongIdKey login(StringIdKey userKey, String password) throws HandlerException {
        try {
            return loginService.login(userKey, password).getKey();
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void logout(LongIdKey idKey) throws HandlerException {
        try {
            loginService.logout(idKey);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public boolean isLogin(LongIdKey idKey) throws HandlerException {
        try {
            return loginService.isLogin(idKey);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void postpone(LongIdKey idKey) throws HandlerException {
        try {
            loginService.postpone(idKey);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
