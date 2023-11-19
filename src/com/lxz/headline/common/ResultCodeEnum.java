package com.lxz.headline.common;

public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    ERROR(501,"失败"),
    NOT_LOGIN(502,"未登录"),
    ACCOUNTREPEAT(503,"账号重复");

    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
