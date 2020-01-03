package com.dwarfeng.rabcwr.sdk.util;


import com.dwarfeng.rabcwr.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class ServiceExceptionCodes {

    /**
     * 未定义错误代码，代表未定义的错误。
     */
    public static final ServiceException.Code UNDEFINE = new ServiceException.Code(0, "undefine");
    /**
     * 参数验证失败。
     */
    public static final ServiceException.Code VALIDATION_FAILED = new ServiceException.Code(100, "validation failed");
    /**
     * 缓存异常。
     */
    public static final ServiceException.Code CACHE_FAILED = new ServiceException.Code(110, "cache failed");
    /**
     * 数据访问层异常。
     */
    public static final ServiceException.Code DAO_FAILED = new ServiceException.Code(120, "dao failed");
    /**
     * 参数验证失败。
     */
    public static final ServiceException.Code PARAM_VALIDATION_FAILED = new ServiceException.Code(130, "param validation failed");
    /**
     * 用户已经存在。
     */
    public static final ServiceException.Code USER_ALREADY_EXISTED = new ServiceException.Code(140, "user already existed");
    /**
     * 用户不存在。
     */
    public static final ServiceException.Code USER_NOT_EXISTS = new ServiceException.Code(145, "user not exists");
    /**
     * 用户已经存在。
     */
    public static final ServiceException.Code WRONG_PASSWORD = new ServiceException.Code(150, "wrong password");
    /**
     * GUID获取失败。
     */
    public static final ServiceException.Code GUID_FETCH_FAILED = new ServiceException.Code(160, "guid fetch failed");
    /**
     * 登录实体过期。
     */
    public static final ServiceException.Code LOGIN_INSTANCE_EXCEEDED = new ServiceException.Code(170, "login instance exceeded");
    /**
     * 登录实体不存在。
     */
    public static final ServiceException.Code LOGIN_INSTANCE_NOT_EXISTS = new ServiceException.Code(171, "login instance not exists");
    /**
     * RSA加密失败。
     */
    public static final ServiceException.Code RSA_ENCRYPT_FAILED = new ServiceException.Code(180, "rsa encrypt failed");
    /**
     * RSA验证失败。
     */
    public static final ServiceException.Code RSA_VALIDATION_FAILED = new ServiceException.Code(190, "rsa validation failed");
    /**
     * 登录状态过期。
     */
    public static final ServiceException.Code LOGIN_STATE_EXPIRED = new ServiceException.Code(200, "login_state_expired");

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
