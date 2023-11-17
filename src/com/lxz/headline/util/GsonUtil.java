package com.lxz.headline.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonUtil{
    private static Gson gson;

    static {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    /**
     * 将类转化为json格式
     * @param object 类对象
     * @return
     */
    public static String toJson(Object object){
        if(null == object){
            return "";
        }
        return gson.toJson(object);
    }

    public static<T> T fromJson(String json,Class<T> clazz){

        return gson.fromJson(json,clazz);
    }
    /**
     * 将json格式转换为List
     * @param json json字符串
     * @return
     * @param <T>
     */
    public static<T> List<T> toList(String json){
        if (null == json) {
            return null;
        }
        Type type = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(json,type);
    }

    /**
     * 将json格式转换为Map
     * @param json json字符串
     * @return
     * @param <T>
     */
    public static<T> Map<T,T> toMap(String json){
        if (null == json) {
            return null;
        }
        Type type = new TypeToken<Map<T,T>>(){}.getType();
        return gson.fromJson(json,type);
    }

}
