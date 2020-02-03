package com.dwarfeng.rabcwr.stack.service;

import com.dwarfeng.rabcwr.stack.bean.entity.LoginState;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 登录服务。
 *
 * @author DwArFeng
 * @since 0.1.0-alpha
 */
public interface LoginService extends Service {

    /**
     * 登录。
     *
     * @param userKey  指定的用户名。
     * @param password 指定的密码。
     * @return 登录状态。
     * @throws ServiceException 服务异常。
     */
    LoginState login(StringIdKey userKey, String password) throws ServiceException;

    /**
     * 登出。
     *
     * @param idKey 指定的登录状态主键。
     * @throws ServiceException 服务异常。
     */
    void logout(LongIdKey idKey) throws ServiceException;

    /**
     * 判断指定的登录状态主键是否处于有效的登录状态。
     *
     * @param idKey 指定的登录状态主键。
     * @return 指定的登录状态主键是否处于有效的登录状态。
     * @throws ServiceException 服务异常。
     */
    boolean isLogin(LongIdKey idKey) throws ServiceException;

    /**
     * 延长指定登录状态主键的超时日期。
     *
     * @param idKey 指定的登录状态主键。
     * @return 延期后的新的登录状态。
     * @throws ServiceException 服务异常。
     */
    LoginState postpone(LongIdKey idKey) throws ServiceException;
}
