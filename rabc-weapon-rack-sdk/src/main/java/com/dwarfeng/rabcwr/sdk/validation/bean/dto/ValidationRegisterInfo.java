package com.dwarfeng.rabcwr.sdk.validation.bean.dto;

import com.dwarfeng.rabcwr.sdk.validation.group.Insert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.io.Serializable;

public class ValidationRegisterInfo implements Serializable {

    private static final long serialVersionUID = 5212005515642301396L;

    @NotNull(groups = {Default.class, Insert.class})
    private String id;

    @NotNull(groups = {Default.class, Insert.class})
    private String name;

    @NotNull(groups = {Default.class, Insert.class})
    @Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$", groups = {Default.class, Insert.class},
            message = "密码长度至少为6位，且至少包含一个字母与一个数字")
    private String password;

    @NotNull(groups = {Default.class, Insert.class})
    private String remark;

    public ValidationRegisterInfo() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ValidationRegisterInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
