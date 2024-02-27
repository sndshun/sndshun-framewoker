package com.sndshun.blog.controller.endpoint;

import com.sndshun.blog.pojo.dto.ChatMessageDTO;
import com.sndshun.commons.tools.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

/**
 * 公开聊天室
 *
 * @author sndshun
 * @date 2024/02/27 01:30:34
 */
@Slf4j
@Controller
public class BlogPublicChatController {

    private static final List<ChatMessageDTO> messages = new LinkedList<>();

    public static synchronized void add(ChatMessageDTO element) {
        if (messages.size() >= 100) {
            // 如果队列已满，移除最旧的元素
            messages.remove(0);
        }
        // 添加新元素到队尾
        messages.add(element);
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessage) {
        if (chatMessage.getType() != ChatMessageDTO.MessageType.CHAT) return null;
        if (chatMessage.getSender() == null || chatMessage.getSender().isEmpty()) return null;
        if (chatMessage.getContent() == null || chatMessage.getContent().length() < 1 || chatMessage.getContent().length() > 2048)
            return null;

        add(chatMessage);
        log.info("接受到消息: {}", chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageDTO addUser(@Payload ChatMessageDTO chatMessage,
                                  SimpMessageHeaderAccessor headerAccessor) {
        if (chatMessage.getType() != ChatMessageDTO.MessageType.JOIN) return null;
        if (chatMessage.getSender() == null || chatMessage.getSender().isEmpty()) return null;
        if(chatMessage.getContent() != null && !chatMessage.getContent().isEmpty()) return null;
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        log.info("添加用户: {}", chatMessage.getSender());
        add(chatMessage);
        return chatMessage;
    }

    @GetMapping("/chat.public/history")
    @ResponseBody
    public Result<List<ChatMessageDTO>> history() {
        return Result.ok(messages);
    }
}