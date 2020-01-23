package com.dwarfeng.rabcwr.api.rpc;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

public interface PermissionMaintainApi extends Service {

    boolean exists(LongIdKey longIdKey) throws ServiceException;

    Permission get(LongIdKey longIdKey) throws ServiceException;

    LongIdKey insert(Permission permission) throws ServiceException;

    LongIdKey update(Permission permission) throws ServiceException;

    void delete(LongIdKey longIdKey) throws ServiceException;
}
