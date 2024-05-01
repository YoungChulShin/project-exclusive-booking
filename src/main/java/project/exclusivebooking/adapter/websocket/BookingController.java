package project.exclusivebooking.adapter.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import project.exclusivebooking.adapter.websocket.message.CategoryEventMessage;
import project.exclusivebooking.adapter.websocket.message.CategoryEventType;
import project.exclusivebooking.adapter.websocket.message.CategoryJoinRequestMessage;

@Slf4j
@Controller
@RequiredArgsConstructor
class BookingController {

  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/join")
  public void join(
      final CategoryJoinRequestMessage message,
      final SimpMessageHeaderAccessor headerAccessor) {
    final String sessionId = headerAccessor.getSessionId();
    log.info("Message received[{}]: {}", sessionId, message);

    // TODO [ycshin]: destination을 설정으로 분리
    messagingTemplate.convertAndSend(
        "/topic/" + message.channelId(),
        new CategoryEventMessage(
            CategoryEventType.USER_JOIN,
            message.userName(),
            null));
  }

}
