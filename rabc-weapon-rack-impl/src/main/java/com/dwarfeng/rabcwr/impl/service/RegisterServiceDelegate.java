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
    public User register(@NotNull RegisterInfo registerInfo) throws ServiceException {
        LOGGER.info("注册新用户 ID=" + registerInfo.getId() + ", 名称=" + registerInfo.getName() + " ...");
        IdKey idKey = new IdKey(registerInfo.getId());
        try {
            if (userCache.exists(idKey) || userDao.exists(idKey)) {
                LOGGER.warn("指定的id " + registerInfo.getId() + " 已经存在，注册失败，将抛出异常...");
                throw new ServiceException(ServiceExceptionCodes.UNDEFINE);
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
        } catch (CacheException | DaoException | RuntimeException e) {
            throw new ServiceException(e);
        }
    }
}
