package com.dwarfeng.rabcwr.stack.bean.entity;

import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class Pexp implements Entity<GuidKey> {

    private static final long serialVersionUID = -7246102861329325491L;

    private GuidKey key;
    private IdKey roleKey;
    private String content;
    private String remark;

    public Pexp() {
    }

    public Pexp(GuidKey key, IdKey roleKey, String content, String remark) {
        this.key = key;
        this.roleKey = roleKey;
        this.content = content;
        this.remark = remark;
    }

    @Override
    public GuidKey getKey() {
        return key;
    }

    @Override
    public void setKey(GuidKey key) {
        this.key = key;
    }

    public IdKey getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(IdKey roleKey) {
        this.roleKey = roleKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
                ", roleKey=" + roleKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
