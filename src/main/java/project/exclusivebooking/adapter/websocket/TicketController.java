package project.exclusivebooking.adapter.websocket;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import project.exclusivebooking.adapter.websocket.message.BookingEventMessage;
import project.exclusivebooking.adapter.websocket.message.BookingEventType;
import project.exclusivebooking.adapter.websocket.message.ViewTicketMessage;
import project.exclusivebooking.adapter.websocket.message.body.ViewTicketEventBody;
import project.exclusivebooking.application.port.in.LockTicketUseCase;
import project.exclusivebooking.application.port.in.model.LockTicketCommand;

@Slf4j
@Controller
@RequiredArgsConstructor
class TicketController {

  private final SimpMessagingTemplate messagingTemplate;
  private final LockTicketUseCase lockTicketUseCase;

  @MessageMapping("/category/ticket/view")
  public void viewTicket(
      final ViewTicketMessage message,
      final SimpMessageHeaderAccessor headerAccessor) {
    log.info("Message received[{}]: {}", headerAccessor.getSessionId(), message);

    // TODO [ycshin]: 점유가 가능한지 확인
    boolean locked =
        lockTicketUseCase.lockTicket(new LockTicketCommand(message.ticketId(), message.userName()));

    // 점유 내용 공유
    if (locked) {
      messagingTemplate.convertAndSend(
          "/topic/" + message.channelId(),
          new BookingEventMessage(
              BookingEventType.VIEW_TICKET,
              message.userName(),
              new ViewTicketEventBody(message.ticketId())));
    } else {
      // TODO [ycshin]: 에러 전달
    }

  }
}
