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
@Table(name = "tbl_role")
public class HibernateRole implements Bean {

    private static final long serialVersionUID = -178166498064464971L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK, nullable = true)
    private String remark;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePexp.class, mappedBy = "role")
    private Set<HibernatePexp> pexps = new HashSet<>();

    // -----------------------------------------------------------多对多-----------------------------------------------------------
    @ManyToMany(targetEntity = HibernateUser.class)
    @JoinTable(name = "tbl_user_has_role", joinColumns = { //
            @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)//
    }, inverseJoinColumns = { //
            @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false),//
    })
    private Set<HibernateUser> users = new HashSet<>();

    public HibernateRole() {
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

    public Set<HibernatePexp> getPexps() {
        return pexps;
    }

    public void setPexps(Set<HibernatePexp> pexps) {
        this.pexps = pexps;
    }

    public Set<HibernateUser> getUsers() {
        return users;
    }

    public void setUsers(Set<HibernateUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "HibernateRole{" +
                "stringId='" + stringId + '\'' +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
