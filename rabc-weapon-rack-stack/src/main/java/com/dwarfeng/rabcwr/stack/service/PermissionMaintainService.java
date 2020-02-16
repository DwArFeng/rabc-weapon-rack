package com.dwarfeng.rabcwr.stack.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.CrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface PermissionMaintainService extends CrudService<StringIdKey, Permission>, PresetLookupService<Permission>,
        EntireLookupService<Permission> {

    String ID_LIKE = "id_like";
}