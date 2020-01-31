package com.dwarfeng.rabcwr.stack.cache;

import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface UserCache extends BatchBaseCache<StringIdKey, User> {
}
