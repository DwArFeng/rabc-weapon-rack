package com.dwarfeng.rabcwr.impl.bean.key;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class JsonIdKey implements Serializable {

    private static final long serialVersionUID = 5663293071480484677L;

    @JSONField(name = "id", ordinal = 1)
    private String id;

    public JsonIdKey() {
    }

    public JsonIdKey(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonIdKey that = (JsonIdKey) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "JsonIdKey{" +
                "id='" + id + '\'' +
                '}';
    }
}
