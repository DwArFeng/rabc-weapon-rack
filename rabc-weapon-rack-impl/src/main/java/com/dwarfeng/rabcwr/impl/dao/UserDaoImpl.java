package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.dao.UserDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetDeleteDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.interceptor.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.DaoException;
import org.dozer.Mapper;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> batchDelegate;
    @Autowired
    private HibernatePresetDeleteDao<StringIdKey, User, HibernateUser> presetDelegate;

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private Mapper mapper;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insert(User element) throws DaoException {
        return batchDelegate.insert(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void update(User element) throws DaoException {
        batchDelegate.update(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(StringIdKey key) throws DaoException {
        try {
            internalDelete(key);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private void internalDelete(StringIdKey key) {
        HibernateUser hibernateUser = template.get(HibernateUser.class, mapper.map(key, HibernateStringIdKey.class));
        for (HibernateRole role : hibernateUser.getRoles()) {
            role.getUsers().remove(hibernateUser);
            template.update(role);
        }
        template.delete(hibernateUser);
        template.flush();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(StringIdKey key) throws DaoException {
        return batchDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public User get(StringIdKey key) throws DaoException {
        return batchDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public List<StringIdKey> batchInsert(List<User> elements) throws DaoException {
        return batchDelegate.batchInsert(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void batchUpdate(List<User> elements) throws DaoException {
        batchDelegate.batchUpdate(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void batchDelete(List<StringIdKey> keys) throws DaoException {
        try {
            for (StringIdKey key : keys) {
                internalDelete(key);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean allExists(List<StringIdKey> keys) throws DaoException {
        return batchDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean nonExists(List<StringIdKey> keys) throws DaoException {
        return batchDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<User> batchGet(List<StringIdKey> keys) {
        return batchDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<User> lookup(String preset, Object[] objs) throws DaoException {
        return presetDelegate.lookup(preset, objs);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<User> lookup(String preset, Object[] objs, PagingInfo pagingInfo) throws DaoException {
        return presetDelegate.lookup(preset, objs, pagingInfo);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public int lookupCount(String preset, Object[] objs) throws DaoException {
        return presetDelegate.lookupCount(preset, objs);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public List<StringIdKey> lookupDelete(String preset, Object[] objs) throws DaoException {
        try {
            DetachedCriteria criteria = DetachedCriteria.forClass(HibernateUser.class);
            presetDelegate.getPresetCriteriaMaker().makeCriteria(criteria, preset, objs);
            //noinspection unchecked
            List<HibernateUser> byCriteria = (List<HibernateUser>) template.findByCriteria(criteria);
            for (HibernateUser hibernateUser : byCriteria) {
                for (HibernateRole role : hibernateUser.getRoles()) {
                    role.getUsers().remove(hibernateUser);
                    template.update(role);
                }
            }
            template.deleteAll(byCriteria);
            template.flush();
            return byCriteria.stream().map(user -> new StringIdKey(user.getStringId())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void addRoles(StringIdKey userIdKey, List<StringIdKey> roleIdKeys) throws DaoException {
        try {
            HibernateUser hibernateUser = template.get(HibernateUser.class, mapper.map(userIdKey, HibernateStringIdKey.class));
            List<HibernateRole> hibernateRoles = new ArrayList<>();
            for (StringIdKey roleIdKey : roleIdKeys) {
                hibernateRoles.add(template.get(HibernateRole.class, mapper.map(roleIdKey, HibernateStringIdKey.class)));
            }
            for (HibernateRole hibernateRole : hibernateRoles) {
                hibernateRole.getUsers().add(hibernateUser);
                template.save(hibernateRole);
            }
            template.flush();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void deleteRoles(StringIdKey userIdKey, List<StringIdKey> roleIdKeys) throws DaoException {
        try {
            HibernateUser hibernateUser = template.get(HibernateUser.class, mapper.map(userIdKey, HibernateStringIdKey.class));
            List<HibernateRole> hibernateRoles = new ArrayList<>();
            for (StringIdKey roleIdKey : roleIdKeys) {
                hibernateRoles.add(template.get(HibernateRole.class, mapper.map(roleIdKey, HibernateStringIdKey.class)));
            }
            for (HibernateRole hibernateRole : hibernateRoles) {
                hibernateRole.getUsers().add(hibernateUser);
                template.save(hibernateRole);
            }
            template.flush();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
