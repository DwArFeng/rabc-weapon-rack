package com.dwarfeng.rabcwr.api.dubbo.api.impl;

import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.rabcwr.sdk.validation.bean.dto.ValidationForcePasswordInfo;
import com.dwarfeng.rabcwr.sdk.validation.bean.dto.ValidationPasswordInfo;
import com.dwarfeng.rabcwr.sdk.validation.bean.dto.ValidationRegisterInfo;
import com.dwarfeng.rabcwr.sdk.validation.bean.dto.ValidationUserInfo;
import com.dwarfeng.rabcwr.sdk.validation.group.Insert;
import com.dwarfeng.rabcwr.stack.bean.dto.ForcePasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.UserInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Component
public class RegisterApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterApiDelegate.class);

    @Autowired
    @Qualifier("registerService")
    private RegisterService service;

    @Autowired
    public ValidateBean validateBean;
    @Autowired
    private Mapper mapper;

    @TimeAnalyse
    public User register(RegisterInfo registerInfo) throws ServiceException {
        ValidationRegisterInfo validationRegisterInfo = mapper.map(registerInfo, ValidationRegisterInfo.class);
        try {
            validateBean.validateRegister(validationRegisterInfo);
        } catch (ConstraintViolationException e) {
            LOGGER.warn("参数非法，将抛出异常...", e);
            throw new ServiceException(ServiceExceptionCodes.PARAM_VALIDATION_FAILED);
        }
        return service.register(registerInfo);
    }

    @TimeAnalyse
    public User updatePassword(PasswordInfo passwordInfo) throws ServiceException {
        ValidationPasswordInfo validationPasswordInfo = mapper.map(passwordInfo, ValidationPasswordInfo.class);
        try {
            validateBean.validateUpdatePassword(validationPasswordInfo);
        } catch (ConstraintViolationException e) {
            LOGGER.warn("参数非法，将抛出异常...", e);
            throw new ServiceException(ServiceExceptionCodes.PARAM_VALIDATION_FAILED);
        }
        return service.updatePassword(passwordInfo);
    }

    @TimeAnalyse
    public User forcePasswordInfo(ForcePasswordInfo forcePasswordInfo) throws ServiceException {
        ValidationForcePasswordInfo validationForcePasswordInfo = mapper.map(forcePasswordInfo, ValidationForcePasswordInfo.class);
        try {
            validateBean.validForcePasswordInfo(validationForcePasswordInfo);
        } catch (ConstraintViolationException e) {
            LOGGER.warn("参数非法，将抛出异常...", e);
            throw new ServiceException(ServiceExceptionCodes.PARAM_VALIDATION_FAILED);
        }
        return service.forceUpdatePassword(forcePasswordInfo);
    }

    @TimeAnalyse
    public User updateUserInfo(UserInfo userInfo) throws ServiceException {
        ValidationUserInfo validationUserInfo = mapper.map(userInfo, ValidationUserInfo.class);
        try {
            validateBean.validUpdateUserInfo(validationUserInfo);
        } catch (ConstraintViolationException e) {
            LOGGER.warn("参数非法，将抛出异常...", e);
            throw new ServiceException(ServiceExceptionCodes.PARAM_VALIDATION_FAILED);
        }
        return service.updateUserInfo(userInfo);
    }

    @TimeAnalyse
    public boolean checkPassword(String userId, String password) throws ServiceException {
        try {
            validateBean.validCheckPassword(userId, password);
        } catch (ConstraintViolationException e) {
            LOGGER.warn("参数非法，将抛出异常...", e);
            throw new ServiceException(ServiceExceptionCodes.PARAM_VALIDATION_FAILED);
        }
        return service.checkPassword(userId, password);
    }

    @Component
    @Validated
    public static class ValidateBean {

        @Validated({Default.class})
        public void validateRegister(@Valid @NotNull ValidationRegisterInfo validationRegisterInfo) throws ConstraintViolationException {
        }

        @Validated({Default.class})
        public void validateUpdatePassword(@Valid @NotNull ValidationPasswordInfo validationPasswordInfo) throws ConstraintViolationException {
        }

        @Validated({Default.class})
        public void validForcePasswordInfo(@Valid @NotNull ValidationForcePasswordInfo validationForcePasswordInfo) {
        }

        @Validated({Default.class})
        public void validUpdateUserInfo(@Valid @NotNull ValidationUserInfo validationUserInfo) {
        }

        @Validated({Default.class})
        public void validCheckPassword(
                @NotNull(groups = {Default.class, Insert.class}) String userId,
                @NotNull(groups = {Default.class, Insert.class}) String password
        ) {
        }
    }
}
