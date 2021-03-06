package com.dwarfeng.rabcwr.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class WebInputRole implements Bean {

    private static final long serialVersionUID = 7478227163791038557L;

    public static Role toStackBean(WebInputRole webInputRole) {
        if (Objects.isNull(webInputRole)) {
            return null;
        }
        return new Role(
                WebInputStringIdKey.toStackBean(webInputRole.getKey()),
                webInputRole.isEnabled(),
                webInputRole.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputStringIdKey key;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "remark")
    private String remark;

    public WebInputRole() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
        this.key = key;
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

    @Override
    public String toString() {
        return "WebInputRole{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
