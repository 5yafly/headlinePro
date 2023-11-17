package com.lxz.headline.common;

/**
 * 响应规范格式类
 * @param <T>
 */
public class Result<T>{

    private Integer code;

    private String message;

    private T data;

    public  Result(){}

    /**
     * 初始化只包含数据的result对象
     * @param data 数据
     * @return 返回result对象
     * @param <T>
     */
    protected static <T> Result<T> build(T data){
        Result<T> result = new Result<T>();
        if (null != data) {
            result.setData(data);
        }
        return result;
    }

    /**
     * 初始化所有属性的result对象
     * @param data 数据
     * @param code 状态码
     * @param message   状态码描述
     * @return 返回result对象
     * @param <T>
     */
    protected static <T> Result<T> build(T data,Integer code,String message){
        Result<T> result = build(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 初始化所有属性的result对象
     * @param data 数据
     * @param resultCodeEnum 状态码枚举类
     * @return 返回result对象
     * @param <T>
     */
    protected static <T> Result<T> build(T data,ResultCodeEnum resultCodeEnum){
        Result<T> result = build((data));
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 初始化状态码是200，并包含数据的result对象
     * @param data 数据
     * @return 返回result对象
     * @param <T>
     */
    protected static <T> Result<T> ok(T data){
        return build(data,ResultCodeEnum.SUCCESS);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
