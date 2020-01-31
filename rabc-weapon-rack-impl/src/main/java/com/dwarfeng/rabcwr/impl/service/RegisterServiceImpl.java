package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.rabcwr.stack.bean.dto.ForcePasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.UserInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.rabcwr.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
public class RegisterServiceImpl implements RegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private ServiceExceptionMapper sem;

    @Value("${rabcwr.password.salt_log_rounds}")
    private int logRounds;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public User register(@NotNull RegisterInfo registerInfo) throws ServiceException {
        LOGGER.info("注册新用户 ID=" + registerInfo.getId() + ", 名称=" + registerInfo.getName() + " ...");
        StringIdKey stringIdKey = new StringIdKey(registerInfo.getId());
        try {
            if (userMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + registerInfo.getId() + " 已经存在，注册失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_ALREADY_EXISTED);
            }
            String password = BCrypt.hashpw(registerInfo.getPassword(), BCrypt.gensalt(logRounds));
            User user = new User(
                    stringIdKey,
                    registerInfo.getName(),
                    password,
                    true,
                    registerInfo.getRemark()
            );
            userMaintainService.insert(user);
            LOGGER.info("用户已成功注册: " + user.toString() + " ...");
            return user;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public User updatePassword(PasswordInfo passwordInfo) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(passwordInfo.getId());
        try {
            LOGGER.info("用户 " + passwordInfo.getId() + " 请求更改密码...");
            /*
             * 1. 验证用户是否存在。
             * 2. 验证用户的旧密码是否正确。
             * 3. 将用户的密码设置为新密码，并更新用户。
             * 4. 返回更新后的用户。
             */
            //1. 验证用户是否存在。
            if (!userMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + passwordInfo.getId() + " 不存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 验证用户的旧密码是否正确。
            //获取用户的详细信息。
            User user = userMaintainService.get(stringIdKey);
            if (BCrypt.checkpw(passwordInfo.getOldPassword(), user.getPassword())) {
                LOGGER.info("用户 ID=" + user.getKey().getStringId() + ", 名称=" + user.getName() + " 密码验证成功，将更新密码...");
            } else {
                LOGGER.warn("用户 ID=" + user.getKey().getStringId() + ", 名称=" + user.getName() + " 密码验证失败，将会抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.WRONG_PASSWORD);
            }
            //3. 将用户的密码设置为新密码，并更新用户。
            user.setPassword(BCrypt.hashpw(passwordInfo.getNewPassword(), BCrypt.gensalt(logRounds)));
            userMaintainService.update(user);
            //4. 返回更新后的用户。
            return user;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public User forceUpdatePassword(ForcePasswordInfo forcePasswordInfo) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(forcePasswordInfo.getId());
        try {
            LOGGER.info("用户 " + forcePasswordInfo.getId() + " 请求强制性更改密码...");
            /*
             * 1. 验证用户是否存在。
             * 2. 将用户的密码设置为新密码，并更新用户。
             * 3. 返回更新后的用户。
             */
            //1. 验证用户是否存在。
            if (!userMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + forcePasswordInfo.getId() + " 不存在存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 将用户的密码设置为新密码，并更新用户。
            //获取用户的详细信息。
            User user = userMaintainService.get(stringIdKey);
            user.setPassword(BCrypt.hashpw(forcePasswordInfo.getNewPassword(), BCrypt.gensalt(logRounds)));
            userMaintainService.update(user);
            //3. 返回更新后的用户。
            return user;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public User updateUserInfo(UserInfo userInfo) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(userInfo.getId());
        try {
            LOGGER.info("用户 " + userInfo.getId() + " 请求强制性更改密码...");
            /*
             * 1. 验证用户是否存在。
             * 2. 设置用户信息为指定的信息，并更新用户。
             * 3. 返回更新后的用户。
             */
            //1. 验证用户是否存在。
            if (!userMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + userInfo.getId() + " 不存在存在，更改密码失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 设置用户信息为指定的信息，并更新用户。
            //获取用户的详细信息。
            User user = userMaintainService.get(stringIdKey);
            user.setName(userInfo.getName());
            user.setEnabled(user.getEnabled());
            user.setRemark(userInfo.getRemark());
            userMaintainService.update(user);
            //3. 返回更新后的用户。
            return user;
        } catch (Exception e) {
            throw sem.map(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public boolean checkPassword(String userId, String password) throws ServiceException {
        StringIdKey stringIdKey = new StringIdKey(userId);
        try {
            LOGGER.info("用户 " + userId + " 查询密码是否正确...");
            /*
             * 1. 验证用户是否存在。
             * 2. 验证用户的密码是否正确，并返回结果。
             */
            //1. 验证用户是否存在。
            if (!userMaintainService.exists(stringIdKey)) {
                LOGGER.warn("指定的id " + userId + " 不存在，密码验证失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.USER_NOT_EXISTS);
            }
            //2. 验证用户的密码是否正确，并返回结果。
            //获取用户的详细信息。
            User user = userMaintainService.get(stringIdKey);
            if (BCrypt.checkpw(password, user.getPassword())) {
                LOGGER.info("用户 ID=" + user.getKey().getStringId() + ", 名称=" + user.getName() + " 密码验证成功...");
                return true;
            } else {
                LOGGER.warn("用户 ID=" + user.getKey().getStringId() + ", 名称=" + user.getName() + " 密码验证失败...");
                return false;
            }
        } catch (Exception e) {
            throw sem.map(e);
        }
    }
}
