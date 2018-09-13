package com.lin.eesdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurationSupport;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket 必须的配置，如没有则报错
 */
@Configuration
class WebSocketConfig  {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}