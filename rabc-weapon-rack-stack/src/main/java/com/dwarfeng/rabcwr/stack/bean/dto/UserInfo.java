package com.dwarfeng.rabcwr.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

public class UserInfo implements Dto {

    private static final long serialVersionUID = -7247213260626039980L;

    private String id;
    private String name;
    private boolean enabled;
    private String remark;

    public UserInfo() {
    }

    public UserInfo(String id, String name, boolean enabled, String remark) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.remark = remark;
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
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
