package com.dwarfeng.rabcwr.impl.service;

import com.dwarfeng.rabcwr.stack.bean.dto.PasswordInfo;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.service.RegisterService;
import com.dwarfeng.rabcwr.stack.service.UserMaintainService;
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
public class RegisterServiceImplTest {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserMaintainService userMaintainService;

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
            if (userMaintainService.exists(key)) {
                userMaintainService.delete(key);
            }
            registerService.register(registerInfo);
            assertTrue(registerService.checkPassword("root", "ninja123456"));
            assertFalse(registerService.checkPassword("root", "123456"));
            registerService.updatePassword(new PasswordInfo(
                    "root",
                    "ninja123456",
                    "qwerty123456"
            ));
            assertTrue(registerService.checkPassword("root", "qwerty123456"));
            assertFalse(registerService.checkPassword("root", "123456"));
        } finally {
            if (userMaintainService.exists(key)) {
                userMaintainService.delete(key);
            }
        }
    }
}