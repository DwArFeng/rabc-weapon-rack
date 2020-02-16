package com.dwarfeng.rabcwr.stack.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PermissionDao extends BatchBaseDao<StringIdKey, Permission>, PresetLookupDao<Permission>,
        EntireLookupDao<Permission> {
}
