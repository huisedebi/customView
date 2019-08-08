package com.xinyartech.baselibrary.utils.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * IntegerGsonDeserializer.java
 */
public class IntegerGsonDeserializer implements JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        try {
            //默认后台返回"" 或者 "null"，则设置默认值为 0
            if (jsonElement.getAsString().equals("") || jsonElement.getAsString().equals("null")) {
                return 0;
            }
        } catch (Exception e) {
        }

        try {
            return jsonElement.getAsInt();
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }
}