package com.dwarfeng.rabcwr.impl.bean.entity;

import com.dwarfeng.rabcwr.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_permission")
public class HibernatePermission implements Bean {

    private static final long serialVersionUID = 6093141336619024729L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "content", length = Constraints.LENGTH_CONTENT, nullable = false)
    private String content;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK, nullable = true)
    private String remark;

    public HibernatePermission() {
    }

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey guidKey) {
        this.longId = Optional.ofNullable(guidKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
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
                "longId=" + longId +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
