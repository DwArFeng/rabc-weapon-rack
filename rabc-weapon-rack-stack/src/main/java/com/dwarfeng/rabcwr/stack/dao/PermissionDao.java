package com.dwarfeng.rabcwr.stack.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.PresetDeleteDao;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PermissionDao extends BatchBaseDao<LongIdKey, Permission>, PresetDeleteDao<LongIdKey, Permission> {
}
