package com.dwarfeng.rabcwr.stack.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.CrudService;
import com.dwarfeng.subgrade.stack.service.PresetDeleteService;

import java.util.List;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface RoleMaintainService extends CrudService<StringIdKey, Role>, PresetDeleteService<Role> {

    String ROLE_FOR_USER = "role_for_user";
    String ENABLED_ROLE_FOR_USER = "enabled_role_for_user";

    /**
     * 添加角色与用户的关联。
     *
     * @param roleIdKey  指定的角色主键。
     * @param userIdKeys 指定的用户主键组成的列表。
     * @throws ServiceException 服务异常。
     */
    void addUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws ServiceException;

    /**
     * 添加角色与用户的关联。
     *
     * @param roleIdKey  指定的角色主键。
     * @param userIdKeys 指定的用户主键组成的列表。
     * @throws ServiceException 服务异常。
     */
    void removeUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws ServiceException;
}
