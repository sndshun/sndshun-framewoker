package com.sndshun.commons.exception;


import com.sndshun.commons.config.ResultCode;
import com.sndshun.commons.tools.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理器
 *
 * @author maple
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理POST请求参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> validExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        return Result.error(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * HTTP 消息无法被读取
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> validExceptionHandler(HttpMessageNotReadableException e) {
        return Result.error(ResultCode.SYSTEM_INNER_ERROR);
    }


    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> validExceptionHandler(BusinessException e) {
        return Result.error(e.resultCode);
    }

    /**
     * 异常主题
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(Exception.class)
    public Result<?> validExceptionHandler(Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        return Result.error(ResultCode.ERROR);
    }
}

