package com.dwarfeng.rabcwr.impl.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.rabcwr.impl.bean.key.JsonGuidKey;

import java.io.Serializable;

public class JsonPexp implements Serializable {

    @JSONField(name = "key", ordinal = 1)
    private JsonGuidKey key;

    @JSONField(name = "content", ordinal = 2)
    private String content;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JsonPexp() {
    }

    public JsonGuidKey getKey() {
        return key;
    }

    public void setKey(JsonGuidKey key) {
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
        return "JsonPexp{" +
                "key=" + key +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
