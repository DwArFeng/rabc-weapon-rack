package com.dwarfeng.rabcwr.stack.bean.entity;

import com.dwarfeng.rabcwr.stack.bean.key.IdKey;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class Role implements Entity<IdKey> {

    private static final long serialVersionUID = -1496451428523868925L;

    private IdKey key;
    private boolean enabled;
    private String remark;

    public Role() {
    }

    public Role(IdKey key, boolean enabled, String remark) {
        this.key = key;
        this.enabled = enabled;
        this.remark = remark;
    }

    @Override
    public IdKey getKey() {
        return key;
    }

    @Override
    public void setKey(IdKey key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
