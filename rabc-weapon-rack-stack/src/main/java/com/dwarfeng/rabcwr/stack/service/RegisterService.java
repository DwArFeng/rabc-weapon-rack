package com.dwarfeng.rabcwr.stack.service;

import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;

/**
 * 注册服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface RegisterService extends Service {

    /**
     * 注册用户。
     *
     * @return 注册成功后返回的用户。
     * @throws ServiceException 服务异常。
     */
    User register(RegisterInfo registerInfo) throws ServiceException;
}
