package com.sndshun.blog.pojo.dto;

import lombok.Data;

import java.util.Date;

/** 消息体
 * @author sndshun
 * @date 2024/02/20 05:20:46
 */
@Data
public class ChatMessageDTO {
    //消息类型
    private MessageType type;
    //消息内容
    private String content;
    //发送者
    private String sender;
    //发送时间
    private Date date;
    //消息类型枚举
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}