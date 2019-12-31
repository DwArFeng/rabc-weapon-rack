package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.rabcwr.impl.bean.key.HibernateIdKey;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Component
@Validated
public class UserDaoDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoDelegate.class);

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private Mapper mapper;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(@NotNull IdKey key) throws DaoException {
        try {
            return internalExists(key);
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    private boolean internalExists(IdKey key) {
        HibernateIdKey hibernateIdKey = mapper.map(key, HibernateIdKey.class);
        return Objects.nonNull(template.get(HibernateUser.class, hibernateIdKey));
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public User get(IdKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的User " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的IdKey " + key.toString() + " 不存在");
            }

            HibernateIdKey hibernateIdKey = mapper.map(key, HibernateIdKey.class);
            HibernateUser hibernateUser = template.get(HibernateUser.class, hibernateIdKey);
            return mapper.map(hibernateUser, User.class);
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey insert(@NotNull User user) throws DaoException {
        try {
            if (internalExists(user.getKey())) {
                LOGGER.warn("指定的User " + user.toString() + " 已经存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的User " + user.toString() + " 已经存在");
            }

            HibernateUser hibernateUser = mapper.map(user, HibernateUser.class);
            template.save(hibernateUser);
            template.flush();
            return user.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey update(@NotNull User user) throws DaoException {
        try {
            if (!internalExists(user.getKey())) {
                LOGGER.warn("指定的User " + user.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的User " + user.toString() + " 不存在");
            }

            HibernateIdKey hibernateIdKey = mapper.map(user.getKey(), HibernateIdKey.class);
            HibernateUser hibernateUser = template.get(HibernateUser.class, hibernateIdKey);
            assert hibernateUser != null;
            mapper.map(user, hibernateUser);
            template.update(hibernateUser);
            template.flush();
            return user.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull IdKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的User " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的IdKey " + key.toString() + " 不存在");
            }

            HibernateIdKey hibernateIdKey = mapper.map(key, HibernateIdKey.class);
            HibernateUser hibernateUser = template.get(HibernateUser.class, hibernateIdKey);
            assert hibernateUser != null;
            template.delete(hibernateUser);
            template.flush();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

}
