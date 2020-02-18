package com.dwarfeng.rabcwr.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.springframework.lang.NonNull;

public class FastJsonUser implements Bean {

    private static final long serialVersionUID = 9199394085072121152L;

    public FastJsonUser(FastJsonStringIdKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "remark", ordinal = 2)
    private String remark;

    public FastJsonUser() {
    }

    public static FastJsonUser of(@NonNull User user) {
        return new FastJsonUser(
                FastJsonStringIdKey.of(user.getKey()),
                user.getRemark()
        );
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonUser{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
