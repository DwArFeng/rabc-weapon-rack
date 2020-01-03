package com.dwarfeng.rabcwr.stack.bean.dto;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class KeepRequestInfo implements Dto {

    private static final long serialVersionUID = 186517806443440060L;

    private long guid;
    private String plaintext;

    public KeepRequestInfo() {
    }

    public KeepRequestInfo(long guid, String plaintext) {
        this.guid = guid;
        this.plaintext = plaintext;
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    @Override
    public String toString() {
        return "KeepRequestInfo{" +
                "guid=" + guid +
                ", plaintext='" + plaintext + '\'' +
                '}';
    }
}
