package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.exception.DaoException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Validated
public class UserDaoDelegate {

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private Mapper mapper;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(@NotNull IdKey key) throws DaoException {
        throw new IllegalStateException("not implemented yet."); //TODO
    }

    @TimeAnalyse
    public User get(@NotNull IdKey key) throws DaoException {
        throw new IllegalStateException("not implemented yet."); //TODO
    }

    @TimeAnalyse
    public IdKey insert(@NotNull User user) throws DaoException {
        throw new IllegalStateException("not implemented yet."); //TODO
    }

    @TimeAnalyse
    public IdKey update(@NotNull User user) throws DaoException {
        throw new IllegalStateException("not implemented yet."); //TODO
    }

    @TimeAnalyse
    public void delete(@NotNull IdKey key) throws DaoException {
        throw new IllegalStateException("not implemented yet."); //TODO
    }
}
