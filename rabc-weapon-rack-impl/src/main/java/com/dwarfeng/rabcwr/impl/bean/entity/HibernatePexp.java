package com.dwarfeng.rabcwr.impl.bean.entity;

import com.dwarfeng.rabcwr.impl.bean.key.HibernateGuidKey;
import com.dwarfeng.rabcwr.impl.bean.key.HibernateIdKey;
import com.dwarfeng.rabcwr.sdk.util.Constraints;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
@IdClass(HibernateGuidKey.class)
@Table(name = "tbl_pexp")
public class HibernatePexp implements Serializable {

    private static final long serialVersionUID = 5957571523574482047L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "guid", nullable = false, unique = true)
    private Long guid;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "role_id", length = Constraints.LENGTH_ID, nullable = true)
    private String roleId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "content", length = Constraints.LENGTH_CONTENT, nullable = false)
    private String content;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK, nullable = true)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateRole.class)
    @JoinColumns({ //
            @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateRole role;

    public HibernatePexp() {
    }

    public HibernateGuidKey getKey() {
        return Optional.ofNullable(guid).map(HibernateGuidKey::new).orElse(null);
    }

    public void setKey(HibernateGuidKey guidKey) {
        this.guid = Optional.ofNullable(guidKey).map(HibernateGuidKey::getGuid).orElse(null);
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public HibernateIdKey getRoleKey() {
        return Optional.ofNullable(roleId).map(HibernateIdKey::new).orElse(null);
    }

    public void setRoleKey(HibernateIdKey guidKey) {
        this.roleId = Optional.ofNullable(guidKey).map(HibernateIdKey::getId).orElse(null);
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateRole getRole() {
        return role;
    }

    public void setRole(HibernateRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "HibernatePexp{" +
                "guid=" + guid +
                ", roleId='" + roleId + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
