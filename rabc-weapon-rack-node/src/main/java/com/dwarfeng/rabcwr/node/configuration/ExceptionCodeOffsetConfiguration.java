package com.dwarfeng.rabcwr.node.configuration;

import com.dwarfeng.rabcwr.sdk.util.ServiceExceptionCodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ExceptionCodeOffsetConfiguration {

    @Value("${rabcwr.exception_code_offset}")
    private int exceptionCodeOffset;
    @Value("${rabcwr.exception_code_offset.subgrade}")
    private int subgradeExceptionCodeOffset;
    @Value("${rabcwr.exception_code_offset.snowflake}")
    private int snowflakeExceptionCodeOffset;

    @PostConstruct
    public void init() {
        ServiceExceptionCodes.setExceptionCodeOffset(exceptionCodeOffset);
        com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.setExceptionCodeOffset(subgradeExceptionCodeOffset);
        com.dwarfeng.sfds.sdk.util.ServiceExceptionCodes.setExceptionCodeOffset(snowflakeExceptionCodeOffset);
    }
}
