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
import project.exclusivebooking.application.port.in.ViewTicketUseCase;
import project.exclusivebooking.application.exceptions.ViewTicketFailedException;

@Slf4j
@Controller
@RequiredArgsConstructor
class TicketController {

  private final SimpMessagingTemplate messagingTemplate;
  private final ViewTicketUseCase viewTicketUseCase;

  @MessageMapping("/ticket/view")
  public void viewTicket(
      final ViewTicketMessage message,
      final SimpMessageHeaderAccessor headerAccessor) {
    log.info("Message received[{}]: {}", headerAccessor.getSessionId(), message);

    try {
      viewTicketUseCase.view(message.ticketId(), message.userName());

      messagingTemplate.convertAndSend(
          "/topic/" + message.channelId(),
          new BookingEventMessage(
              BookingEventType.VIEW_TICKET,
              message.userName(),
              new ViewTicketEventBody(message.ticketId())));
    } catch (ViewTicketFailedException viewTicketFailedException) {
      // TODO [ycshin]: 에러 전달
    }
  }
}
