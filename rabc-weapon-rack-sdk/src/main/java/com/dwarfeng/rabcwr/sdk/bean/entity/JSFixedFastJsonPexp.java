package com.dwarfeng.rabcwr.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.springframework.lang.NonNull;

public class JSFixedFastJsonPexp implements Bean {

    private static final long serialVersionUID = 6913527582734758032L;
    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;
    @JSONField(name = "role_key", ordinal = 2)
    private FastJsonStringIdKey roleKey;
    @JSONField(name = "content", ordinal = 3)
    private String content;
    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public JSFixedFastJsonPexp() {
    }

    public JSFixedFastJsonPexp(JSFixedFastJsonLongIdKey key, FastJsonStringIdKey roleKey, String content, String remark) {
        this.key = key;
        this.roleKey = roleKey;
        this.content = content;
        this.remark = remark;
    }

    public static JSFixedFastJsonPexp of(@NonNull Pexp pexp) {
        return new JSFixedFastJsonPexp(
                JSFixedFastJsonLongIdKey.of(pexp.getKey()),
                FastJsonStringIdKey.of(pexp.getRoleKey()),
                pexp.getContent(),
                pexp.getRemark()
        );
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
