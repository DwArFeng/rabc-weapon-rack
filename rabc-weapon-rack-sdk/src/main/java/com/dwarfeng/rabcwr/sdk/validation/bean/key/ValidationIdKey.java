package com.dwarfeng.rabcwr.sdk.validation.bean.key;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Objects;

public class ValidationIdKey implements Serializable {

    private static final long serialVersionUID = -4517482416919778277L;

    @Positive(groups = {Default.class})
    @NotNull
    private long guid;

    public ValidationIdKey() {
    }

    public ValidationIdKey(long guid) {
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
        ValidationIdKey that = (ValidationIdKey) o;
        return guid == that.guid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }

    @Override
    public String toString() {
        return "ApiGuidKey{" +
                "guid=" + guid +
                '}';
    }
}
