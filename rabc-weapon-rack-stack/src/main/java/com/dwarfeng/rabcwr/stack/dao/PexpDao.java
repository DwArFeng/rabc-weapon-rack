package com.dwarfeng.rabcwr.stack.dao;

import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PexpDao extends BatchBaseDao<LongIdKey, Pexp>, PresetLookupDao<Pexp> {
}
