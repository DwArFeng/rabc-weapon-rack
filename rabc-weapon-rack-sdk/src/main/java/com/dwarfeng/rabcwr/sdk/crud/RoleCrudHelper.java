package com.dwarfeng.rabcwr.sdk.crud;

import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.cache.RoleCache;
import com.dwarfeng.rabcwr.stack.dao.RoleDao;
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
public class RoleCrudHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleCrudHelper.class);

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleCache roleCache;

    @Value("${cache.timeout.entity.role}")
    private long roleTimeout;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Role get(@NotNull IdKey key) throws ServiceException {
        try {
            return noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Role noAdviseGet(IdKey key) throws CacheException, DaoException {
        if (roleCache.exists(key)) {
            LOGGER.debug("在缓存中发现了 " + key.toString() + " 对应的值，直接返回该值...");
            return roleCache.get(key);
        } else {
            LOGGER.debug("未能在缓存中发现 " + key.toString() + " 对应的值，读取数据访问层...");
            Role role = roleDao.get(key);
            LOGGER.debug("将读取到的值 " + role.toString() + " 回写到缓存中...");
            roleCache.push(key, role, roleTimeout);
            return role;
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey insert(@NotNull Role role) throws ServiceException {
        try {
            return noAdviseInsert(role);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public IdKey noAdviseInsert(Role role) throws CacheException, DaoException {
        if (roleCache.exists(role.getKey()) || roleDao.exists(role.getKey())) {
            LOGGER.debug("指定的实体 " + role.toString() + " 已经存在，无法插入...");
            throw new IllegalStateException("指定的实体 " + role.toString() + " 已经存在，无法插入...");
        } else {
            LOGGER.debug("将指定的实体 " + role.toString() + " 插入数据访问层中...");
            roleDao.insert(role);
            LOGGER.debug("将指定的实体 " + role.toString() + " 插入缓存中...");
            roleCache.push(role.getKey(), role, roleTimeout);
            return role.getKey();
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey update(@NotNull Role role) throws ServiceException {
        try {
            return noAdviseUpdate(role);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public IdKey noAdviseUpdate(Role role) throws CacheException, DaoException {
        if (!roleCache.exists(role.getKey()) && !roleDao.exists(role.getKey())) {
            LOGGER.debug("指定的实体 " + role.toString() + " 已经存在，无法更新...");
            throw new IllegalStateException("指定的实体 " + role.toString() + " 已经存在，无法更新...");
        } else {
            LOGGER.debug("将指定的实体 " + role.toString() + " 插入数据访问层中...");
            roleDao.update(role);
            LOGGER.debug("将指定的实体 " + role.toString() + " 插入缓存中...");
            roleCache.push(role.getKey(), role, roleTimeout);
            return role.getKey();
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull IdKey key) throws ServiceException {
        try {
            noAdviseDelete(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void noAdviseDelete(IdKey key) throws CacheException, DaoException {
        if (!roleCache.exists(key) && !roleDao.exists(key)) {
            LOGGER.debug("指定的键 " + key.toString() + " 不存在，无法删除...");
            throw new IllegalStateException("指定的键 " + key.toString() + " 不存在，无法删除...");
        } else {
            LOGGER.debug("清除实体 " + key.toString() + " 对应的缓存...");
            roleCache.delete(key);
            LOGGER.debug("将指定的Role从数据访问层中删除...");
            roleDao.delete(key);
        }
    }
}
