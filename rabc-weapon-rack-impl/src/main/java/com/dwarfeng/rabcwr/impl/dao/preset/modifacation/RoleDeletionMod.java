package com.dwarfeng.rabcwr.impl.dao.preset.modifacation;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePexp;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DeletionMod;

import java.util.ArrayList;
import java.util.List;

public class RoleDeletionMod implements DeletionMod<HibernateRole> {

    @Override
    public List<Object> updateBeforeDelete(HibernateRole hibernateRole) {
        List<Object> objects = new ArrayList<>();

        //清除Pexp的
        for (HibernatePexp pexp : hibernateRole.getPexps()) {
            pexp.setRoleId(null);
            objects.add(pexp);
        }

        hibernateRole.getUsers().clear();
        objects.add(hibernateRole);
        return objects;
    }
}
