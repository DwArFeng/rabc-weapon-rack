package com.dwarfeng.rabcwr.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

public class FastJsonLoginState implements Bean {

    private static final long serialVersionUID = 6905371931123253056L;

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "user_key", ordinal = 2)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "expire_date", ordinal = 3)
    private long expireDate;

    public FastJsonLoginState() {
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(FastJsonStringIdKey userKey) {
        this.userKey = userKey;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "FastJsonLoginState{" +
                "key=" + key +
                ", userKey=" + userKey +
                ", expireDate=" + expireDate +
                '}';
    }
}
