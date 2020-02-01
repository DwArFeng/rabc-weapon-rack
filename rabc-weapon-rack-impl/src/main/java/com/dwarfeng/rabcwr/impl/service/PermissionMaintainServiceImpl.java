package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.cache.PermissionCache;
import com.dwarfeng.rabcwr.stack.cache.PermissionListCache;
import com.dwarfeng.rabcwr.stack.dao.PermissionDao;
import com.dwarfeng.rabcwr.stack.service.PermissionMaintainService;
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
public class PermissionMaintainServiceImpl implements PermissionMaintainService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionCache permissionCache;
    @Autowired
    private PermissionListCache permissionListCache;

    @Autowired
    private ServiceExceptionMapper sem;
    @Value("${cache.timeout.entity.permission}")
    private long permissionTimeout;
    @Value("${cache.timeout.list.permission}")
    private long permissionListTimeout;

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
        return permissionCache.exists(key) || permissionDao.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Permission get(StringIdKey key) throws ServiceException {
        try {
            if (permissionCache.exists(key)) {
                return permissionCache.get(key);
            } else {
                if (!permissionDao.exists(key)) {
                    throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
                }
                Permission permission = permissionDao.get(key);
                permissionCache.push(permission, permissionTimeout);
                return permission;
            }
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insert(Permission permission) throws ServiceException {
        try {
            if (Objects.nonNull(permission.getKey()) && internalExists(permission.getKey())) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_EXISTED);
            }

            permissionListCache.clear();

            permissionDao.insert(permission);
            permissionCache.push(permission, permissionTimeout);
            return permission.getKey();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("插入实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void update(Permission permission) throws ServiceException {
        try {
            if (Objects.nonNull(permission.getKey()) && !internalExists(permission.getKey())) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }

            permissionListCache.clear();

            permissionCache.push(permission, permissionTimeout);
            permissionDao.update(permission);
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

            permissionListCache.clear();

            internalDelete(key);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除实体时发生异常", LogLevel.WARN, sem, e);
        }
    }

    private void internalDelete(StringIdKey key) throws Exception {
        permissionDao.delete(key);
        permissionCache.delete(key);
    }

    @Override
    public PagedData<Permission> lookup() throws ServiceException {
        try {
            if (permissionListCache.exists()) {
                return PagingUtil.pagedData(permissionListCache.get());
            }
            List<Permission> lookup = permissionDao.lookup();
            permissionListCache.set(lookup, permissionTimeout);
            return PagingUtil.pagedData(lookup);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("查询全部时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public PagedData<Permission> lookup(PagingInfo pagingInfo) throws ServiceException {
        try {
            if (permissionListCache.exists()) {
                List<Permission> permissions = permissionListCache.get(pagingInfo);
                return PagingUtil.pagedData(pagingInfo, permissions.size(), permissions);
            }
            List<Permission> lookup = permissionDao.lookup();
            permissionListCache.set(lookup, permissionTimeout);
            PagingUtil.IntIndexBounds bounds = PagingUtil.intIndexBound(pagingInfo, lookup.size());
            return PagingUtil.pagedData(pagingInfo, lookup.size(), lookup.subList(bounds.getBeginIndex(), bounds.getEndIndex()));
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("查询全部时发生异常", LogLevel.WARN, sem, e);
        }
    }
}

