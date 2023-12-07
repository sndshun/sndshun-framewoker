package com.sndshun.blog.annotation;

import com.sndshun.blog.enums.VisitEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author maple
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLog {

    /**
     * 访问类型
     *
     * @return 默认：未知
     */
    VisitEnum value() default VisitEnum.UNKNOWN;
}
