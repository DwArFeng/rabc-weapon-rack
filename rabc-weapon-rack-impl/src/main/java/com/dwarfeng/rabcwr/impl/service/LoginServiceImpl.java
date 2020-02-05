package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.rabcwr.stack.bean.entity.LoginState;
import com.dwarfeng.rabcwr.stack.cache.LoginStateCache;
import com.dwarfeng.rabcwr.stack.service.LoginService;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.ENTITY_NOT_EXIST;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginStateCache loginStateCache;
    @Autowired
    private KeyFetcher<LongIdKey> keyFetcher;

    @Autowired
    private ServiceExceptionMapper sem;

    @Value("${rabcwr.login.expire}")
    private long expireTimeout;
    @Value("${rabcwr.login.timeout_factor}")
    private double expireTimeoutFactor;

    @Override
    public LoginState login(StringIdKey userKey, String password) throws ServiceException {
        try {
            if (!registerService.checkPassword(userKey.getStringId(), password)) {
                throw new ServiceException(ServiceExceptionCodes.WRONG_PASSWORD);
            }
            LoginState loginState = new LoginState(
                    keyFetcher.fetchKey(),
                    userKey,
                    System.currentTimeMillis() + expireTimeout
            );
            long timeout = (long) (expireTimeout * expireTimeoutFactor);
            loginStateCache.push(loginState, timeout);
            return loginState;
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void logout(LongIdKey idKey) throws ServiceException {
        try {
            if (loginStateCache.exists(idKey)) {
                loginStateCache.delete(idKey);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("登出时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public boolean isLogin(LongIdKey idKey) throws ServiceException {
        try {
            if (!loginStateCache.exists(idKey)) return false;
            LoginState loginState = loginStateCache.get(idKey);
            return loginState.getExpireDate() >= System.currentTimeMillis();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断是否登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public LoginState getLoginState(LongIdKey idKey) throws ServiceException {
        try {
            if (!loginStateCache.exists(idKey)) throw new ServiceException(ENTITY_NOT_EXIST);
            return loginStateCache.get(idKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断是否登录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public LoginState postpone(LongIdKey idKey) throws ServiceException {
        try {
            if (!loginStateCache.exists(idKey)) {
                throw new ServiceException(ENTITY_NOT_EXIST);
            }
            LoginState loginState = loginStateCache.get(idKey);
            if (loginState.getExpireDate() < System.currentTimeMillis()) {
                throw new ServiceException(ServiceExceptionCodes.ALREADY_EXPIRED);
            }
            loginState.setExpireDate(System.currentTimeMillis() + expireTimeout);
            long timeout = (long) (expireTimeout * expireTimeoutFactor);
            loginStateCache.push(loginState, timeout);
            return loginState;
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("延长超时时间时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
