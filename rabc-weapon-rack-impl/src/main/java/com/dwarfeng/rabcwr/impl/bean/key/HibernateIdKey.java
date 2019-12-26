package com.dwarfeng.rabcwr.impl.bean.key;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class HibernateIdKey implements Serializable {

    private static final long serialVersionUID = 4042279292963193454L;

    private String id;

    public HibernateIdKey() {
    }

    public HibernateIdKey(String id) {
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

        HibernateIdKey that = (HibernateIdKey) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HibernateIdKey{" +
                "id='" + id + '\'' +
                '}';
    }
}
