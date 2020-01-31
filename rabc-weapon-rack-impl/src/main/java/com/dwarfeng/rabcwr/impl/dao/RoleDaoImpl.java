package com.dwarfeng.rabcwr.impl.dao;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.dao.RoleDao;
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

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Role, HibernateRole> batchDelegate;
    @Autowired
    private HibernatePresetDeleteDao<StringIdKey, Role, HibernateRole> presetDelegate;

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private Mapper mapper;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public StringIdKey insert(Role element) throws DaoException {
        return batchDelegate.insert(element);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void update(Role element) throws DaoException {
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
        HibernateRole hibernateRole = template.get(HibernateRole.class, mapper.map(key, HibernateStringIdKey.class));
        assert hibernateRole != null;
        hibernateRole.getUsers().clear();
        template.update(hibernateRole);
        template.delete(hibernateRole);
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
    public Role get(StringIdKey key) throws DaoException {
        return batchDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public List<StringIdKey> batchInsert(List<Role> elements) throws DaoException {
        return batchDelegate.batchInsert(elements);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void batchUpdate(List<Role> elements) throws DaoException {
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
    public List<Role> batchGet(List<StringIdKey> keys) {
        return batchDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<Role> lookup(String preset, Object[] objs) throws DaoException {
        return presetDelegate.lookup(preset, objs);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<Role> lookup(String preset, Object[] objs, PagingInfo pagingInfo) throws DaoException {
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
            DetachedCriteria criteria = DetachedCriteria.forClass(HibernateRole.class);
            presetDelegate.getPresetCriteriaMaker().makeCriteria(criteria, preset, objs);
            //noinspection unchecked
            List<HibernateRole> byCriteria = (List<HibernateRole>) template.findByCriteria(criteria);
            for (HibernateRole hibernateRole : byCriteria) {
                hibernateRole.getUsers().clear();
                template.update(hibernateRole);
            }
            template.deleteAll(byCriteria);
            return byCriteria.stream().map(role -> new StringIdKey(role.getStringId())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws DaoException {

    }

    @Override
    public void removeUsers(StringIdKey roleIdKey, List<StringIdKey> userIdKeys) throws DaoException {

    }
}
