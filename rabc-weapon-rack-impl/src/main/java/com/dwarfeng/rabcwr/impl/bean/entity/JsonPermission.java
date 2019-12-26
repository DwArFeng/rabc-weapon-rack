package com.dwarfeng.rabcwr.impl.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;

import java.io.Serializable;

public class JsonPermission implements Serializable {

    private static final long serialVersionUID = -1611452886001308613L;

    @JSONField(name = "key", ordinal = 1)
    private GuidKey key;

    @JSONField(name = "content", ordinal = 2)
    private String content;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JsonPermission() {
    }

    public GuidKey getKey() {
        return key;
    }

    public void setKey(GuidKey key) {
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
        return "JsonPermission{" +
                "key=" + key +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
