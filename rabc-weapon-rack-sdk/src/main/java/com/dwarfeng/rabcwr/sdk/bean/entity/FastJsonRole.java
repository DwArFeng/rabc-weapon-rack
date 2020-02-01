package com.dwarfeng.rabcwr.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

public class FastJsonRole implements Bean {

    private static final long serialVersionUID = -4501705687291160541L;

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonRole() {
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "JsonRole{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
