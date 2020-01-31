package com.dwarfeng.rabcwr.impl.configuration;

import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePermission;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernatePexp;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateRole;
import com.dwarfeng.rabcwr.impl.bean.entity.HibernateUser;
import com.dwarfeng.rabcwr.impl.dao.preset.PermissionPresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.PexpPresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.RolePresetCriteriaMaker;
import com.dwarfeng.rabcwr.impl.dao.preset.UserPresetCriteriaMaker;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetDeleteDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    @Autowired
    private HibernateTemplate template;
    @Autowired
    private Mapper mapper;

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Permission, HibernatePermission> permissionDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(Permission.class, HibernatePermission.class, mapper),
                HibernatePermission.class);
    }

    @Bean
    public HibernatePresetDeleteDao<LongIdKey, Permission, HibernatePermission> permissionHibernatePresetDeleteDao() {
        return new HibernatePresetDeleteDao<>(
                template,
                new DozerBeanTransformer<>(Permission.class, HibernatePermission.class, mapper),
                HibernatePermission.class,
                new PermissionPresetCriteriaMaker()
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Pexp, HibernatePexp> pexpDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(Pexp.class, HibernatePexp.class, mapper),
                HibernatePexp.class);
    }

    @Bean
    public HibernatePresetDeleteDao<LongIdKey, Pexp, HibernatePexp> pexpHibernatePresetDeleteDao() {
        return new HibernatePresetDeleteDao<>(
                template,
                new DozerBeanTransformer<>(Pexp.class, HibernatePexp.class, mapper),
                HibernatePexp.class,
                new PexpPresetCriteriaMaker()
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, Role, HibernateRole> roleDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(Role.class, HibernateRole.class, mapper),
                HibernateRole.class);
    }

    @Bean
    public HibernatePresetDeleteDao<StringIdKey, Role, HibernateRole> roleHibernatePresetDeleteDao() {
        return new HibernatePresetDeleteDao<>(
                template,
                new DozerBeanTransformer<>(Role.class, HibernateRole.class, mapper),
                HibernateRole.class,
                new RolePresetCriteriaMaker()
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userDaoDelegate() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class);
    }

    @Bean
    public HibernatePresetDeleteDao<StringIdKey, User, HibernateUser> userHibernatePresetDeleteDao() {
        return new HibernatePresetDeleteDao<>(
                template,
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class,
                new UserPresetCriteriaMaker()
        );
    }
}
