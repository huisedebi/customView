package com.xinyartech.baselibrary.utils.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * GenericType.java
 */
public class GenericType<T> {

    private final Type type;

    protected  GenericType(){
        Type superClass = getClass().getGenericSuperclass();

        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}