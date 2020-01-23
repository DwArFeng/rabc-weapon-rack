package com.dwarfeng.rabcwr.api.rpc;

import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

public interface RoleMaintainApi extends Service {

    boolean exists(StringIdKey stringIdKey) throws ServiceException;

    Role get(StringIdKey stringIdKey) throws ServiceException;

    StringIdKey insert(Role role) throws ServiceException;

    StringIdKey update(Role role) throws ServiceException;

    void delete(StringIdKey stringIdKey) throws ServiceException;
}
