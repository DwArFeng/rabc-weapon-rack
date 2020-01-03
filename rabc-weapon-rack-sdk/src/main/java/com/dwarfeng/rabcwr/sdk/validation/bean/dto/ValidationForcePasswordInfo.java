package com.dwarfeng.rabcwr.sdk.validation.bean.dto;

import com.dwarfeng.rabcwr.sdk.validation.group.Insert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.io.Serializable;

public class ValidationForcePasswordInfo implements Serializable {

    private static final long serialVersionUID = -7029014217591537570L;

    @NotNull(groups = {Default.class, Insert.class})
    private String id;

    @NotNull(groups = {Default.class, Insert.class})
    @Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$", groups = {Default.class, Insert.class},
            message = "密码长度至少为6位，且至少包含一个字母与一个数字")
    private String newPassword;

    public ValidationForcePasswordInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ValidationForcePasswordInfo{" +
                "id='" + id + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
