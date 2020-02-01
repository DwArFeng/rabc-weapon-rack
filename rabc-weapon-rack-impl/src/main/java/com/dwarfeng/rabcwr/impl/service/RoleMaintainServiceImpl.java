package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.cache.RoleCache;
import com.dwarfeng.rabcwr.stack.cache.UserPermissionCache;
import com.dwarfeng.rabcwr.stack.dao.RoleDao;
import com.dwarfeng.rabcwr.stack.service.RoleMaintainService;
import com.dwarfeng.subgrade.sdk.bean.dto.PagingUtil;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.dto.PagedData;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class RoleMaintainServiceImpl implements RoleMaintainService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleCache roleCache;
    @Autowired
    private UserPermissionCache userPermissionCache;

    @Autowired
    private ServiceExceptionMapper sem;

    @Value("${cache.timeout.entity.role}")
    private long roleTimeout;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(StringIdKey key) throws ServiceException {
        try {
            return internalExists(key);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断实体是否存在时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private boolean internalExists(StringIdKey key) throws Exception {
        return roleCache.exists(key) || roleDao.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Role get(StringIdKey key) throws ServiceException {
        try {
            if (roleCache.exists(key)) {
                return roleCache.get(key);
            } else {
                if (!roleDao.exists(key)) {
                    throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
                }
                Role role = roleDao.get(key);
                roleCache.push(role, roleTimeout);
                return role;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insert(Role role) throws ServiceException {
        try {
            if (Objects.nonNull(role.getKey()) && internalExists(role.getKey())) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_EXISTED);
            }

            userPermissionCache.clear();

            roleDao.insert(role);
            roleCache.push(role, roleTimeout);
            return role.getKey();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("插入实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void update(Role role) throws ServiceException {
        try {
            if (Objects.nonNull(role.getKey()) && !internalExists(role.getKey())) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }

            userPermissionCache.clear();

            roleCache.push(role, roleTimeout);
            roleDao.update(role);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(StringIdKey key) throws ServiceException {
        try {
            if (!internalExists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }

            userPermissionCache.clear();

            internalDelete(key);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private void internalDelete(StringIdKey key) throws Exception {
        roleDao.delete(key);
        roleCache.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public PagedData<Role> lookup(String preset, Object[] objs) throws ServiceException {
        try {
            return PagingUtil.pagedData(roleDao.lookup(preset, objs));
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("查询实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public PagedData<Role> lookup(String preset, Object[] objs, PagingInfo pagingInfo) throws ServiceException {
        try {
            return PagingUtil.pagedData(pagingInfo, roleDao.lookupCount(preset, objs), roleDao.lookup(preset, objs, pagingInfo));
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("查询实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void lookupDelete(String preset, Object[] objs) throws ServiceException {
        try {
            List<StringIdKey> longIdKeys = roleDao.lookupDelete(preset, objs);
            userPermissionCache.clear();
            for (StringIdKey longIdKey : longIdKeys) {
                internalDelete(longIdKey);
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("查询并删除实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void addUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws ServiceException {
        try {
            userPermissionCache.clear();
            roleDao.addUsers(roleIdKey, userIdKeys);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("添加角色与用户的关联时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void removeUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws ServiceException {
        try {
            userPermissionCache.clear();
            roleDao.removeUsers(roleIdKey, userIdKeys);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("添加角色与用户的关联时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
