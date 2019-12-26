package com.dwarfeng.rabcwr.impl.bean.entity;

import com.dwarfeng.rabcwr.impl.bean.key.HibernateGuidKey;
import com.dwarfeng.rabcwr.sdk.util.Constraints;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateGuidKey.class)
@Table(name = "tbl_pexp")
public class HibernatePexp implements Serializable {

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "guid", nullable = false, unique = true)
    private Long guid;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "content", length = Constraints.LENGTH_CONTENT, nullable = false)
    private String content;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK, nullable = true)
    private String remark;

    // -----------------------------------------------------------多对多-----------------------------------------------------------
    @ManyToMany(targetEntity = HibernateRole.class, cascade = CascadeType.MERGE, mappedBy = "pexps")
    private Set<HibernateRole> roles = new HashSet<>();

    public HibernatePexp() {
    }

    public HibernateGuidKey getKey() {
        return Optional.ofNullable(guid).map(HibernateGuidKey::new).orElse(null);
    }

    public void setKey(HibernateGuidKey guidKey) {
        this.guid = Optional.ofNullable(guidKey).map(HibernateGuidKey::getGuid).orElse(null);
    }

    public long getGuid() {
        return guid;
    }

    public void setGuid(long guid) {
        this.guid = guid;
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

    public Set<HibernateRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<HibernateRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "HibernatePexp{" +
                "guid=" + guid +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
