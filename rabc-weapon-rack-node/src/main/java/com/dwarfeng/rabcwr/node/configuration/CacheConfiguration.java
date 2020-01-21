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
import com.dwarfeng.subgrade.impl.cache.RedisStringJsonBaseCache;
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
    private RedisTemplate<String, Object> template;
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
    public RedisStringJsonBaseCache<LongIdKey, Permission, FastJsonPermission> permissionCacheDelegate() {
        return new RedisStringJsonBaseCache<>(
                template,
                new LongIdStringKeyFormatter(permissionPrefix),
                new DozerBeanTransformer<>(Permission.class, FastJsonPermission.class, mapper)
        );
    }

    @Bean
    public RedisStringJsonBaseCache<LongIdKey, Pexp, FastJsonPexp> pexpCacheDelegate() {
        return new RedisStringJsonBaseCache<>(
                template,
                new LongIdStringKeyFormatter(pexpPrefix),
                new DozerBeanTransformer<>(Pexp.class, FastJsonPexp.class, mapper)
        );
    }

    @Bean
    public RedisStringJsonBaseCache<StringIdKey, Role, FastJsonRole> roleCacheDelegate() {
        return new RedisStringJsonBaseCache<>(
                template,
                new StringIdStringKeyFormatter(rolePrefix),
                new DozerBeanTransformer<>(Role.class, FastJsonRole.class, mapper)
        );
    }

    @Bean
    public RedisStringJsonBaseCache<StringIdKey, User, FastJsonUser> userCacheDelegate() {
        return new RedisStringJsonBaseCache<>(
                template,
                new StringIdStringKeyFormatter(userPrefix),
                new DozerBeanTransformer<>(User.class, FastJsonUser.class, mapper)
        );
    }
}
