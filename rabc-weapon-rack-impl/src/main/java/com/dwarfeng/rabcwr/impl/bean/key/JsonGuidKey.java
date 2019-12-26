package com.dwarfeng.rabcwr.impl.bean.key;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class JsonGuidKey implements Serializable {

    private static final long serialVersionUID = -3679141371566355293L;

    @JSONField(name = "guid", ordinal = 1)
    private long guid;

    public JsonGuidKey() {
    }

    public JsonGuidKey(long guid) {
        this.guid = guid;
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonGuidKey that = (JsonGuidKey) o;

        return guid == that.guid;
    }

    @Override
    public int hashCode() {
        return (int) (guid ^ (guid >>> 32));
    }

    @Override
    public String toString() {
        return "JsonGuidKey{" +
                "guid=" + guid +
                '}';
    }
}
