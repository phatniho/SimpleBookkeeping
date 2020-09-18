package com.ph.simplebookkeeping.util;

import java.util.Date;

public class Json {
    /**
     * 成功处理200
     */
    public static final Integer CODE_DEAL_SUCCESS = 200;

    /**
     * 已知错误异常506
     */
    public static final Integer CODE_KNOWN_ERROR = 506;

    /**
     * 未知错误异常500
     */
    public static final Integer CODE_UNKNOWN_ERROR = 500;

    /**
     * 未登陆，或者登陆过期
     */
    public static final Integer CODE_AUTH_FAIL = 401;


    private int code;

    private String msg;

    private Object data;

    private String error;

    private Long timestamp;


    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public String toJsonStr() {
        return JacksonUtil.toJson(this);
    }

    public Json() {
        super();
    }

    public Json(int code, String message, Object data, String error, Long timestamp) {
        super();
        this.code = code;
        this.msg = message;
        this.data = data;
        this.error = error;
        this.timestamp = timestamp;
    }


    /**
     * 处理成功
     *
     * @return 200
     */
    public static Json success() {
        return new Json(Json.CODE_DEAL_SUCCESS, "成功", null, null, new Date().getTime());
    }

    /**
     * 处理成功
     *
     * @param data
     * @return 200
     */
    public static Json success(Object data) {
        return new Json(Json.CODE_DEAL_SUCCESS, "成功", data, null, new Date().getTime());
    }

    /**
     * 未知异常错误
     *
     * @param msg
     * @return 500
     */
    public static Json unKnownFail(String msg) {
        return new Json(Json.CODE_UNKNOWN_ERROR, msg, null, null, new Date().getTime());
    }

    /**
     * 未知异常错误
     *
     * @param msg
     * @return 500
     */
    public static Json unKnownFail(String msg, String error) {
        return new Json(Json.CODE_UNKNOWN_ERROR, msg, null, error, new Date().getTime());
    }

    /**
     * 已知异常错误
     *
     * @param msg
     * @return 506
     */
    public static Json knownFail(String msg) {
        return new Json(Json.CODE_KNOWN_ERROR, msg, null, null, new Date().getTime());
    }

    /**
     * 已知异常错误
     *
     * @param msg
     * @return 506
     */
    public static Json knownFail(String msg, String error) {
        return new Json(Json.CODE_KNOWN_ERROR, msg, null, error, new Date().getTime());
    }

    /**
     * 授权失效
     *
     * @param msg
     * @return 506
     */
    public static Json authFail(String msg, String error) {
        return new Json(Json.CODE_AUTH_FAIL, msg, null, error, new Date().getTime());
    }

    /**
     * 授权失效
     *
     * @param msg
     * @return 506
     */
    public static Json authFail(String msg) {
        return new Json(Json.CODE_AUTH_FAIL, msg, null, null, new Date().getTime());
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


}
