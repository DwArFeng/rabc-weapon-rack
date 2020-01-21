package com.dwarfeng.rabcwr.api.rpc;

import com.dwarfeng.rabcwr.stack.bean.dto.ForcePasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.UserInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

public interface RegisterApi extends Service {

    /**
     * 注册用户。
     *
     * @return 注册成功后返回的用户。
     * @throws ServiceException 服务异常。
     */
    User register(RegisterInfo registerInfo) throws ServiceException;

    /**
     * 更新用户密码。
     *
     * @param passwordInfo 用户的密码信息。
     * @return 更改密码成功后返回的用户。
     * @throws ServiceException 服务异常。
     */
    User updatePassword(PasswordInfo passwordInfo) throws ServiceException;

    /**
     * 强制更新用户密码。
     *
     * @param forcePasswordInfo 用户的强制更换密码信息。
     * @return 更改密码成功后返回的用户。
     * @throws ServiceException 服务异常。
     */
    User forceUpdatePassword(ForcePasswordInfo forcePasswordInfo) throws ServiceException;

    /**
     * 更新用户信息。
     *
     * @param userInfo 指定的信息。
     * @return 更新用户信息之后返回的用户。
     * @throws ServiceException 服务异常。
     */
    User updateUserInfo(UserInfo userInfo) throws ServiceException;

    /**
     * 判断指定的用户密码是否正确。
     *
     * @param userId   指定的用户id。
     * @param password 用户的密码。
     * @return 用户的密码是否正确。
     * @throws ServiceException 服务异常。
     */
    boolean checkPassword(String userId, String password) throws ServiceException;
}
