package com.dwarfeng.rabcwr.stack.bean.entity;

import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class Pexp implements Entity<GuidKey> {

    private static final long serialVersionUID = 8790319865873105566L;

    private GuidKey key;
    private String content;
    private String remark;

    public Pexp() {
    }

    public Pexp(GuidKey key, String content, String remark) {
        this.key = key;
        this.content = content;
        this.remark = remark;
    }

    @Override
    public GuidKey getKey() {
        return key;
    }

    @Override
    public void setKey(GuidKey key) {
        this.key = key;
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
        return "Pexp{" +
                "key=" + key +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
