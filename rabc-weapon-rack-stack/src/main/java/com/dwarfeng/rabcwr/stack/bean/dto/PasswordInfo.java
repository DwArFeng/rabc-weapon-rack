package com.dwarfeng.rabcwr.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

public class PasswordInfo implements Dto {

    private static final long serialVersionUID = -6800873539681986190L;

    private String id;
    private String oldPassword;
    private String newPassword;

    public PasswordInfo() {
    }

    public PasswordInfo(String id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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
        return "PasswordInfo{" +
                "id='" + id + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
