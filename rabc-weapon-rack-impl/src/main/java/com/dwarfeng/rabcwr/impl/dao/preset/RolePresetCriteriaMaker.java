package com.dwarfeng.rabcwr.impl.dao.preset;

import com.dwarfeng.rabcwr.stack.service.RoleMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.Arrays;
import java.util.Objects;

public class RolePresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case RoleMaintainService.ROLE_FOR_USER:
                roleForUser(detachedCriteria, objects);
                break;
            case RoleMaintainService.ENABLED_ROLE_FOR_USER:
                enabledRoleForUser(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void roleForUser(DetachedCriteria detachedCriteria, Object[] objects) {
        Object object = objects[0];
        if (Objects.isNull(object)) {
            detachedCriteria.add(Restrictions.isEmpty("users"));
        } else if (object instanceof StringIdKey) {
            detachedCriteria.createAlias("users", "u");
            detachedCriteria.add(Restrictions.eqOrIsNull("u.stringId", ((StringIdKey) object).getStringId()));
        } else {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void enabledRoleForUser(DetachedCriteria detachedCriteria, Object[] objects) {
        Object object = objects[0];
        if (Objects.isNull(object)) {
            detachedCriteria.add(Restrictions.isEmpty("users"));
            detachedCriteria.add(Restrictions.eq("enabled", true));
        } else if (object instanceof StringIdKey) {
            detachedCriteria.createAlias("users", "u");
            detachedCriteria.add(Restrictions.eqOrIsNull("u.stringId", ((StringIdKey) object).getStringId()));
            detachedCriteria.add(Restrictions.eq("enabled", true));
        } else {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
