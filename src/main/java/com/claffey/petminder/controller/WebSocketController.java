package com.claffey.petminder.controller;


import com.claffey.petminder.model.NotificationMessage;
import com.claffey.petminder.service.WebSocketService;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public NotificationMessage addListeners(@Payload NotificationMessage message, Principal principal) throws Exception {
        String username = message.getMessage();
        webSocketService.addUserName(username);
        Thread.sleep(1000); // simulated delay
//        String name = new Gson().fromJson(message., Map.class).get("name").toString();
        return new NotificationMessage("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
    }

}
