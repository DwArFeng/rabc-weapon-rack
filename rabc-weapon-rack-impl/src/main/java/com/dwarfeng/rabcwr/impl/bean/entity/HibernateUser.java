package com.dwarfeng.rabcwr.impl.bean.entity;

import com.dwarfeng.rabcwr.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_user")
public class HibernateUser implements Bean {

    private static final long serialVersionUID = 6069180993822366758L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "password", columnDefinition = "CHAR(60)", nullable = true)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK, nullable = true)
    private String remark;

    // -----------------------------------------------------------多对多-----------------------------------------------------------
    @ManyToMany(targetEntity = HibernateRole.class, cascade = CascadeType.MERGE, mappedBy = "users")
    private Set<HibernateRole> roles = new HashSet<>();

    public HibernateUser() {
    }

    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey uuidKey) {
        this.stringId = Optional.ofNullable(uuidKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
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

    public Set<HibernateRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<HibernateRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "HibernateUser{" +
                "stringId='" + stringId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
