package com.dwarfeng.rabcwr.sdk.validation.bean.key;

import com.dwarfeng.rabcwr.sdk.util.Constraints;
import com.dwarfeng.rabcwr.sdk.validation.group.Insert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Objects;

public class ValidationGuidKey implements Serializable {

    private static final long serialVersionUID = 5910437002459060419L;

    @NotNull(groups = {Default.class, Insert.class})
    @Length(max = Constraints.LENGTH_ID, groups = {Default.class, Insert.class})
    private String id;

    public ValidationGuidKey() {
    }

    public ValidationGuidKey(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidationGuidKey that = (ValidationGuidKey) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ValidationGuidKey{" +
                "id='" + id + '\'' +
                '}';
    }
}
