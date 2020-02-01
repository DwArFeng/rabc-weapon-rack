package com.dwarfeng.rabcwr.node.configuration;

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
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.sdk.bean.entity.FastJsonUser");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.sdk.bean.entity.FastJsonRole");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.sdk.bean.entity.FastJsonPexp");
        ParserConfig.getGlobalInstance().addAccept("com.dwarfeng.rabcwr.sdk.bean.entity.FastJsonPermission");
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
