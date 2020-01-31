package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.Role;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.rabcwr.stack.service.RoleMaintainService;
import com.dwarfeng.rabcwr.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class RoleMaintainServiceImplTest {

    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private RoleMaintainService roleMaintainService;
    @Autowired
    private RegisterService registerService;

    private RegisterInfo zhangSanReg;
    private RegisterInfo liSiReg;
    private RegisterInfo wangWuReg;

    private Role admin;
    private Role guest;
    private Role moderator;

    @Before
    public void setUp() {
        zhangSanReg = new RegisterInfo("zhang_san", "张三", "ninja123456", "测试用账号");
        liSiReg = new RegisterInfo("li_si", "李四", "ninja123456", "测试用账号");
        wangWuReg = new RegisterInfo("wang_wu", "王五", "ninja123456", "测试用账号");
        admin = new Role(new StringIdKey("admin"), true, "测试用角色");
        guest = new Role(new StringIdKey("guest"), false, "测试用角色");
        moderator = new Role(new StringIdKey("moderator"), true, "测试用角色");
    }

    @After
    public void tearDown() {
        zhangSanReg = null;
        liSiReg = null;
        wangWuReg = null;
        admin = null;
        guest = null;
        moderator = null;
    }

    @Test
    public void test() throws ServiceException {
        User zhangSan = null;
        User liSi = null;
        User wangWu = null;
        try {
            zhangSan = registerService.register(zhangSanReg);
            liSi = registerService.register(liSiReg);
            wangWu = registerService.register(wangWuReg);
            roleMaintainService.insert(admin);
            roleMaintainService.insert(guest);
            roleMaintainService.insert(moderator);
            userMaintainService.addRoles(zhangSan.getKey(), Arrays.asList(admin.getKey(), moderator.getKey(), guest.getKey()));
            userMaintainService.addRoles(liSi.getKey(), Arrays.asList(moderator.getKey(), guest.getKey()));
            userMaintainService.addRoles(wangWu.getKey(), Collections.singletonList(guest.getKey()));

            //此处用断点观测roles的值，判断是否正确。
            //noinspection unused
            List<Role> roles = roleMaintainService.lookup(RoleMaintainService.ROLE_FOR_USER, new Object[]{zhangSan.getKey()}).getData();
            //noinspection UnusedAssignment
            roles = roleMaintainService.lookup(RoleMaintainService.ROLE_FOR_USER, new Object[]{liSi.getKey()}).getData();
            //noinspection UnusedAssignment
            roles = roleMaintainService.lookup(RoleMaintainService.ROLE_FOR_USER, new Object[]{wangWu.getKey()}).getData();
            //noinspection UnusedAssignment
            roles = roleMaintainService.lookup(RoleMaintainService.ENABLED_ROLE_FOR_USER, new Object[]{zhangSan.getKey()}).getData();
            //noinspection UnusedAssignment
            roles = roleMaintainService.lookup(RoleMaintainService.ENABLED_ROLE_FOR_USER, new Object[]{liSi.getKey()}).getData();
            //noinspection UnusedAssignment
            roles = roleMaintainService.lookup(RoleMaintainService.ENABLED_ROLE_FOR_USER, new Object[]{wangWu.getKey()}).getData();
            //noinspection UnusedAssignment
            roles = null;
        } finally {
            if (Objects.nonNull(zhangSan)) userMaintainService.delete(zhangSan.getKey());
            if (Objects.nonNull(liSi)) userMaintainService.delete(liSi.getKey());
            if (Objects.nonNull(wangWu)) userMaintainService.delete(wangWu.getKey());
            roleMaintainService.delete(admin.getKey());
            roleMaintainService.delete(moderator.getKey());
            roleMaintainService.delete(guest.getKey());
        }
    }
}