package com.dwarfeng.rabcwr.stack.handler;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.List;
import java.util.Map;

/**
 * Pexp处理器。
 *
 * @author DwArFeng
 * @since 0.1.0-alpha
 */
public interface PexpHandler extends Handler {

    /**
     * 分析权限。
     *
     * @param pexpsMap       角色与权限表达式列表对应的映射。
     * @param allPermissions 所有权限组成的列表。
     * @return 满足权限表达式的所有权限。
     * @throws HandlerException 处理器异常。
     */
    List<Permission> analysePermissions(Map<Role, List<Pexp>> pexpsMap, List<Permission> allPermissions) throws HandlerException;
}
