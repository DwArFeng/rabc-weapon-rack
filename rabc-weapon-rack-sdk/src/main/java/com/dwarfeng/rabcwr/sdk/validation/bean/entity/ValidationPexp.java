package com.dwarfeng.rabcwr.sdk.validation.bean.entity;

import com.dwarfeng.rabcwr.sdk.util.Constraints;
import com.dwarfeng.rabcwr.sdk.validation.bean.key.ValidationStringIdKey;
import com.dwarfeng.subgrade.sdk.validation.group.Insert;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

public class ValidationPexp implements Serializable {

    private static final long serialVersionUID = 4426538851002379937L;

    @NotNull(groups = {Default.class})
    @Valid
    private ValidationStringIdKey key;

    @NotNull(groups = {Default.class, Insert.class})
    @Length(max = Constraints.LENGTH_CONTENT, groups = {Default.class, Insert.class})
    private String content;

    @Length(max = Constraints.LENGTH_REMARK, groups = {Default.class, Insert.class})
    private String remark;

    public ValidationPexp() {
    }

    public ValidationStringIdKey getKey() {
        return key;
    }

    public void setKey(ValidationStringIdKey key) {
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
        return "ValidationPexp{" +
                "key=" + key +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
