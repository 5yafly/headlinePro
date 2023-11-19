package com.lxz.headline.util;

import com.lxz.headline.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 对请求的json格式数据进行处理
 * 将后端数据转json通过响应发送出去
 */
public class WebUtil {
    /**
     * 将请求中的json字符串转换为相对应实体类
     * @param req 请求对象
     * @param clazz 实体类
     * @return
     * @param <T>
     */
    public static<T> T  readRequest(HttpServletRequest req,Class<T> clazz) {
        T t = null;
        BufferedReader reader = null;
        try {
            reader = req.getReader();
            StringBuffer stringBuffer = new StringBuffer();
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuffer.append(s);
            }
            t = GsonUtil.fromJson(stringBuffer.toString(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    /**
     * 将响应规范类转化为json串并传入响应发送出去
     * @param resp 响应
     * @param result    响应规范类对象
     */
    public static void writeResponse(HttpServletResponse resp, Result result){
        resp.setContentType("application/json;charset=UTF-8");
        String info = GsonUtil.toJson(result);
        System.out.println(info);
        try {
            resp.getWriter().write(info);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
