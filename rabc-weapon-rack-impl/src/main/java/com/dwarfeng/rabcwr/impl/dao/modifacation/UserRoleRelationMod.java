package com.dwarfeng.rabcwr.impl.dao.modifacation;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.subgrade.sdk.hibernate.modification.RelationMod;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserRoleRelationMod implements RelationMod<HibernateUser, HibernateRole> {

    @Override
    public List<Object> updateOnAdd(HibernateUser hibernateUser, HibernateRole hibernateRole) {
        hibernateRole.getUsers().add(hibernateUser);
        return Collections.singletonList(hibernateRole);
    }

    @Override
    public List<Object> updateOnDelete(HibernateUser hibernateUser, HibernateRole hibernateRole) {
        hibernateRole.getUsers().remove(hibernateUser);
        return Collections.singletonList(hibernateRole);
    }
}
