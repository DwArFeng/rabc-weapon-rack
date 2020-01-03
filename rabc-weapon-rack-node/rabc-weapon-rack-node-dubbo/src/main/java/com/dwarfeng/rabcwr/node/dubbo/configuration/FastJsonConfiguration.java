package com.dwarfeng.rabcwr.node.dubbo.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.impl.bean.entity.JsonUser");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.impl.bean.entity.JsonRole");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.impl.bean.entity.JsonPexp");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.impl.bean.entity.JsonPermission");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.impl.bean.entity.JsonLoginState");
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
