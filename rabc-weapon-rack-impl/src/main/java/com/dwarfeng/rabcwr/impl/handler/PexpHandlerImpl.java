package com.dwarfeng.rabcwr.impl.handler;

import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.exception.PexpFormatException;
import com.dwarfeng.rabcwr.stack.handler.PexpHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PexpHandlerImpl implements PexpHandler {

    public static final String DELIMITER = "@";
    public static final String EXACT = "EXACT";
    public static final String PREFIX = "PREFIX";
    public static final String REGEXP = "REGEXP";
    public static final String EXCLUDE_EXACT = "!EXACT";
    public static final String EXCLUDE_PREFIX = "!PREFIX";
    public static final String EXCLUDE_REGEXP = "!REGEXP";

    @Override
    @BehaviorAnalyse
    public List<Permission> analysePermissions(List<Pexp> pexps, List<Permission> allPermissions) throws HandlerException {
        final Map<StringIdKey, Permission> allPermissionMap = allPermissions.stream().collect(Collectors.toMap(Permission::getKey, Function.identity()));
        final Set<StringIdKey> allowedPermissions = new HashSet<>();
        final Set<StringIdKey> rejectedPermissions = new HashSet<>();
        for (Pexp pexp : pexps) {
            String attribute;
            String value;
            try {
                String content = pexp.getContent();
                String[] split = content.split(DELIMITER);
                attribute = split[0].toUpperCase();
                value = split[1];
            } catch (Exception e) {
                throw new PexpFormatException(pexp);
            }

            final String finalValue = value;
            switch (attribute) {
                case EXACT:
                    allPermissionMap.keySet().stream().filter(key -> Objects.equals(key.getStringId(), finalValue))
                            .forEach(allowedPermissions::add);
                    break;
                case PREFIX:
                    allPermissionMap.keySet().stream().filter(key -> key.getStringId().startsWith(finalValue))
                            .forEach(allowedPermissions::add);
                    break;
                case REGEXP:
                    allPermissionMap.keySet().stream().filter(key -> key.getStringId().matches(finalValue))
                            .forEach(allowedPermissions::add);
                    break;
                case EXCLUDE_EXACT:
                    allPermissionMap.keySet().stream().filter(key -> Objects.equals(key.getStringId(), finalValue))
                            .forEach(rejectedPermissions::add);
                    break;
                case EXCLUDE_PREFIX:
                    allPermissionMap.keySet().stream().filter(key -> key.getStringId().startsWith(finalValue))
                            .forEach(rejectedPermissions::add);
                    break;
                case EXCLUDE_REGEXP:
                    allPermissionMap.keySet().stream().filter(key -> key.getStringId().matches(finalValue))
                            .forEach(rejectedPermissions::add);
                    break;
                default:
                    throw new PexpFormatException(pexp);
            }
        }

        allowedPermissions.removeAll(rejectedPermissions);
        return allowedPermissions.stream().map(allPermissionMap::get).sorted(new PermissionComparator()).collect(Collectors.toList());
    }

    private final static class PermissionComparator implements Comparator<Permission> {

        @Override
        public int compare(Permission o1, Permission o2) {
            return o1.getKey().getStringId().compareTo(o2.getKey().getStringId());
        }
    }
}
