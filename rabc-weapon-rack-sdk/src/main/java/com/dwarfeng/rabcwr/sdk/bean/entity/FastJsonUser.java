package com.dwarfeng.rabcwr.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

public class FastJsonUser implements Bean {

    private static final long serialVersionUID = -2412108889516251526L;

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "password", ordinal = 3)
    private String password;

    @JSONField(name = "enabled", ordinal = 4)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonUser() {
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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

    public boolean getEnabled() {
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
        return "JsonUser{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
