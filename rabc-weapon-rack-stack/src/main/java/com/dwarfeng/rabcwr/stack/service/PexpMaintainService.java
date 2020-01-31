package com.dwarfeng.rabcwr.stack.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.CrudService;
import com.dwarfeng.subgrade.stack.service.PresetDeleteService;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PexpMaintainService extends CrudService<LongIdKey, Pexp>, PresetDeleteService<Pexp> {
}
