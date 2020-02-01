package com.dwarfeng.rabcwr.impl.dao.preset.modifacation;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DeletionMod;

import java.util.Collections;
import java.util.List;

public class RoleDeletionMode implements DeletionMod<HibernateRole> {

    @Override
    public List<Object> updateBeforeDelete(HibernateRole hibernateRole) {
        hibernateRole.getUsers().clear();
        return Collections.singletonList(hibernateRole);
    }
}
