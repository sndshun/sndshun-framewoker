package com.sndshun.blog.exception;


import com.sndshun.commons.config.ResultCode;

/**
 * @author maple
 */
public class BusinessException extends RuntimeException {

    public ResultCode resultCode;

    public String errorMessage;

    public BusinessException(ResultCode errorCode, String errorMessage) {
        this.resultCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(ResultCode errorCode) {
        this.resultCode = errorCode;
        this.errorMessage = errorCode.getMsg();
    }

    public ResultCode getErrorCode() {
        return resultCode;
    }
}
