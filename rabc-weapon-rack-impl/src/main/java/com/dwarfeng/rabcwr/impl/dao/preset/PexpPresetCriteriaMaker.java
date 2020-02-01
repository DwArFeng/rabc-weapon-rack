package com.dwarfeng.rabcwr.impl.dao.preset;

import com.dwarfeng.rabcwr.stack.service.PexpMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PexpPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case PexpMaintainService.PEXP_FOR_ROLE:
                childForRole(detachedCriteria, objects);
                break;
            case PexpMaintainService.PEXP_FOR_ROLE_SET:
                childForRoleSet(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
        detachedCriteria.addOrder(Order.asc("longId"));
    }

    private void childForRole(DetachedCriteria detachedCriteria, Object[] objects) {
        Object object = objects[0];
        if (Objects.isNull(object)) {
            detachedCriteria.add(Restrictions.isNull("roleId"));
        } else if (object instanceof StringIdKey) {
            detachedCriteria.add(Restrictions.eqOrIsNull("roleId", ((StringIdKey) object).getStringId()));
        } else {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForRoleSet(DetachedCriteria detachedCriteria, Object[] objects) {
        Object object = objects[0];
        if (Objects.isNull(object)) {
            detachedCriteria.add(Restrictions.isNull("roleId"));
        } else if (object instanceof List) {
            if (((List<?>) object).isEmpty()) {
                detachedCriteria.add(Restrictions.isNull("longId"));
            } else {
                //noinspection unchecked
                detachedCriteria.add(Restrictions.in("roleId", stringList((List<StringIdKey>) object)));
            }
        } else {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private List<String> stringList(List<StringIdKey> list) {
        return list.stream().map(StringIdKey::getStringId).collect(Collectors.toList());
    }
}
