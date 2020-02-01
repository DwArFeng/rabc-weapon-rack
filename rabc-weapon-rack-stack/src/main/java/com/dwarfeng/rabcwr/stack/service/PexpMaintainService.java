package com.dwarfeng.rabcwr.stack.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.CrudService;
import com.dwarfeng.subgrade.stack.service.PresetDeleteService;

/**
 * 权限表达式维护服务。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PexpMaintainService extends CrudService<LongIdKey, Pexp>, PresetDeleteService<Pexp> {
    String PEXP_FOR_ROLE = "pexp_for_role";
    String PEXP_FOR_ROLE_SET = "pexp_for_role_set";
}
