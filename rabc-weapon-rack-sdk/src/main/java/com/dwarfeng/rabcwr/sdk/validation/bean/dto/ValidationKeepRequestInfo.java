package com.dwarfeng.rabcwr.sdk.validation.bean.dto;

import com.dwarfeng.rabcwr.sdk.validation.group.Insert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.io.Serializable;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class ValidationKeepRequestInfo implements Serializable {

    private static final long serialVersionUID = -4882153931905928832L;

    @Positive(groups = {Default.class, Insert.class})
    private long guid;

    @NotNull(groups = {Default.class, Insert.class})
    private String plaintext;

    public ValidationKeepRequestInfo() {
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
        return "ValidationKeepRequestInfo{" +
                "guid=" + guid +
                ", plaintext='" + plaintext + '\'' +
                '}';
    }
}
