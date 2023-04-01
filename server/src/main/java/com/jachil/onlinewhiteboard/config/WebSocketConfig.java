package com.jachil.onlinewhiteboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

    @Configuration
    @EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override

    // TODO: MAYBE CHANGE REGISTRY TO CONFIG?
    // configureMessageBroker configures the message broker. It enables a simple memory-based message broker to carry the messages back to the client on destinations prefixed with "/topic".
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    // registerStompEndpoints registers the "/ws" endpoint, enabling SockJS fallback options so that alternate transports can be used if WebSocket is not available.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();

    }
}