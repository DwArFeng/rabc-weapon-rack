package com.dwarfeng.rabcwr.api.dubbo.api;

import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import com.dwarfeng.rabcwr.stack.service.Service;

public interface RegisterApi extends Service {

    /**
     * 注册用户。
     *
     * @return 注册成功后返回的用户。
     * @throws ServiceException 服务异常。
     */
    User register(RegisterInfo registerInfo) throws ServiceException;
}
