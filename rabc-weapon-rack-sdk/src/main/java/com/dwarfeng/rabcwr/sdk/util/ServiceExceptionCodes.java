package com.dwarfeng.rabcwr.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    /**
     * 用户已经存在。
     */
    public static final ServiceException.Code USER_ALREADY_EXISTED = new ServiceException.Code(500, "user already existed");
    /**
     * 用户不存在。
     */
    public static final ServiceException.Code USER_NOT_EXISTS = new ServiceException.Code(510, "user not exists");
    /**
     * 密码错误。
     */
    public static final ServiceException.Code WRONG_PASSWORD = new ServiceException.Code(520, "wrong password");
    /**
     * 权限表达式格式错误。
     */
    public static final ServiceException.Code PEXP_FORMAT_ERROR = new ServiceException.Code(530, "pexp format error");


    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
