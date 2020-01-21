package com.dwarfeng.rabcwr.sdk.validation.bean.dto;

import com.dwarfeng.rabcwr.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.validation.group.Insert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;

public class ValidationUserInfo implements Serializable {

    private static final long serialVersionUID = 7834392273900328051L;

    @NotNull(groups = {Default.class, Insert.class})
    private String id;

    @NotNull(groups = {Default.class, Insert.class})
    @Length(max = Constraints.LENGTH_NAME, groups = {Default.class, Insert.class})
    private String name;

    private boolean enabled;

    @Length(max = Constraints.LENGTH_REMARK, groups = {Default.class, Insert.class})
    private String remark;

    public ValidationUserInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "ValidationUserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
