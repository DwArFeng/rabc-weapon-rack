package com.dwarfeng.rabcwr.impl.handler.preset;

import com.dwarfeng.rabcwr.impl.handler.PermissionFilter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 精确匹配的权限过滤器。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Component
public class ExactPermissionFilter implements PermissionFilter {

    @Override
    public String getIdentifier() {
        return "EXACT";
    }

    @Override
    public boolean accept(String pattern, String permissionContent) {
        return Objects.equals(pattern, permissionContent);
    }
}
