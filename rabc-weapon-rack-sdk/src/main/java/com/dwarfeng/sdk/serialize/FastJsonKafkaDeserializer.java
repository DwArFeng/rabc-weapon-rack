package com.dwarfeng.sdk.serialize;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.sdk.util.Constants;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * 使用FastJson进行序列化的Redis序列化器。
 *
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public class FastJsonKafkaDeserializer<T> implements Deserializer<T> {

    private Class<T> clazz;

    public FastJsonKafkaDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, Constants.DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz);
    }
}
