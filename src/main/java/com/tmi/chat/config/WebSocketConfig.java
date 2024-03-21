package com.tmi.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // WebSocket 메시지 핸들링 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 웹소캣 핸드쉐이크 커넥션을 생성할 경로
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트에서 보낸 메시지를 받을 prefix -> @MessageMapping 메서드로 라우팅
        // 상황에 따라 곧바로 브로커로 가는게 아니라, 메시지의 어떤 처리나 가공이 필요할 때 핸들러를 거쳐가도록 설정 가능
        registry.setApplicationDestinationPrefixes("/app");
        // 해당 주소를 구독하고 있는 클라이언트에게 메시지를 전달 -> 해당 경로를 구독하는 클라이언트에게 메시지 전달
        // /queue : 1:1 채팅
        // /topic : 1:N 채팅
        registry.enableSimpleBroker("/queue", "/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat") // Socket을 연결할 주소
                .setAllowedOrigins("*"); // CORS 허용
//                .withSockJS()
    }

}
