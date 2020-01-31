package com.dwarfeng.rabcwr.stack.cache;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PermissionCache extends BatchBaseCache<LongIdKey, Permission> {
}
