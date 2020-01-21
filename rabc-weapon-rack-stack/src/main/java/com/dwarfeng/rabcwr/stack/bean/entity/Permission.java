package com.dwarfeng.rabcwr.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

public class Permission implements Entity<LongIdKey> {

    private static final long serialVersionUID = -1534203776335886L;

    private LongIdKey key;
    private String content;
    private String remark;

    public Permission() {
    }

    public Permission(LongIdKey key, String content, String remark) {
        this.key = key;
        this.content = content;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
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
        return "Permission{" +
                "key=" + key +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
