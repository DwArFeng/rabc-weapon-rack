package com.dwarfeng.rabcwr.sdk.util;

/**
 * 约束类。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public final class Constraints {

    /**
     * ID的长度约束。
     */
    public static final int LENGTH_ID = 100;
    /**
     * 名称的长度约束。
     */
    public static final int LENGTH_NAME = 50;
    /**
     * 备注的长度约束。
     */
    public static final int LENGTH_REMARK = 100;
    /**
     * 内容的长度约束。
     */
    public static final int LENGTH_CONTENT = 150;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}
