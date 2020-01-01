package com.dwarfeng.rabcwr.impl.cache.formatter;

import com.dwarfeng.rabcwr.stack.bean.key.GuidKey;
import org.springframework.stereotype.Component;

@Component("guidKeyFormatter")
public class GuidKeyFormatter implements Formatter<GuidKey> {

    @Override
    public String format(String prefix, GuidKey target) {
        return prefix + target.getGuid();
    }
}
