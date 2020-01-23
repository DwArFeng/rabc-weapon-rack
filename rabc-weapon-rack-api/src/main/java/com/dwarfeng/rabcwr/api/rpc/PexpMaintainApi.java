package com.dwarfeng.rabcwr.api.rpc;

import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

public interface PexpMaintainApi extends Service {

    boolean exists(LongIdKey longIdKey) throws ServiceException;

    Pexp get(LongIdKey longIdKey) throws ServiceException;

    LongIdKey insert(Pexp pexp) throws ServiceException;

    LongIdKey update(Pexp pexp) throws ServiceException;

    void delete(LongIdKey longIdKey) throws ServiceException;
}
