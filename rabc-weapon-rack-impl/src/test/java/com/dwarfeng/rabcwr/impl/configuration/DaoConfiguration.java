package com.dwarfeng.rabcwr.impl.configuration;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePermission;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePexp;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.rabcwr.impl.dao.modifacation.RoleDeletionMod;
import com.dwarfeng.rabcwr.impl.dao.modifacation.RoleUserRelationMod;
import com.dwarfeng.rabcwr.impl.dao.modifacation.UserDeletionMod;
import com.dwarfeng.rabcwr.impl.dao.modifacation.UserRoleRelationMod;
import com.dwarfeng.rabcwr.impl.dao.preset.PexpPresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.RolePresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.UserPresetCriteriaMaker;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchRelationDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private BeanTransformerConfiguration beanTransformerConfiguration;

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Permission, HibernatePermission> permissionDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.permissionBeanTransformer(),
                HibernatePermission.class);
    }

    @Bean
    public HibernateEntireLookupDao<Permission, HibernatePermission> permissionHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                beanTransformerConfiguration.permissionBeanTransformer(),
                HibernatePermission.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Pexp, HibernatePexp> pexpDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                beanTransformerConfiguration.longIdKeyBeanTransformer(),
                beanTransformerConfiguration.pexpBeanTransformer(),
                HibernatePexp.class);
    }

    @Autowired
    private PexpPresetCriteriaMaker pexpPresetCriteriaMaker;
    @Autowired
    private RoleDeletionMod roleDeletionMod;
    @Autowired
    private RolePresetCriteriaMaker rolePresetCriteriaMaker;
    @Autowired
    private UserDeletionMod userDeletionMod;
    @Autowired
    private UserPresetCriteriaMaker userPresetCriteriaMaker;
    @Autowired
    private UserRoleRelationMod userRoleRelationMod;
    @Autowired
    private RoleUserRelationMod roleUserRelationMod;

    @Bean
    public HibernatePresetLookupDao<Pexp, HibernatePexp> pexpHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                beanTransformerConfiguration.pexpBeanTransformer(),
                HibernatePexp.class,
                pexpPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Role, HibernateRole> roleDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.roleBeanTransformer(),
                HibernateRole.class,
                roleDeletionMod
        );
    }

    @Bean
    public HibernatePresetLookupDao<Role, HibernateRole> roleHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                beanTransformerConfiguration.roleBeanTransformer(),
                HibernateRole.class,
                rolePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.userBeanTransformer(),
                HibernateUser.class,
                userDeletionMod
        );
    }

    @Bean
    public HibernatePresetLookupDao<User, HibernateUser> userHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                beanTransformerConfiguration.userBeanTransformer(),
                HibernateUser.class,
                userPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchRelationDao<StringIdKey, StringIdKey, HibernateStringIdKey, HibernateStringIdKey, HibernateUser, HibernateRole> userRoleBatchRelationDao() {
        return new HibernateBatchRelationDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                HibernateUser.class,
                HibernateRole.class,
                userRoleRelationMod
        );
    }

    @Bean
    public HibernateBatchRelationDao<StringIdKey, StringIdKey, HibernateStringIdKey, HibernateStringIdKey, HibernateRole, HibernateUser> roleUserBatchRelationDao() {
        return new HibernateBatchRelationDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                HibernateRole.class,
                HibernateUser.class,
                roleUserRelationMod
        );
    }
}
