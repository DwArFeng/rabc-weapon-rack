package com.dwarfeng.rabcwr.stack.bean.entity;


import com.dwarfeng.rabcwr.stack.bean.key.IdKey;

/**
 * 用户。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class User implements Entity<IdKey> {

    private static final long serialVersionUID = -7166808835407200393L;

    private IdKey key;
    private String name;
    private String password;
    private boolean enabled;
    private String remark;

    public User() {
    }

    public User(IdKey key, String name, String password, boolean enabled, String remark) {
        this.key = key;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
        this.remark = remark;
    }

    @Override
    public IdKey getKey() {
        return key;
    }

    @Override
    public void setKey(IdKey key) {
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

    public boolean getEnabled() {
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
        return "User{" +
                "key=" + key +
                ", name=" + name +
                ", password='" + password + '\'' +
                ", enabled='" + enabled + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
