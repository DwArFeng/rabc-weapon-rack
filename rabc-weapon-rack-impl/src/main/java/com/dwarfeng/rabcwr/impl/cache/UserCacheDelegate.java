package com.dwarfeng.rabcwr.impl.cache;

import com.dwarfeng.rabcwr.impl.bean.entity.JsonUser;
import com.dwarfeng.rabcwr.impl.cache.formatter.Formatter;
import com.dwarfeng.rabcwr.sdk.interceptor.TimeAnalyse;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import com.dwarfeng.rabcwr.stack.exception.CacheException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@Component
@Validated
public class UserCacheDelegate {

    @Autowired
    private RedisTemplate<String, JsonUser> template;
    @Autowired
    private Mapper mapper;
    @Autowired
    @Qualifier("idKeyFormatter")
    private Formatter<IdKey> formatter;

    @Value("${cache.prefix.entity.user}")
    private String keyPrefix;

    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    @TimeAnalyse
    public boolean exists(@NotNull IdKey key) throws CacheException {
        try {
            return template.hasKey(formatter.format(keyPrefix, key));
        } catch (Exception e) {
            throw new CacheException("缓存异常", e);
        }
    }

    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    @TimeAnalyse
    public User get(@NotNull IdKey key) throws CacheException {
        try {
            JsonUser jsonUser = template.opsForValue().get(formatter.format(keyPrefix, key));
            return mapper.map(jsonUser, User.class);
        } catch (Exception e) {
            throw new CacheException("缓存异常", e);
        }
    }

    @Transactional(transactionManager = "hibernateTransactionManager")
    @TimeAnalyse
    public void push(@NotNull IdKey key, @NotNull User user, @Min(0) long timeout) throws CacheException {
        try {
            JsonUser jsonUser = mapper.map(user, JsonUser.class);
            template.opsForValue().set(formatter.format(keyPrefix, key), jsonUser, timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new CacheException("缓存异常", e);
        }
    }

    @Transactional(transactionManager = "hibernateTransactionManager")
    @TimeAnalyse
    public void delete(@NotNull IdKey key) throws CacheException {
        try {
            template.delete(formatter.format(keyPrefix, key));
        } catch (Exception e) {
            throw new CacheException("缓存异常", e);
        }
    }
}
