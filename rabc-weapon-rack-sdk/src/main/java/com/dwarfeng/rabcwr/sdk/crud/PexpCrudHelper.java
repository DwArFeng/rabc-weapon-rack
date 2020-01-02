package com.dwarfeng.rabcwr.sdk.crud;

import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.cache.PexpCache;
import com.dwarfeng.rabcwr.stack.dao.PexpDao;
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
public class PexpCrudHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PexpCrudHelper.class);

    @Autowired
    private PexpDao pexpDao;
    @Autowired
    private PexpCache pexpCache;

    @Value("${cache.timeout.entity.pexp}")
    private long pexpTimeout;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Pexp get(@NotNull GuidKey key) throws ServiceException {
        try {
            return noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Pexp noAdviseGet(GuidKey key) throws CacheException, DaoException {
        if (pexpCache.exists(key)) {
            LOGGER.debug("在缓存中发现了 " + key.toString() + " 对应的值，直接返回该值...");
            return pexpCache.get(key);
        } else {
            LOGGER.debug("未能在缓存中发现 " + key.toString() + " 对应的值，读取数据访问层...");
            Pexp pexp = pexpDao.get(key);
            LOGGER.debug("将读取到的值 " + pexp.toString() + " 回写到缓存中...");
            pexpCache.push(key, pexp, pexpTimeout);
            return pexp;
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey insert(@NotNull Pexp pexp) throws ServiceException {
        try {
            return noAdviseInsert(pexp);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public GuidKey noAdviseInsert(Pexp pexp) throws CacheException, DaoException {
        if (pexpCache.exists(pexp.getKey()) || pexpDao.exists(pexp.getKey())) {
            LOGGER.debug("指定的实体 " + pexp.toString() + " 已经存在，无法插入...");
            throw new IllegalStateException("指定的实体 " + pexp.toString() + " 已经存在，无法插入...");
        } else {
            LOGGER.debug("将指定的实体 " + pexp.toString() + " 插入数据访问层中...");
            pexpDao.insert(pexp);
            LOGGER.debug("将指定的实体 " + pexp.toString() + " 插入缓存中...");
            pexpCache.push(pexp.getKey(), pexp, pexpTimeout);
            return pexp.getKey();
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey update(@NotNull Pexp pexp) throws ServiceException {
        try {
            return noAdviseUpdate(pexp);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public GuidKey noAdviseUpdate(Pexp pexp) throws CacheException, DaoException {
        if (!pexpCache.exists(pexp.getKey()) && !pexpDao.exists(pexp.getKey())) {
            LOGGER.debug("指定的实体 " + pexp.toString() + " 已经存在，无法更新...");
            throw new IllegalStateException("指定的实体 " + pexp.toString() + " 已经存在，无法更新...");
        } else {
            LOGGER.debug("将指定的实体 " + pexp.toString() + " 插入数据访问层中...");
            pexpDao.update(pexp);
            LOGGER.debug("将指定的实体 " + pexp.toString() + " 插入缓存中...");
            pexpCache.push(pexp.getKey(), pexp, pexpTimeout);
            return pexp.getKey();
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
        if (!pexpCache.exists(key) && !pexpDao.exists(key)) {
            LOGGER.debug("指定的键 " + key.toString() + " 不存在，无法删除...");
            throw new IllegalStateException("指定的键 " + key.toString() + " 不存在，无法删除...");
        } else {
            LOGGER.debug("清除实体 " + key.toString() + " 对应的子项缓存...");
            LOGGER.debug("将指定的Pexp从缓存中删除...");
            pexpCache.delete(key);
            LOGGER.debug("将指定的Pexp从数据访问层中删除...");
            pexpDao.delete(key);
        }
    }
}
