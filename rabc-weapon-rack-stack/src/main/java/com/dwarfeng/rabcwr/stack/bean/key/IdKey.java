package com.dwarfeng.rabcwr.stack.bean.key;

import java.util.Objects;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class IdKey implements Key {

    private static final long serialVersionUID = -2189265770009313374L;

    private String id;

    public IdKey() {
    }

    public IdKey(String id) {
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

        IdKey userKey = (IdKey) o;

        return Objects.equals(id, userKey.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IdKey{" +
                "id='" + id + '\'' +
                '}';
    }
}
