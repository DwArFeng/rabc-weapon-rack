package com.dwarfeng.rabcwr.api.rpc;

import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

public interface UserMaintainApi extends Service {

    boolean exists(StringIdKey stringIdKey) throws ServiceException;

    User get(StringIdKey stringIdKey) throws ServiceException;

    StringIdKey insert(User user) throws ServiceException;

    StringIdKey update(User user) throws ServiceException;

    void delete(StringIdKey stringIdKey) throws ServiceException;
}
