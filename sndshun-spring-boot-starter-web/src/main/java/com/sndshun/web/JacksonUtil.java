package com.sndshun.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

/**
 * json 工具类
 * @author sndshun
 * @date 2023/11/29 08:57:34
 */

public class JacksonUtil {
	private static ObjectMapper objectMapper =new ObjectMapper();
	/** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    static {//初始化
        //设置date属性
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(dateFormat);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GTM+8"));
        //当JSON数据的属性多于java对象属性时，会抛异常，这时，需要通过配置Jackson的Feature使能可以让你忽略那些多余的属性
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //设置LocalDateTime，LocalDate，LocalTime
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
         //空指针不报错
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
          //未知错误不报错
        objectMapper.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
         //支持null和空串
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        //字符串中允许单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        //表示字符串中也可以无引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
        //允许支持JSON数组中“缺失”值，数组中缺失了值表示两个逗号之间，啥都没有，形如这样[value1, , value3]。
        objectMapper.configure(JsonParser.Feature.ALLOW_MISSING_VALUES,true);
        //允许像00001这样的“数字”出现（而不报错）
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS,true);
        //允许**反斜杠**转义任何字符
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,true);
        //允许/* */或者//这种类型的注释出现。
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS,true);
        //支持Yaml格式的的注释，也就是#形式的注释语法。形如 {"table#abd":"test"}
        objectMapper.configure(JsonParser.Feature.ALLOW_YAML_COMMENTS,true);
         //支持结尾逗号{"name":"小明","age": "18",}
        objectMapper.configure(JsonParser.Feature.ALLOW_TRAILING_COMMA, true);
    }

    /** 对象转 json
     * @param o o
     * @return {@link String }
     * @author sndshun
     * @date 2023/11/29 08:57:52
     */
    public static String toJSONString(Object o) {
        String json =null;
        try {
            json =objectMapper.writeValueAsString(o);
        }catch (JsonProcessingException e){
            System.out.println(e.getMessage());
        }
        return json;
    }

    /** json格式或者JsonNode，ArrayNode，ObjectNode 转 Object
     * @param text  json
     * @param clazz bean类型
     * @return {@link T }
     * @author sndshun
     * @date 2023/11/29 08:58:32
     */
    public static <T> T parseObject(Object text,Class<T> clazz)   {
        if (text==null){
            return  null;
        }else {
            T json = null;
            try {
                if (text instanceof JsonNode){
                    T pojo =  objectMapper.convertValue(text,clazz);
                    return pojo;
                }
                if (text instanceof ArrayNode){
                    T pojo =  objectMapper.convertValue(text,clazz);
                    return pojo;
                }
                if (text instanceof ObjectNode){
                    T pojo =  objectMapper.convertValue(text,clazz);
                    return pojo;
                }
                if (text instanceof String){
                    String resut =text.toString();
                    json = objectMapper.readValue(resut, clazz);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return json;
        }
    }

    /** json 转集合
     * @param text  json
     * @param clazz bean 类型
     * @return {@link List }<{@link T }>
     * @author sndshun
     * @date 2023/11/29 08:59:46
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz)  {
        if (text==null){
            return  null;
        }else {
            try {
                return objectMapper.readValue(text, objectMapper.getTypeFactory().constructParametricType(List.class, clazz));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }
    }




}
