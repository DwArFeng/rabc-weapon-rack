package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.key.HibernateIdKey;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
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
public class RoleDaoDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoDelegate.class);

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
        return Objects.nonNull(template.get(HibernateRole.class, hibernateIdKey));
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Role get(IdKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的Role " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的IdKey " + key.toString() + " 不存在");
            }

            HibernateIdKey hibernateIdKey = mapper.map(key, HibernateIdKey.class);
            HibernateRole hibernateRole = template.get(HibernateRole.class, hibernateIdKey);
            return mapper.map(hibernateRole, Role.class);
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey insert(@NotNull Role role) throws DaoException {
        try {
            if (internalExists(role.getKey())) {
                LOGGER.warn("指定的Role " + role.toString() + " 已经存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的Role " + role.toString() + " 已经存在");
            }

            HibernateRole hibernateRole = mapper.map(role, HibernateRole.class);
            template.save(hibernateRole);
            template.flush();
            return role.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey update(@NotNull Role role) throws DaoException {
        try {
            if (!internalExists(role.getKey())) {
                LOGGER.warn("指定的Role " + role.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的Role " + role.toString() + " 不存在");
            }

            HibernateIdKey hibernateIdKey = mapper.map(role.getKey(), HibernateIdKey.class);
            HibernateRole hibernateRole = template.get(HibernateRole.class, hibernateIdKey);
            assert hibernateRole != null;
            mapper.map(role, hibernateRole);
            template.update(hibernateRole);
            template.flush();
            return role.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull IdKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的Role " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的IdKey " + key.toString() + " 不存在");
            }

            HibernateIdKey hibernateIdKey = mapper.map(key, HibernateIdKey.class);
            HibernateRole hibernateRole = template.get(HibernateRole.class, hibernateIdKey);
            assert hibernateRole != null;
            template.delete(hibernateRole);
            template.flush();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

}
