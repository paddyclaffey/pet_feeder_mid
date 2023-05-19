package com.claffey.petminder.websocket;


import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;

public class WebSocketInterceptor implements ChannelInterceptor {

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("Channel Interceptor");

        MessageHeaders headers = message.getHeaders();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        MultiValueMap<String, String> multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS,MultiValueMap.class);
        if (multiValueMap != null) {
            for (Map.Entry<String, List<String>> entry : multiValueMap.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();
                System.out.println(key + "#" + values);
            }
        }

        // Retrieve the principal object from the message headers
//        Authentication authentication = accessor.getUser();
        Principal principal = accessor.getUser();

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            // Retrieve the authentication object from SecurityContextHolder
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Access the principal (user object) and username from authentication
            Object userObject = authentication.getPrincipal();
            String username = authentication.getName();

            // Perform further processing with the user object or username
        }

        return message;
    }
}