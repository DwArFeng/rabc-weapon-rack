package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.sdk.crud.RoleCrudHelper;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Validated
public class RoleMaintainServiceDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleMaintainServiceDelegate.class);

    @Autowired
    private RoleCrudHelper helper;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Role get(@NotNull IdKey key) throws ServiceException {
        try {
            return helper.noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey insert(@NotNull Role role) throws ServiceException {
        try {
            return helper.noAdviseInsert(role);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public IdKey update(@NotNull Role role) throws ServiceException {
        try {
            return helper.noAdviseUpdate(role);
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
