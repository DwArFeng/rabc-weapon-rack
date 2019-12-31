package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePexp;
import com.dwarfeng.rabcwr.impl.bean.key.HibernateGuidKey;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
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
public class PexpDaoDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(PexpDaoDelegate.class);

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
        return Objects.nonNull(template.get(HibernatePexp.class, hibernateGuidKey));
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Pexp get(GuidKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的Pexp " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的GuidKey " + key.toString() + " 不存在");
            }

            HibernateGuidKey hibernateGuidKey = mapper.map(key, HibernateGuidKey.class);
            HibernatePexp hibernatePexp = template.get(HibernatePexp.class, hibernateGuidKey);
            return mapper.map(hibernatePexp, Pexp.class);
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey insert(@NotNull Pexp pexp) throws DaoException {
        try {
            if (internalExists(pexp.getKey())) {
                LOGGER.warn("指定的Pexp " + pexp.toString() + " 已经存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的Pexp " + pexp.toString() + " 已经存在");
            }

            HibernatePexp hibernatePexp = mapper.map(pexp, HibernatePexp.class);
            template.save(hibernatePexp);
            template.flush();
            return pexp.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey update(@NotNull Pexp pexp) throws DaoException {
        try {
            if (!internalExists(pexp.getKey())) {
                LOGGER.warn("指定的Pexp " + pexp.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的Pexp " + pexp.toString() + " 不存在");
            }

            HibernateGuidKey hibernateGuidKey = mapper.map(pexp.getKey(), HibernateGuidKey.class);
            HibernatePexp hibernatePexp = template.get(HibernatePexp.class, hibernateGuidKey);
            assert hibernatePexp != null;
            mapper.map(pexp, hibernatePexp);
            template.update(hibernatePexp);
            template.flush();
            return pexp.getKey();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull GuidKey key) throws DaoException {
        try {
            if (!internalExists(key)) {
                LOGGER.warn("指定的Pexp " + key.toString() + " 不存在, 将抛出异常...");
                throw new IllegalArgumentException("指定的GuidKey " + key.toString() + " 不存在");
            }

            HibernateGuidKey hibernateGuidKey = mapper.map(key, HibernateGuidKey.class);
            HibernatePexp hibernatePexp = template.get(HibernatePexp.class, hibernateGuidKey);
            assert hibernatePexp != null;
            template.delete(hibernatePexp);
            template.flush();
        } catch (Exception e) {
            throw new DaoException("数据访问发生异常", e);
        }
    }

}
