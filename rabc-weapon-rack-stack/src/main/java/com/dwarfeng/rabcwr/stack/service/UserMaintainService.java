package com.dwarfeng.rabcwr.stack.service;

import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.CrudService;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface UserMaintainService extends CrudService<StringIdKey, User> {
}
