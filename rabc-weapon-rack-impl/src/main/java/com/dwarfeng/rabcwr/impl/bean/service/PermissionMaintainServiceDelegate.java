package com.dwarfeng.rabcwr.impl.bean.service;

import com.dwarfeng.rabcwr.sdk.crud.PermissionCrudHelper;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.cache.PermissionCache;
import com.dwarfeng.rabcwr.stack.dao.PermissionDao;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
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
public class PermissionMaintainServiceDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionMaintainServiceDelegate.class);

    @Autowired
    private PermissionCrudHelper helper;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private PermissionCache permissionCache;

    @Value("${cache.timeout.entity.permission}")
    private long permissionTimeout;

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public Permission get(@NotNull GuidKey key) throws ServiceException {
        try {
            return helper.noAdviseGet(key);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey insert(@NotNull Permission permission) throws ServiceException {
        try {
            return helper.noAdviseInsert(permission);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @TimeAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public GuidKey update(@NotNull Permission permission) throws ServiceException {
        try {
            return helper.noAdviseUpdate(permission);
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
