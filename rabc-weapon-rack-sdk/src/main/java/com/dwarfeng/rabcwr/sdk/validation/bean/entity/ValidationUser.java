package com.dwarfeng.rabcwr.sdk.validation.bean.entity;

import com.dwarfeng.rabcwr.sdk.util.Constraints;
import com.dwarfeng.rabcwr.sdk.validation.bean.key.ValidationLongIdKey;
import com.dwarfeng.subgrade.sdk.validation.group.Insert;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

public class ValidationUser implements Serializable {

    private static final long serialVersionUID = 3983362857686103011L;

    @NotNull(groups = {Default.class, Insert.class})
    @Valid
    private ValidationLongIdKey key;

    @NotNull(groups = {Default.class, Insert.class})
    @Length(max = Constraints.LENGTH_NAME, groups = {Default.class, Insert.class})
    private String name;

    @Length(min = Constraints.LENGTH_PASSWORD, max = Constraints.LENGTH_PASSWORD, groups = {Default.class, Insert.class})
    private String password;

    private boolean enabled;

    @Length(max = Constraints.LENGTH_REMARK, groups = {Default.class, Insert.class})
    private String remark;

    public ValidationUser() {
    }

    public ValidationLongIdKey getKey() {
        return key;
    }

    public void setKey(ValidationLongIdKey key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ValidationUser{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
