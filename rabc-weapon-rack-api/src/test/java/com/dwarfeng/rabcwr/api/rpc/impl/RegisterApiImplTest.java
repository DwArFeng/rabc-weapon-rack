package com.dwarfeng.rabcwr.api.rpc.impl;

import com.dwarfeng.rabcwr.api.rpc.RegisterApi;
import com.dwarfeng.rabcwr.api.rpc.UserMaintainApi;
import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
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
    private RegisterApi registerApi;
    @Autowired
    private UserMaintainApi userMaintainApi;

    @Test
    public void register() throws ServiceException {
        RegisterInfo registerInfo = new RegisterInfo(
                "root",
                "ROOT",
                "ninja123456",
                "这是用于测试的人员信息..."
        );
        StringIdKey key = new StringIdKey(registerInfo.getId());

        try {
            if (userMaintainApi.exists(key)) {
                userMaintainApi.delete(key);
            }
            registerApi.register(registerInfo);
            assertTrue(registerApi.checkPassword("root", "ninja123456"));
            assertFalse(registerApi.checkPassword("root", "123456"));
            registerApi.updatePassword(new PasswordInfo(
                    "root",
                    "ninja123456",
                    "qwerty123456"
            ));
            assertTrue(registerApi.checkPassword("root", "qwerty123456"));
            assertFalse(registerApi.checkPassword("root", "123456"));
        } finally {
            if (userMaintainApi.exists(key)) {
                userMaintainApi.delete(key);
            }
        }
    }
}