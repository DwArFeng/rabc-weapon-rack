package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePermission;
import com.dwarfeng.rabcwr.impl.bean.key.HibernateGuidKey;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
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
public class PermissionDaoDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionDaoDelegate.class);

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private Mapper mapper;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(@NotNull GuidKey key) throws DaoException {
        try {
            return internalExists(key);
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    private boolean internalExists(GuidKey key) {
        HibernateGuidKey hibernateGuidKey = mapper.map(key, HibernateGuidKey.class);
        return Objects.nonNull(template.get(HibernatePermission.class, hibernateGuidKey));
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Permission get(GuidKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的Permission " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的GuidKey " + key.toString() + " 不存在");
            }

            HibernateGuidKey hibernateGuidKey = mapper.map(key, HibernateGuidKey.class);
            HibernatePermission hibernatePermission = template.get(HibernatePermission.class, hibernateGuidKey);
            return mapper.map(hibernatePermission, Permission.class);
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey insert(@NotNull Permission permission) throws DaoException {
        try {
            if (internalExists(permission.getKey())) {
                LOGGER.warn("指定的Permission " + permission.toString() + " 已经存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的Permission " + permission.toString() + " 已经存在");
            }

            HibernatePermission hibernatePermission = mapper.map(permission, HibernatePermission.class);
            template.save(hibernatePermission);
            template.flush();
            return permission.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey update(@NotNull Permission permission) throws DaoException {
        try {
            if (!internalExists(permission.getKey())) {
                LOGGER.warn("指定的Permission " + permission.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的Permission " + permission.toString() + " 不存在");
            }

            HibernateGuidKey hibernateGuidKey = mapper.map(permission.getKey(), HibernateGuidKey.class);
            HibernatePermission hibernatePermission = template.get(HibernatePermission.class, hibernateGuidKey);
            assert hibernatePermission != null;
            mapper.map(permission, hibernatePermission);
            template.update(hibernatePermission);
            template.flush();
            return permission.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull GuidKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的Permission " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的GuidKey " + key.toString() + " 不存在");
            }

            HibernateGuidKey hibernateGuidKey = mapper.map(key, HibernateGuidKey.class);
            HibernatePermission hibernatePermission = template.get(HibernatePermission.class, hibernateGuidKey);
            assert hibernatePermission != null;
            template.delete(hibernatePermission);
            template.flush();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

}
