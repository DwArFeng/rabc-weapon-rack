package com.dwarfeng.rabcwr.stack.bean.entity;

import com.dwarfeng.rabcwr.stack.bean.key.UuidKey;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class Pexp implements Entity<UuidKey> {

    private static final long serialVersionUID = 8790319865873105566L;

    private UuidKey key;
    private String remark;

    public Pexp() {
    }

    public Pexp(UuidKey key, String remark) {
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
        return "Pexp{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
