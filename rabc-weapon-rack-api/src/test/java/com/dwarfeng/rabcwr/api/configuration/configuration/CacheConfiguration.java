package com.dwarfeng.rabcwr.api.configuration.configuration;

import com.dwarfeng.rabcwr.sdk.bean.entity.*;
import com.dwarfeng.rabcwr.stack.bean.entity.*;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.impl.cache.RedisKeyListCache;
import com.dwarfeng.subgrade.impl.cache.RedisListCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
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
    @Value("${cache.key.list.permission}")
    private String permissionListKey;
    @Value("${cache.prefix.list.user_has_permission}")
    private String userPermissionListKey;

    @Bean
    public RedisBatchBaseCache<StringIdKey, Permission, FastJsonPermission> permissionCacheDelegate() {
        //noinspection unchecked
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPermission>) template,
                new StringIdStringKeyFormatter(permissionPrefix),
                new DozerBeanTransformer<>(Permission.class, FastJsonPermission.class, mapper)
        );
    }

    @Bean
    public RedisListCache<Permission, FastJsonPermission> permissionRedisListCache() {
        //noinspection unchecked
        return new RedisListCache<>(
                permissionListKey,
                (RedisTemplate<String, FastJsonPermission>) template,
                new DozerBeanTransformer<>(Permission.class, FastJsonPermission.class, mapper)
        );
    }

    @Bean
    public RedisBatchBaseCache<LongIdKey, Pexp, FastJsonPexp> pexpCacheDelegate() {
        //noinspection unchecked
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPexp>) template,
                new LongIdStringKeyFormatter(pexpPrefix),
                new DozerBeanTransformer<>(Pexp.class, FastJsonPexp.class, mapper)
        );
    }

    @Bean
    public RedisBatchBaseCache<StringIdKey, Role, FastJsonRole> roleCacheDelegate() {
        //noinspection unchecked
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonRole>) template,
                new StringIdStringKeyFormatter(rolePrefix),
                new DozerBeanTransformer<>(Role.class, FastJsonRole.class, mapper)
        );
    }

    @Bean
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userCacheDelegate() {
        //noinspection unchecked
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new DozerBeanTransformer<>(User.class, FastJsonUser.class, mapper)
        );
    }

    @Bean
    public RedisKeyListCache<StringIdKey, Permission, FastJsonPermission> userPermissionRedisKeyListCache() {
        //noinspection unchecked
        return new RedisKeyListCache<>(
                (RedisTemplate<String, FastJsonPermission>) template,
                new StringIdStringKeyFormatter(userPermissionListKey),
                new DozerBeanTransformer<>(Permission.class, FastJsonPermission.class, mapper)
        );
    }

    @Value("${cache.prefix.entity.login_state}")
    private String loginStatePrefix;

    @Bean
    public RedisBatchBaseCache<LongIdKey, LoginState, FastJsonLoginState> loginStateRedisBatchBaseCache() {
        //noinspection unchecked
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonLoginState>) template,
                new LongIdStringKeyFormatter(loginStatePrefix),
                new DozerBeanTransformer<>(LoginState.class, FastJsonLoginState.class, mapper)
        );
    }
}