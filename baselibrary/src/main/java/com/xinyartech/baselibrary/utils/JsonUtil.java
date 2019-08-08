package com.xinyartech.baselibrary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * 作者: created by zcm on 2018/9/27
 * 修改: modified by zcm on 2018/9/27
 * 描述: desc(json工具类)
 */
public class JsonUtil {
    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {

            gson = new GsonBuilder().
                    registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

                        @Override
                        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                            if (src == src.longValue())
                                return new JsonPrimitive(src.longValue());
                            return new JsonPrimitive(src);
                        }
                    }).create();
        }
        return gson;
    }
}
