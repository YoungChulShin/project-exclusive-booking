package project.exclusivebooking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  private static final String STOMP_ENDPOINT_PATH = "/ws";

  private static final String MESSAGE_BROKER_RELAY_PREFIX = "/topic";
//  private static final String MESSAGE_BROKER_ERROR_PREFIX = "/error";
  private static final String MESSAGE_BROKER_APP_DESTINATION_PREFIX = "/app";

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker(
        MESSAGE_BROKER_RELAY_PREFIX);
    registry.setApplicationDestinationPrefixes(MESSAGE_BROKER_APP_DESTINATION_PREFIX);
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint(STOMP_ENDPOINT_PATH).withSockJS();
  }
}
