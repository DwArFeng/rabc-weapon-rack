package com.dwarfeng.rabcwr.sdk.crud;

import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.cache.UserCache;
import com.dwarfeng.rabcwr.stack.dao.UserDao;
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
public class UserCrudHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCrudHelper.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCache userCache;

    @Value("${cache.timeout.entity.user}")
    private long userTimeout;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public User get(@NotNull IdKey key) throws ServiceException {
        try {
            return noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public User noAdviseGet(IdKey key) throws CacheException, DaoException {
        if (userCache.exists(key)) {
            LOGGER.debug("在缓存中发现了 " + key.toString() + " 对应的值，直接返回该值...");
            return userCache.get(key);
        } else {
            LOGGER.debug("未能在缓存中发现 " + key.toString() + " 对应的值，读取数据访问层...");
            User user = userDao.get(key);
            LOGGER.debug("将读取到的值 " + user.toString() + " 回写到缓存中...");
            userCache.push(key, user, userTimeout);
            return user;
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey insert(@NotNull User user) throws ServiceException {
        try {
            return noAdviseInsert(user);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public IdKey noAdviseInsert(User user) throws CacheException, DaoException {
        if (userCache.exists(user.getKey()) || userDao.exists(user.getKey())) {
            LOGGER.debug("指定的实体 " + user.toString() + " 已经存在，无法插入...");
            throw new IllegalStateException("指定的实体 " + user.toString() + " 已经存在，无法插入...");
        } else {
            LOGGER.debug("将指定的实体 " + user.toString() + " 插入数据访问层中...");
            userDao.insert(user);
            LOGGER.debug("将指定的实体 " + user.toString() + " 插入缓存中...");
            userCache.push(user.getKey(), user, userTimeout);
            return user.getKey();
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey update(@NotNull User user) throws ServiceException {
        try {
            return noAdviseUpdate(user);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public IdKey noAdviseUpdate(User user) throws CacheException, DaoException {
        if (!userCache.exists(user.getKey()) && !userDao.exists(user.getKey())) {
            LOGGER.debug("指定的实体 " + user.toString() + " 已经存在，无法更新...");
            throw new IllegalStateException("指定的实体 " + user.toString() + " 已经存在，无法更新...");
        } else {
            LOGGER.debug("将指定的实体 " + user.toString() + " 插入数据访问层中...");
            userDao.update(user);
            LOGGER.debug("将指定的实体 " + user.toString() + " 插入缓存中...");
            userCache.push(user.getKey(), user, userTimeout);
            return user.getKey();
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
        if (!userCache.exists(key) && !userDao.exists(key)) {
            LOGGER.debug("指定的键 " + key.toString() + " 不存在，无法删除...");
            throw new IllegalStateException("指定的键 " + key.toString() + " 不存在，无法删除...");
        } else {
            LOGGER.debug("清除实体 " + key.toString() + " 对应的子项缓存...");
            LOGGER.debug("将指定的User从缓存中删除...");
            userCache.delete(key);
            LOGGER.debug("将指定的User从数据访问层中删除...");
            userDao.delete(key);
        }
    }
}
