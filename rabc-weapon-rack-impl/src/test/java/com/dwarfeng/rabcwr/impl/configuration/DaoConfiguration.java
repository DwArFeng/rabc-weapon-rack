package com.dwarfeng.rabcwr.impl.configuration;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePermission;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePexp;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.rabcwr.impl.dao.preset.PexpPresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.RolePresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.UserPresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.modifacation.RoleDeletionMod;
import com.dwarfeng.rabcwr.impl.dao.preset.modifacation.RoleUserRelationMod;
import com.dwarfeng.rabcwr.impl.dao.preset.modifacation.UserDeletionMod;
import com.dwarfeng.rabcwr.impl.dao.preset.modifacation.UserRoleRelationMod;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchRelationDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetDeleteDao;
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

    @Bean
    public HibernatePresetDeleteDao<LongIdKey, Pexp, HibernatePexp> pexpHibernatePresetDeleteDao() {
        return new HibernatePresetDeleteDao<>(
                template,
                beanTransformerConfiguration.pexpBeanTransformer(),
                HibernatePexp.class,
                new PexpPresetCriteriaMaker()
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Role, HibernateRole> roleDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.roleBeanTransformer(),
                HibernateRole.class,
                new RoleDeletionMod()
        );
    }

    @Bean
    public HibernatePresetDeleteDao<StringIdKey, Role, HibernateRole> roleHibernatePresetDeleteDao() {
        return new HibernatePresetDeleteDao<>(
                template,
                beanTransformerConfiguration.roleBeanTransformer(),
                HibernateRole.class,
                new RolePresetCriteriaMaker(),
                new RoleDeletionMod()
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                beanTransformerConfiguration.stringIdKeyBeanTransformer(),
                beanTransformerConfiguration.userBeanTransformer(),
                HibernateUser.class,
                new UserDeletionMod()
        );
    }

    @Bean
    public HibernatePresetDeleteDao<StringIdKey, User, HibernateUser> userHibernatePresetDeleteDao() {
        return new HibernatePresetDeleteDao<>(
                template,
                beanTransformerConfiguration.userBeanTransformer(),
                HibernateUser.class,
                new UserPresetCriteriaMaker(),
                new UserDeletionMod()
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
                new UserRoleRelationMod()
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
                new RoleUserRelationMod()
        );
    }
}
