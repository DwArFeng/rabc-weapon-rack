package com.dwarfeng.rabcwr.api.integration.subgrade;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.service.PermissionLookupService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.PermissionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限处理器的实现。
 *
 * @author DwArFeng
 * @since 0.1.1-alpha
 */
@Component
public class PermissionHandlerImpl implements PermissionHandler {

    @Autowired
    private PermissionLookupService service;

    @Override
    public boolean hasPermission(String userName, String permissionNode) throws HandlerException {
        try {
            List<String> ownedPermissionNodes = service.lookupPermissions(new StringIdKey(userName)).stream().map(Permission::getKey)
                    .map(StringIdKey::getStringId).collect(Collectors.toList());
            return ownedPermissionNodes.contains(permissionNode);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public boolean hasPermission(String userName, List<String> permissionNodes) throws HandlerException {
        try {
            List<String> ownedPermissionNodes = service.lookupPermissions(new StringIdKey(userName)).stream().map(Permission::getKey)
                    .map(StringIdKey::getStringId).collect(Collectors.toList());
            return ownedPermissionNodes.containsAll(permissionNodes);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public List<String> getMissingPermission(String userName, List<String> permissionNodes) throws HandlerException {
        try {
            List<String> ownedPermissionNodes = service.lookupPermissions(new StringIdKey(userName)).stream().map(Permission::getKey)
                    .map(StringIdKey::getStringId).collect(Collectors.toList());
            List<String> dejavu = new ArrayList<>(permissionNodes);
            dejavu.removeAll(ownedPermissionNodes);
            return dejavu;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
