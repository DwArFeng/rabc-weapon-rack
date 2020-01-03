package com.dwarfeng.rabcwr.api.dubbo.api.impl;

import com.dwarfeng.rabcwr.api.dubbo.api.RegisterApi;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class RegisterApiImplTest {

    @Autowired
    private RegisterApi api;

    @Test
    public void register() throws ServiceException {
        RegisterInfo registerInfo = new RegisterInfo(
                "root",
                "ROOT",
                "ninja123456",
                "这是用于测试的人员信息..."
        );
        api.register(registerInfo);
        assertTrue(api.checkPassword("root", "ninja123456"));
        assertFalse(api.checkPassword("root", "123456"));
        api.updatePassword(new PasswordInfo(
                "root",
                "ninja123456",
                "qwerty123456"
        ));
        assertTrue(api.checkPassword("root", "qwerty123456"));
        assertFalse(api.checkPassword("root", "123456"));
    }
}