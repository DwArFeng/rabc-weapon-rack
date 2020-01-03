package com.dwarfeng.rabcwr.api.dubbo.api.impl;

import com.dwarfeng.rabcwr.api.dubbo.api.RegisterApi;
import com.dwarfeng.rabcwr.stack.bean.dto.RegisterInfo;
import com.dwarfeng.rabcwr.stack.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    }
}