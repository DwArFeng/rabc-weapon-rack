package com.dwarfeng.rabcwr.impl.bean.entity;

import com.dwarfeng.rabcwr.impl.bean.key.HibernateGuidKey;
import com.dwarfeng.rabcwr.sdk.util.Constraints;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;

@Entity
@IdClass(HibernateGuidKey.class)
@Table(name = "tbl_permission")
public class HibernatePermission implements Serializable {

    private static final long serialVersionUID = 6093141336619024729L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "guid", nullable = false, unique = true)
    private Long guid;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "content", length = Constraints.LENGTH_CONTENT, nullable = false)
    private String content;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK, nullable = true)
    private String remark;

    public HibernatePermission() {
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

    @Override
    public String toString() {
        return "HibernatePermission{" +
                "guid=" + guid +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
