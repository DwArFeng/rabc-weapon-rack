package com.dwarfeng.rabcwr.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

import static com.dwarfeng.rabcwr.sdk.util.Constants.EXCEPTION_CODE_OFFSET;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    /**
     * 用户不存在。
     */
    public static final ServiceException.Code USER_NOT_EXISTS = new ServiceException.Code(EXCEPTION_CODE_OFFSET, "user not exists");
    /**
     * 权限表达式格式错误。
     */
    public static final ServiceException.Code PEXP_FORMAT_ERROR = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 10, "pexp format error");

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
