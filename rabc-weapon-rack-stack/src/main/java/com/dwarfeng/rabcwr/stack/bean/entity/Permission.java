package com.dwarfeng.rabcwr.stack.bean.entity;

import com.dwarfeng.rabcwr.stack.bean.key.UuidKey;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class Permission implements Entity<UuidKey> {

    private static final long serialVersionUID = -1534203776335886L;

    private UuidKey key;
    private String remark;

    public Permission() {
    }

    public Permission(UuidKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    @Override
    public UuidKey getKey() {
        return key;
    }

    @Override
    public void setKey(UuidKey key) {
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
