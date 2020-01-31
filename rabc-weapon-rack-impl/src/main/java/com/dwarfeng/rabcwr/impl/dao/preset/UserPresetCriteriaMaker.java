package com.dwarfeng.rabcwr.impl.dao.preset;

import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import org.hibernate.criterion.DetachedCriteria;

public class UserPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
//            case PermissionMaintainService.CHILD_FOR_PARENT:
//                childForParent(detachedCriteria, objects);
//                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

//    private void childForParent(DetachedCriteria detachedCriteria, Object[] objects) {
//        Object object = objects[0];
//        if (Objects.isNull(object)) {
//            detachedCriteria.add(Restrictions.isNull("parentLongId"));
//        } else if (object instanceof LongIdKey) {
//            detachedCriteria.add(Restrictions.eqOrIsNull("parentLongId", ((LongIdKey) object).getLongId()));
//        } else {
//            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
//        }
//    }
}
