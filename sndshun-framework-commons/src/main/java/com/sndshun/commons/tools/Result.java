package com.sndshun.commons.tools;

import com.sndshun.commons.config.ResultCode;

import java.io.Serializable;

/**
 * 统一响应返回体
 *
 * @author sndshun
 * @since 2023-11-16 20:29:08
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private T data;

    private Result() {
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return new Result<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    /**
     * 成功返回自定义信息
     *
     * @param resultCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(ResultCode resultCode, T data) {
        Result<T> r = new Result<T>(resultCode.SUCCESS.getCode(), resultCode.SUCCESS.getMsg(), data);
        return r;
    }

    /**
     * 返回错误
     *
     * @param resultCode
     * @return
     */
    public static Result error(ResultCode resultCode) {
        Result r = new Result();
        r.setCode(resultCode.getCode());
        r.setMessage(resultCode.getMsg());
        return r;
    }

    /**
     * 自定义异常信息
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        Result r = new Result();
        r.setCode(ResultCode.FAIL.getCode());
        r.setMessage(msg);
        return r;
    }

    //链式编程   Result.result().data(..).code(..)
    public static <T> Result<T> result() {
        return new Result<T>();
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public Result message(String msg) {
        this.message = msg;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
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
