package com.dwarfeng.rabcwr.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 登录状态。
 *
 * @author DwArFeng
 * @since 0.1.0-alpha
 */
public class LoginState implements Entity<LongIdKey> {

    private static final long serialVersionUID = 1978695890873432131L;

    private LongIdKey key;
    private StringIdKey userKey;
    private long expireDate;

    public LoginState() {
    }

    public LoginState(LongIdKey key, StringIdKey userKey, long expireDate) {
        this.key = key;
        this.userKey = userKey;
        this.expireDate = expireDate;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
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
        return "LoginState{" +
                "key=" + key +
                ", userKey=" + userKey +
                ", expireDate=" + expireDate +
                '}';
    }
}
