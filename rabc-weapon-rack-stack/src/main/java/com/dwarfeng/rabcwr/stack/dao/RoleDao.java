package com.dwarfeng.rabcwr.stack.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.subgrade.stack.exception.DaoException;

import java.util.List;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface RoleDao extends BatchBaseDao<StringIdKey, Role>, PresetLookupDao<Role> {

    /**
     * 添加角色与用户的关联。
     *
     * @param roleIdKey  指定的角色主键。
     * @param userIdKeys 指定的用户主键组成的列表。
     * @throws DaoException 数据访问层异常。
     */
    void addUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws DaoException;

    /**
     * 添加角色与用户的关联。
     *
     * @param roleIdKey  指定的角色主键。
     * @param userIdKeys 指定的用户主键组成的列表。
     * @throws DaoException 数据访问层异常。
     */
    void removeUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws DaoException;
}
