package com.dwarfeng.rabcwr.impl.dao.preset.modifacation;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DeletionMod;

import java.util.ArrayList;
import java.util.List;

public class UserDeletionMode implements DeletionMod<HibernateUser> {

    @Override
    public List<Object> updateBeforeDelete(HibernateUser hibernateUser) {
        List<Object> objects = new ArrayList<>();
        for (HibernateRole role : hibernateUser.getRoles()) {
            role.getUsers().remove(hibernateUser);
            objects.add(role);
        }
        return objects;
    }
}
