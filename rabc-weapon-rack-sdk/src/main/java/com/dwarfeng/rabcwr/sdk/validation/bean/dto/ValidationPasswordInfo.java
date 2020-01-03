package com.dwarfeng.rabcwr.sdk.validation.bean.dto;

import com.dwarfeng.rabcwr.sdk.validation.group.Insert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.io.Serializable;

public class ValidationPasswordInfo implements Serializable {

    private static final long serialVersionUID = -6842296339745376267L;

    @NotNull(groups = {Default.class, Insert.class})
    private String id;

    @NotNull(groups = {Default.class, Insert.class})
    private String oldPassword;

    @NotNull(groups = {Default.class, Insert.class})
    @Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$", groups = {Default.class, Insert.class},
            message = "密码长度至少为6位，且至少包含一个字母与一个数字")
    private String newPassword;

    public ValidationPasswordInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ValidationPasswordInfo{" +
                "id='" + id + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
