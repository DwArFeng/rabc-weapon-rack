package com.dwarfeng.rabcwr.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 500;

    /**
     * 用户不存在。
     */
    public static final ServiceException.Code USER_NOT_EXISTS = new ServiceException.Code(EXCEPTION_CODE_OFFSET, "user not exists");
    /**
     * 权限表达式格式错误。
     */
    public static final ServiceException.Code PEXP_FORMAT_ERROR = new ServiceException.Code(EXCEPTION_CODE_OFFSET + 10, "pexp format error");


    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
