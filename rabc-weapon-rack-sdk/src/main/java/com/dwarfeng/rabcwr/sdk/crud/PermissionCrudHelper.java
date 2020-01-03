package com.dwarfeng.rabcwr.sdk.crud;

import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.cache.PermissionCache;
import com.dwarfeng.rabcwr.stack.dao.PermissionDao;
import com.dwarfeng.rabcwr.stack.exception.CacheException;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Component
public class PermissionCrudHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionCrudHelper.class);

    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private PermissionCache permissionCache;

    @Value("${cache.timeout.entity.permission}")
    private long permissionTimeout;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Permission get(@NotNull GuidKey key) throws ServiceException {
        try {
            return noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Permission noAdviseGet(GuidKey key) throws CacheException, DaoException {
        if (permissionCache.exists(key)) {
            LOGGER.debug("在缓存中发现了 " + key.toString() + " 对应的值，直接返回该值...");
            return permissionCache.get(key);
        } else {
            LOGGER.debug("未能在缓存中发现 " + key.toString() + " 对应的值，读取数据访问层...");
            Permission permission = permissionDao.get(key);
            LOGGER.debug("将读取到的值 " + permission.toString() + " 回写到缓存中...");
            permissionCache.push(key, permission, permissionTimeout);
            return permission;
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey insert(@NotNull Permission permission) throws ServiceException {
        try {
            return noAdviseInsert(permission);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public GuidKey noAdviseInsert(Permission permission) throws CacheException, DaoException {
        if (permissionCache.exists(permission.getKey()) || permissionDao.exists(permission.getKey())) {
            LOGGER.debug("指定的实体 " + permission.toString() + " 已经存在，无法插入...");
            throw new IllegalStateException("指定的实体 " + permission.toString() + " 已经存在，无法插入...");
        } else {
            LOGGER.debug("将指定的实体 " + permission.toString() + " 插入数据访问层中...");
            permissionDao.insert(permission);
            LOGGER.debug("将指定的实体 " + permission.toString() + " 插入缓存中...");
            permissionCache.push(permission.getKey(), permission, permissionTimeout);
            return permission.getKey();
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey update(@NotNull Permission permission) throws ServiceException {
        try {
            return noAdviseUpdate(permission);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public GuidKey noAdviseUpdate(Permission permission) throws CacheException, DaoException {
        if (!permissionCache.exists(permission.getKey()) && !permissionDao.exists(permission.getKey())) {
            LOGGER.debug("指定的实体 " + permission.toString() + " 已经存在，无法更新...");
            throw new IllegalStateException("指定的实体 " + permission.toString() + " 已经存在，无法更新...");
        } else {
            LOGGER.debug("将指定的实体 " + permission.toString() + " 插入数据访问层中...");
            permissionDao.update(permission);
            LOGGER.debug("将指定的实体 " + permission.toString() + " 插入缓存中...");
            permissionCache.push(permission.getKey(), permission, permissionTimeout);
            return permission.getKey();
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull GuidKey key) throws ServiceException {
        try {
            noAdviseDelete(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void noAdviseDelete(GuidKey key) throws CacheException, DaoException {
        if (!permissionCache.exists(key) && !permissionDao.exists(key)) {
            LOGGER.debug("指定的键 " + key.toString() + " 不存在，无法删除...");
            throw new IllegalStateException("指定的键 " + key.toString() + " 不存在，无法删除...");
        } else {
            LOGGER.debug("清除实体 " + key.toString() + " 对应的缓存...");
            permissionCache.delete(key);
            LOGGER.debug("将指定的Permission从数据访问层中删除...");
            permissionDao.delete(key);
        }
    }
}
