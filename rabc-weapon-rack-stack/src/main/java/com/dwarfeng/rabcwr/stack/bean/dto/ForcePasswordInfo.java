package com.dwarfeng.rabcwr.stack.bean.dto;

public class ForcePasswordInfo implements Dto {

    private static final long serialVersionUID = -6800873539681986190L;

    private String id;
    private String newPassword;

    public ForcePasswordInfo() {
    }

    public ForcePasswordInfo(String id, String newPassword) {
        this.id = id;
        this.newPassword = newPassword;
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
        return "ForcePasswordInfo{" +
                "id='" + id + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
