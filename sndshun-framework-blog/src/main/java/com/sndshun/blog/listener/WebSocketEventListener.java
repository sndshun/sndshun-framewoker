package com.sndshun.blog.listener;

import com.sndshun.blog.controller.endpoint.BlogPublicChatController;
import com.sndshun.blog.pojo.dto.ChatMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.annotation.Resource;

@Component
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
    @Resource
    private SimpMessageSendingOperations messagingTemplate;
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            logger.info("用户退出 : " + username);
            ChatMessageDTO chatMessage = new ChatMessageDTO();
            chatMessage.setType(ChatMessageDTO.MessageType.LEAVE);
            chatMessage.setSender(username);
            BlogPublicChatController.add(chatMessage);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
