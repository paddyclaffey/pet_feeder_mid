package com.claffey.petminder.controller;


import com.claffey.petminder.model.NotificationMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class WebsocketController {

    @MessageMapping("/message")
    @SendTo("/topic/message")
    public NotificationMessage greeting(NotificationMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new NotificationMessage("Hello, " + HtmlUtils.htmlEscape(message.getMessage()) + "!");
    }

}
