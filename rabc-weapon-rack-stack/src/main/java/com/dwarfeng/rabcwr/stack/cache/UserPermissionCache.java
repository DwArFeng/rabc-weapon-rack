package com.dwarfeng.rabcwr.stack.cache;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.KeyListCache;

/**
 * 用户持有权限的缓存。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface UserPermissionCache extends KeyListCache<StringIdKey, Permission> {

}
