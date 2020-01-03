package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.sdk.crud.UserCrudHelper;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.cache.UserCache;
import com.dwarfeng.rabcwr.stack.dao.UserDao;
import com.dwarfeng.rabcwr.stack.exception.CacheException;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Validated
public class UserMaintainServiceDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMaintainServiceDelegate.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCache userCache;
    @Autowired
    private UserCrudHelper helper;

    @Value("${rabcwr.password.salt_log_rounds}")
    private int logRounds;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public User get(@NotNull IdKey key) throws ServiceException {
        try {
            return helper.noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public User register(RegisterInfo registerInfo) throws ServiceException {
        LOGGER.info("注册新用户 ID=" + registerInfo.getId() + ", 名称=" + registerInfo.getName() + " ...");
        IdKey idKey = new IdKey(registerInfo.getId());
        try {
            if (userCache.exists(idKey) || userDao.exists(idKey)) {
                LOGGER.warn("指定的id " + registerInfo.getId() + " 已经存在，注册失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_ALREADY_EXISTED);
            }
            String password = BCrypt.hashpw(registerInfo.getPassword(), BCrypt.gensalt(logRounds));
            User user = new User(
                    idKey,
                    registerInfo.getName(),
                    password,
                    true,
                    registerInfo.getRemark()
            );
            helper.noAdviseInsert(user);
            LOGGER.info("用户已成功注册: " + user.toString() + " ...");
            return user;
        } catch (DaoException e) {
            LOGGER.warn("持久层异常", e);
            throw new ServiceException(ServiceExceptionCodes.DAO_FAILED);
        } catch (CacheException e) {
            LOGGER.warn("缓存层异常", e);
            throw new ServiceException(ServiceExceptionCodes.CACHE_FAILED);
        } catch (RuntimeException e) {
            LOGGER.warn("运行时异常", e);
            throw new ServiceException(ServiceExceptionCodes.UNDEFINE);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey insert(@NotNull User user) throws ServiceException {
        try {
            return helper.noAdviseInsert(user);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey update(@NotNull User user) throws ServiceException {
        try {
            return helper.noAdviseUpdate(user);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull IdKey key) throws ServiceException {
        try {
            helper.noAdviseDelete(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
