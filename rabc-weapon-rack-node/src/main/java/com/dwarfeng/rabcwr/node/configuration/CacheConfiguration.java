package com.dwarfeng.rabcwr.node.configuration;

import com.dwarfeng.rabcwr.impl.bean.entity.FastJsonPermission;
import com.dwarfeng.rabcwr.impl.bean.entity.FastJsonPexp;
import com.dwarfeng.rabcwr.impl.bean.entity.FastJsonRole;
import com.dwarfeng.rabcwr.impl.bean.entity.FastJsonUser;
import com.dwarfeng.rabcwr.stack.bean.entity.Permission;
import com.dwarfeng.rabcwr.stack.bean.entity.Pexp;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBaseCache;
import com.dwarfeng.subgrade.impl.cache.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.impl.cache.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    @Autowired
    private RedisTemplate<String, ?> template;
    @Autowired
    private Mapper mapper;

    @Value("${cache.prefix.entity.permission}")
    private String permissionPrefix;
    @Value("${cache.prefix.entity.pexp}")
    private String pexpPrefix;
    @Value("${cache.prefix.entity.role}")
    private String rolePrefix;
    @Value("${cache.prefix.entity.user}")
    private String userPrefix;

    @Bean
    public RedisBaseCache<LongIdKey, Permission, FastJsonPermission> permissionCacheDelegate() {
        //noinspection unchecked
        return new RedisBaseCache<>(
                (RedisTemplate<String, FastJsonPermission>) template,
                new LongIdStringKeyFormatter(permissionPrefix),
                new DozerBeanTransformer<>(Permission.class, FastJsonPermission.class, mapper)
        );
    }

    @Bean
    public RedisBaseCache<LongIdKey, Pexp, FastJsonPexp> pexpCacheDelegate() {
        //noinspection unchecked
        return new RedisBaseCache<>(
                (RedisTemplate<String, FastJsonPexp>) template,
                new LongIdStringKeyFormatter(pexpPrefix),
                new DozerBeanTransformer<>(Pexp.class, FastJsonPexp.class, mapper)
        );
    }

    @Bean
    public RedisBaseCache<StringIdKey, Role, FastJsonRole> roleCacheDelegate() {
        //noinspection unchecked
        return new RedisBaseCache<>(
                (RedisTemplate<String, FastJsonRole>) template,
                new StringIdStringKeyFormatter(rolePrefix),
                new DozerBeanTransformer<>(Role.class, FastJsonRole.class, mapper)
        );
    }

    @Bean
    public RedisBaseCache<StringIdKey, User, FastJsonUser> userCacheDelegate() {
        //noinspection unchecked
        return new RedisBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new DozerBeanTransformer<>(User.class, FastJsonUser.class, mapper)
        );
    }
}
