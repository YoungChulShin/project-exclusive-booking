package project.exclusivebooking.adapter.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import project.exclusivebooking.adapter.websocket.message.BookingEventMessage;
import project.exclusivebooking.adapter.websocket.message.BookingEventType;
import project.exclusivebooking.adapter.websocket.message.CategoryJoinMessage;
import project.exclusivebooking.adapter.websocket.message.ViewTicketMessage;
import project.exclusivebooking.adapter.websocket.message.body.ViewTicketEventBody;

@Slf4j
@Controller
@RequiredArgsConstructor
class BookingController {

  private final SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/category/join")
  public void joinCategory(
      final CategoryJoinMessage message,
      final SimpMessageHeaderAccessor headerAccessor) {
    log.info("Message received[{}]: {}", headerAccessor.getSessionId(), message);

    // TODO [ycshin]: destination을 설정으로 분리
    messagingTemplate.convertAndSend(
        "/topic/" + message.channelId(),
        new BookingEventMessage(
            BookingEventType.CATEGORY_JOIN,
            message.userName(),
            null));
  }

  @MessageMapping("/category/ticket/view")
  public void viewTicket(
      final ViewTicketMessage message,
      final SimpMessageHeaderAccessor headerAccessor) {
    log.info("Message received[{}]: {}", headerAccessor.getSessionId(), message);

    // TODO [ycshin]: 점유가 가능한지 확인

    // 점유 내용 공유
    messagingTemplate.convertAndSend(
        "/topic/" + message.channelId(),
        new BookingEventMessage(
            BookingEventType.VIEW_TICKET,
            message.userName(),
            new ViewTicketEventBody(message.ticketId())));
  }



}
