package com.dwarfeng.rabcwr.stack.bean.dto;

import java.io.Serializable;

public class RegisterInfo implements Serializable {

    private static final long serialVersionUID = 3823420735812131351L;

    private String id;
    private String name;
    private String password;
    private String remark;

    public RegisterInfo() {
    }

    public RegisterInfo(String id, String name, String password, String remark) {
        this.id = id;
        this.name = name;
        this.password = password;
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
        return "RegisterInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
