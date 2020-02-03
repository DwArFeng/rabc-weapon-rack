package com.dwarfeng.rabcwr.api.integration.subgrade;

import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.bean.entity.LoginState;
import com.dwarfeng.rabcwr.stack.bean.entity.User;
import com.dwarfeng.rabcwr.stack.service.LoginService;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.rabcwr.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.ExceptionContext;
import com.dwarfeng.subgrade.sdk.interceptor.login.LoginRequired;
import com.dwarfeng.subgrade.sdk.interceptor.login.RequestLogin;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class LoginHandlerImplTest {

    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private AopTester aopTester;

    private RegisterInfo zhangSanReg;
    private RegisterInfo liSiReg;
    private RegisterInfo wangWuReg;

    @Before
    public void setUp() {
        zhangSanReg = new RegisterInfo("zhang_san", "张三", "ninja123456", "测试用账号");
        liSiReg = new RegisterInfo("li_si", "李四", "ninja123456", "测试用账号");
        wangWuReg = new RegisterInfo("wang_wu", "王五", "ninja123456", "测试用账号");
    }

    @After
    public void tearDown() {
        zhangSanReg = null;
        liSiReg = null;
        wangWuReg = null;
    }

    @Test
    public void test() throws Exception {
        User zhangSan = null;
        User liSi = null;
        User wangWu = null;
        try {
            zhangSan = registerService.register(zhangSanReg);
            liSi = registerService.register(liSiReg);
            wangWu = registerService.register(wangWuReg);

            LoginState loginState = loginService.login(zhangSan.getKey(), "ninja123456");
            //noinspection unused
            ExceptionContext exceptionContext = aopTester.test(loginState.getKey(), null);
            //noinspection UnusedAssignment
            exceptionContext = aopTester.test(new LongIdKey(loginState.getKey().getLongId() + 1), null);
            //noinspection UnusedAssignment
            exceptionContext = null;
            loginService.logout(loginState.getKey());
        } finally {
            if (Objects.nonNull(zhangSan)) userMaintainService.delete(zhangSan.getKey());
            if (Objects.nonNull(liSi)) userMaintainService.delete(liSi.getKey());
            if (Objects.nonNull(wangWu)) userMaintainService.delete(wangWu.getKey());
        }
    }

    @Component
    public static class AopTester {

        @LoginRequired(postpone = true)
        public ExceptionContext test(@RequestLogin LongIdKey loginId, ExceptionContext exceptionContext) {
            return exceptionContext;
        }
    }
}