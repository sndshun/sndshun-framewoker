package com.sndshun.commons.tools;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Objects;

/**
 * 时间相关工具类
 *
 * @author mapleie
 */
public class DateUtils {

    /**
     * 注意时间对应的时区和默认时区差异
     *
     * @param zonedDateTime 时间
     * @return Date
     */
    public static Date toDate(ZonedDateTime zonedDateTime) {
        Objects.requireNonNull(zonedDateTime, "zonedDateTime");
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * date转换成年月日 时分秒
     *
     * @param date 时间
     * @return 年月日 时分秒
     */
    public static String dateToStringYyyyMMddHHMmSs(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
