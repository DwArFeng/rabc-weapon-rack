package com.dwarfeng.rabcwr.api.integration.subgrade;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.service.LoginService;
import com.dwarfeng.rabcwr.stack.service.PermissionLookupService;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.handler.LoginPermHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录权限处理器的实现。
 *
 * @author DwArFeng
 * @since 1.0.1
 */
@Component
public class LoginPermHandlerImpl implements LoginPermHandler {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private PermissionLookupService permissionLookupService;

    @Override
    public boolean checkPassword(StringIdKey userKey, String password) throws HandlerException {
        try {
            return registerService.checkPassword(userKey.getStringId(), password);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public LongIdKey login(StringIdKey userKey, String password) throws HandlerException {
        try {
            return loginService.login(userKey, password).getKey();
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void logout(LongIdKey idKey) throws HandlerException {
        try {
            loginService.logout(idKey);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public boolean isLogin(LongIdKey idKey) throws HandlerException {
        try {
            return loginService.isLogin(idKey);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void postpone(LongIdKey idKey) throws HandlerException {
        try {
            loginService.postpone(idKey);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public boolean hasPermission(LongIdKey idKey, String permissionNode) throws HandlerException {
        try {
            StringIdKey userKey = loginService.getLoginState(idKey).getUserKey();
            List<String> ownedPermissionNodes = permissionLookupService.lookupPermissions(userKey).stream().map(Permission::getKey)
                    .map(StringIdKey::getStringId).collect(Collectors.toList());
            return ownedPermissionNodes.contains(permissionNode);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public boolean hasPermission(LongIdKey idKey, List<String> permissionNodes) throws HandlerException {
        try {
            StringIdKey userKey = loginService.getLoginState(idKey).getUserKey();
            List<String> ownedPermissionNodes = permissionLookupService.lookupPermissions(userKey).stream().map(Permission::getKey)
                    .map(StringIdKey::getStringId).collect(Collectors.toList());
            return ownedPermissionNodes.containsAll(permissionNodes);
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public List<String> getMissingPermission(LongIdKey idKey, List<String> permissionNodes) throws HandlerException {
        try {
            StringIdKey userKey = loginService.getLoginState(idKey).getUserKey();
            List<String> ownedPermissionNodes = permissionLookupService.lookupPermissions(userKey).stream().map(Permission::getKey)
                    .map(StringIdKey::getStringId).collect(Collectors.toList());
            List<String> dejavu = new ArrayList<>(permissionNodes);
            dejavu.removeAll(ownedPermissionNodes);
            return dejavu;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
