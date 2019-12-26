package com.dwarfeng.rabcwr.stack.bean.key;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class GuidKey implements Key {

    private long guid;

    public GuidKey() {
    }

    public GuidKey(long guid) {
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

        GuidKey guidKey = (GuidKey) o;

        return guid == guidKey.guid;
    }

    @Override
    public int hashCode() {
        return (int) (guid ^ (guid >>> 32));
    }

    @Override
    public String toString() {
        return "GuidKey{" +
                "guid=" + guid +
                '}';
    }
}
