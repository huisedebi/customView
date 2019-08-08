package com.xinyartech.baselibrary.utils.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * HttpResultGsonDeserializer.java
 */
public class HttpResultGsonDeserializer implements JsonDeserializer<HttpResult<?>> {

    @Override
    public HttpResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement jsonData = jsonObject.has("data") ? jsonObject.get("data") : null;
        HttpResult httpResult = new HttpResult();
        httpResult.setStatus(jsonObject.has("code") ? jsonObject.get("code").getAsInt() : 0);
        httpResult.setMsg(jsonObject.has("message") ? jsonObject.get("message").getAsString() : "");

        //处理Data空串情况
        if (jsonData != null && jsonData.isJsonObject()) {
            //指定泛型具体类型
            if (type instanceof ParameterizedType) {
                Type itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
                Object item = jsonDeserializationContext.deserialize(jsonData, itemType);
                httpResult.setResult(item);
            } else {
                //未指定泛型具体类型
                httpResult.setResult(jsonData);
            }
        } else if (jsonData != null && jsonData.isJsonArray()){
            //指定泛型具体类型
            if (type instanceof ParameterizedType) {
                try {
                    Type itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
                    Object item = jsonDeserializationContext.deserialize(jsonData, itemType);
                    httpResult.setResult(item);
                } catch (Exception e) {
                    httpResult.setResult(null);
                }
            } else {
                //未指定泛型具体类型
                httpResult.setResult(jsonData);
            }
        }

        return httpResult;
    }
}