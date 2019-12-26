package com.dwarfeng.rabcwr.impl.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.rabcwr.impl.bean.key.JsonIdKey;

import java.io.Serializable;

public class JsonUser implements Serializable {

    private static final long serialVersionUID = -2412108889516251526L;

    @JSONField(name = "key", ordinal = 1)
    private JsonIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "password", ordinal = 3)
    private String password;

    @JSONField(name = "enabled", ordinal = 4)
    private String enabled;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JsonUser() {
    }

    public JsonIdKey getKey() {
        return key;
    }

    public void setKey(JsonIdKey key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
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
        return "JsonUser{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
