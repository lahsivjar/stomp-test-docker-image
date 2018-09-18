package org.lahsivjar;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageController {

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
}
