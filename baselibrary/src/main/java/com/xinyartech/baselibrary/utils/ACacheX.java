/**
 * Copyright (c) 2012-2013, Michael Yang 杨福海 (www.yangfuhai.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed toGeRen in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xinyartech.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.xinyartech.baselibrary.utils.gson.GsonUtils;

import static android.content.Context.MODE_PRIVATE;

public class ACacheX {

    public static String getAsString(Context ctx, String cacheName, String key) {
        SharedPreferences sp = ctx.getSharedPreferences(cacheName, MODE_PRIVATE);
        String value = sp.getString(key, "");
        if (value.contains("@#@")) {
            String[] split = value.split("@#@");
            long time = Long.parseLong(split[1]);
            long l = System.currentTimeMillis();
            if ((time + Long.parseLong(split[2])) < l) {
                clear(ctx, cacheName);
                return "";
            }
            return split[0];
        }
        return value;
    }

    public static void putAsString(Context ctx, String cacheName, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static void putAsString(Context ctx, String cacheName, String key, String value, long time) {
        if (value.contains("@#@")) {
            throw new RuntimeException("存储的数据不能有@#@");
        }
        SharedPreferences sp = ctx.getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value + "@#@" + time + "@#@" + System.currentTimeMillis());
        edit.commit();
    }


    public static Object getAsObject(Context ctx, String cacheName, String key, Class aClass) {
        SharedPreferences sp = ctx.getSharedPreferences(cacheName, MODE_PRIVATE);
        String value = sp.getString(key, "");
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        if (value.contains("@#@")) {
            String[] split = value.split("@#@");
            long time = Long.parseLong(split[1]);
            long l = System.currentTimeMillis();
            if ((time + Long.parseLong(split[2])) < l) {
                clear(ctx, cacheName);
                return null;
            }
            return GsonUtils.parseJSON(split[0], aClass);
        }
        return GsonUtils.parseJSON(value, aClass);
    }

    public static void putAsObject(Context ctx, String cacheName, String key, Object value, long time) {
        String s = GsonUtils.parseObject(value);
        if (s.contains("@#@")) {
            throw new RuntimeException("存储的数据不能有@#@");
        }
        SharedPreferences sp = ctx.getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, s + "@#@" + time + "@#@" + System.currentTimeMillis());
        edit.commit();
    }

    public static void putAsObject(Context ctx, String cacheName, String key, Object value) {
        String s = GsonUtils.parseObject(value);
        if (s.contains("@#@")) {
            throw new RuntimeException("存储的数据不能有@#@");
        }
        SharedPreferences sp = ctx.getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, s );
        edit.commit();
    }


    public static void clear(Context ctx, String cacheName) {
        SharedPreferences sp = ctx.getSharedPreferences(cacheName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }
}
