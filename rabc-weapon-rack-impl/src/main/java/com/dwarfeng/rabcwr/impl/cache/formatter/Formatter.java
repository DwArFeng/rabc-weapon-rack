package com.dwarfeng.rabcwr.impl.cache.formatter;

/**
 * @author DwArFeng
 * @since 0.0.1-alpha
 */
public interface Formatter<T> {

    String format(String prefix, T target);
}
