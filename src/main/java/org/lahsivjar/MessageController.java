package org.lahsivjar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    @Qualifier("clientOutboundChannel")
    private MessageChannel clientOutboundChannel;

    @MessageMapping("/text/ping")
    @SendTo("/topic/ping")
    public String textPong(@Payload String message) {
        return "pong";
    }

    @MessageMapping("/json/ping")
    @SendTo("/topic/ping")
    public Map<String, String> jsonPong(@Payload String message) {
        final Map<String, String> result = new HashMap<>();
        result.put("msg", "pong");
        return result;
    }

    @MessageMapping("/err")
    public void error(SimpMessageHeaderAccessor headerAccessor) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.create(StompCommand.ERROR);
        stompHeaderAccessor.setSessionId(headerAccessor.getSessionId());
        stompHeaderAccessor.setDestination("/topic/ping");
        clientOutboundChannel.send(MessageBuilder.createMessage(new byte[0], stompHeaderAccessor.getMessageHeaders()));
    }

}
