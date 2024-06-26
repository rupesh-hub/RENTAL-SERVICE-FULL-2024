package com.rentme.app.resource;

import com.rentme.app.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageResource {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/application") //mapped as: /app/application
    @SendTo("/all/messages")
    public Message send(final Message message) throws Exception {
        return message;
    }

    @MessageMapping("/private") //mapped as: app/private
    public void sendToUser(@Payload Message message){
        messagingTemplate.convertAndSendToUser(message.to(), "/specific", message);
    }

}
