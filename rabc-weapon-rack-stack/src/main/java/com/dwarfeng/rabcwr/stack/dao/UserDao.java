package com.dwarfeng.rabcwr.stack.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.subgrade.stack.exception.DaoException;

import java.util.List;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface UserDao extends BatchBaseDao<StringIdKey, User>, PresetLookupDao<User> {

    /**
     * 添加用户与角色的关联。
     *
     * @param userIdKey  指定的用户主键。
     * @param roleIdKeys 指定的角色主键组成的列表。
     * @throws DaoException 服务异常。
     */
    void addRoles(StringIdKey userIdKey, List<StringIdKey> roleIdKeys) throws DaoException;

    /**
     * 删除用户与角色的关联。
     *
     * @param userIdKey  指定的用户主键。
     * @param roleIdKeys 指定的角色主键组成的列表。
     * @throws DaoException 服务异常。
     */
    void deleteRoles(StringIdKey userIdKey, List<StringIdKey> roleIdKeys) throws DaoException;
}
