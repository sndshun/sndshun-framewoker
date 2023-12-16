package com.sndshun.commons.config;


/**
 * Result状态码
 *
 * @author sndshun
 * @since 2023-11-16 20:29:08
 */
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "成功"),
    /*失败*/
    FAIL(201, "失败"),
    /*异常*/
    ERROR(500, "异常错误"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号已被禁用"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),

    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),
    SCHEDULE_RUN_SEARCH_ERROR(30002, "当前任务不存在"),
    SCHEDULE_RUN_ERROR(30003, "运行失败，请重试"),


    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /* 数据错误：50001-599999 */
    RESULT_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限"),

    /*文件相关错误：80001-89999*/
    OSS_BUCKET_EXIST(80001, "对象存储桶名称已存在"),
    OSS_BUCKET_IS_EXIST(80002, "对象存储桶名称不存在"),
    OSS_DEL_OK(80003, "删除存储桶成功"),
    OSS_DEL_ERROR(80004, "删除存储桶失败，请联系管理员"),
    OSS_UPLOAD_OK(80005, "上传成功"),
    OSS_UPLOAD_ERROR(80006, "上传失败"),
    OSS_SEARCH_BUCKET_ERROR(80007, "暂无数据"),
    OSS_FILE_NOT(80008, "文件不存在"),
    OSS_FILE_EMPTY(80009, "无法上传空文件"),
    OSS_FILE_NAME_ERROR(80010, "文件名不合法"),


    ;


    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

