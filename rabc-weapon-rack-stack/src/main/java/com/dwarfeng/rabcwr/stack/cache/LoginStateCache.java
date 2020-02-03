package com.dwarfeng.rabcwr.stack.cache;

import com.dwarfeng.rabcwr.stack.bean.entity.LoginState;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 登录状态缓存。
 *
 * @author DwArFeng
 * @since 0.1.0-alpha
 */
public interface LoginStateCache extends BatchBaseCache<LongIdKey, LoginState> {
}
