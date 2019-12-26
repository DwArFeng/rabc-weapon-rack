package com.dwarfeng.rabcwr.impl.bean.key;

import java.io.Serializable;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class HibernateGuidKey implements Serializable {

    private static final long serialVersionUID = 2927661300759736613L;

    private long guid;

    public HibernateGuidKey() {
    }

    public HibernateGuidKey(long guid) {
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

        HibernateGuidKey that = (HibernateGuidKey) o;

        return guid == that.guid;
    }

    @Override
    public int hashCode() {
        return (int) (guid ^ (guid >>> 32));
    }

    @Override
    public String toString() {
        return "HibernateGuidKey{" +
                "guid=" + guid +
                '}';
    }
}
