package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.sdk.crud.UserCrudHelper;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.rabcwr.stack.bean.dto.ForcePasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.UserInfo;
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
public class RegisterServiceDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceDelegate.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCache userCache;
    @Autowired
    private UserCrudHelper userCrudHelper;

    @Value("${rabcwr.password.salt_log_rounds}")
    private int logRounds;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public User register(@NotNull RegisterInfo registerInfo) throws ServiceException {
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
            userCrudHelper.noAdviseInsert(user);
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
    public User updatePassword(PasswordInfo passwordInfo) throws ServiceException {
        IdKey idKey = new IdKey(passwordInfo.getId());
        try {
            LOGGER.info("用户 " + passwordInfo.getId() + " 请求更改密码...");
            /*
             * 1. 验证用户是否存在。
             * 2. 验证用户的旧密码是否正确。
             * 3. 将用户的密码设置为新密码，并更新用户。
             * 4. 返回更新后的用户。
             */
            //1. 验证用户是否存在。
            if (!userCache.exists(idKey) && !userDao.exists(idKey)) {
                LOGGER.warn("指定的id " + passwordInfo.getId() + " 不存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 验证用户的旧密码是否正确。
            //获取用户的详细信息。
            User user = userCrudHelper.noAdviseGet(idKey);
            if (BCrypt.checkpw(passwordInfo.getOldPassword(), user.getPassword())) {
                LOGGER.info("用户 ID=" + user.getKey().getId() + ", 名称=" + user.getName() + " 密码验证成功，将更新密码...");
            } else {
                LOGGER.warn("用户 ID=" + user.getKey().getId() + ", 名称=" + user.getName() + " 密码验证失败，将会抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.WRONG_PASSWORD);
            }
            //3. 将用户的密码设置为新密码，并更新用户。
            user.setPassword(BCrypt.hashpw(passwordInfo.getNewPassword(), BCrypt.gensalt(logRounds)));
            userCrudHelper.update(user);
            //4. 返回更新后的用户。
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
    public User forceUpdatePassword(ForcePasswordInfo forcePasswordInfo) throws ServiceException {
        IdKey idKey = new IdKey(forcePasswordInfo.getId());
        try {
            LOGGER.info("用户 " + forcePasswordInfo.getId() + " 请求强制性更改密码...");
            /*
             * 1. 验证用户是否存在。
             * 2. 将用户的密码设置为新密码，并更新用户。
             * 3. 返回更新后的用户。
             */
            //1. 验证用户是否存在。
            if (!userCache.exists(idKey) && !userDao.exists(idKey)) {
                LOGGER.warn("指定的id " + forcePasswordInfo.getId() + " 不存在存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 将用户的密码设置为新密码，并更新用户。
            //获取用户的详细信息。
            User user = userCrudHelper.noAdviseGet(idKey);
            user.setPassword(BCrypt.hashpw(forcePasswordInfo.getNewPassword(), BCrypt.gensalt(logRounds)));
            userCrudHelper.update(user);
            //3. 返回更新后的用户。
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
    public User updateUserInfo(UserInfo userInfo) throws ServiceException {
        IdKey idKey = new IdKey(userInfo.getId());
        try {
            LOGGER.info("用户 " + userInfo.getId() + " 请求强制性更改密码...");
            /*
             * 1. 验证用户是否存在。
             * 2. 设置用户信息为指定的信息，并更新用户。
             * 3. 返回更新后的用户。
             */
            //1. 验证用户是否存在。
            if (!userCache.exists(idKey) && !userDao.exists(idKey)) {
                LOGGER.warn("指定的id " + userInfo.getId() + " 不存在存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 设置用户信息为指定的信息，并更新用户。
            //获取用户的详细信息。
            User user = userCrudHelper.noAdviseGet(idKey);
            user.setName(userInfo.getName());
            user.setEnabled(user.getEnabled());
            user.setRemark(userInfo.getRemark());
            userCrudHelper.update(user);
            //3. 返回更新后的用户。
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
    public boolean checkPassword(String userId, String password) throws ServiceException {
        IdKey idKey = new IdKey(userId);
        try {
            LOGGER.info("用户 " + userId + " 查询密码是否正确...");
            /*
             * 1. 验证用户是否存在。
             * 2. 验证用户的密码是否正确，并返回结果。
             */
            //1. 验证用户是否存在。
            if (!userCache.exists(idKey) && !userDao.exists(idKey)) {
                LOGGER.warn("指定的id " + userId + " 不存在，密码验证失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 验证用户的密码是否正确，并返回结果。
            //获取用户的详细信息。
            User user = userCrudHelper.noAdviseGet(idKey);
            if (BCrypt.checkpw(password, user.getPassword())) {
                LOGGER.info("用户 ID=" + user.getKey().getId() + ", 名称=" + user.getName() + " 密码验证成功...");
                return true;
            } else {
                LOGGER.warn("用户 ID=" + user.getKey().getId() + ", 名称=" + user.getName() + " 密码验证失败...");
                return false;
            }
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
}
