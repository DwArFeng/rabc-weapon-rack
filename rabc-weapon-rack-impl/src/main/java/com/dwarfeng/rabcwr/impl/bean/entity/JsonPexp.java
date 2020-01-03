package com.dwarfeng.rabcwr.impl.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.rabcwr.impl.bean.key.JsonGuidKey;
import com.dwarfeng.rabcwr.impl.bean.key.JsonIdKey;

import java.io.Serializable;

public class JsonPexp implements Serializable {

    private static final long serialVersionUID = 6913527582734758032L;

    @JSONField(name = "key", ordinal = 1)
    private JsonGuidKey key;

    @JSONField(name = "key", ordinal = 2)
    private JsonIdKey roleKey;

    @JSONField(name = "content", ordinal = 3)
    private String content;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public JsonPexp() {
    }

    public JsonGuidKey getKey() {
        return key;
    }

    public void setKey(JsonGuidKey key) {
        this.key = key;
    }

    public JsonIdKey getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(JsonIdKey roleKey) {
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
