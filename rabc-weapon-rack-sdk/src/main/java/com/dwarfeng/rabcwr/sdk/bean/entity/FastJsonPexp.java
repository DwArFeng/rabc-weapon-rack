package com.dwarfeng.rabcwr.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

public class FastJsonPexp implements Bean {

    private static final long serialVersionUID = 6913527582734758032L;

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "role_key", ordinal = 2)
    private FastJsonStringIdKey roleKey;

    @JSONField(name = "content", ordinal = 3)
    private String content;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonPexp() {
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonStringIdKey getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(FastJsonStringIdKey roleKey) {
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
        return "JsonPexp{" +
                "key=" + key +
                ", roleKey=" + roleKey +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
