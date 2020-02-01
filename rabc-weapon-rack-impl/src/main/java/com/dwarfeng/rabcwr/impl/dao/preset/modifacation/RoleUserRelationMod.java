package com.dwarfeng.rabcwr.impl.dao.preset.modifacation;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.subgrade.sdk.hibernate.modification.RelationMod;

import java.util.Collections;
import java.util.List;

public class RoleUserRelationMod implements RelationMod<HibernateRole, HibernateUser> {

    @Override
    public List<Object> updateOnAdd(HibernateRole hibernateRole, HibernateUser hibernateUser) {
        hibernateRole.getUsers().add(hibernateUser);
        return Collections.singletonList(hibernateRole);
    }

    @Override
    public List<Object> updateOnDelete(HibernateRole hibernateRole, HibernateUser hibernateUser) {
        hibernateRole.getUsers().remove(hibernateUser);
        return Collections.singletonList(hibernateRole);
    }
}
