package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.sdk.crud.PexpCrudHelper;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
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
public class PexpMaintainServiceDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(PexpMaintainServiceDelegate.class);

    @Autowired
    private PexpCrudHelper helper;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Pexp get(@NotNull GuidKey key) throws ServiceException {
        try {
            return helper.noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey insert(@NotNull Pexp pexp) throws ServiceException {
        try {
            return helper.noAdviseInsert(pexp);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey update(@NotNull Pexp pexp) throws ServiceException {
        try {
            return helper.noAdviseUpdate(pexp);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(@NotNull GuidKey key) throws ServiceException {
        try {
            helper.noAdviseDelete(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
