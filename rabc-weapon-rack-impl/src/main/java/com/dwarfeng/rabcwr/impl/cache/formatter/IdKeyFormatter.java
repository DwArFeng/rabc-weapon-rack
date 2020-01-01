package com.dwarfeng.rabcwr.impl.cache.formatter;

import com.dwarfeng.rabcwr.stack.bean.key.IdKey;
import org.springframework.stereotype.Component;

@Component("idKeyFormatter")
public class IdKeyFormatter implements Formatter<IdKey> {

    @Override
    public String format(String prefix, IdKey target) {
        return prefix + target.getId();
    }
}
