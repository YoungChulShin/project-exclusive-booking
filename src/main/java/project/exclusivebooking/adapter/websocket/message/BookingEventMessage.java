package project.exclusivebooking.adapter.websocket.message;


/**
 * 이벤트 타입
 * - 사용자 입장
 * - 예약 점유
 * 사용자 정보
 * 예약 정보
 */
public record BookingEventMessage(
    BookingEventType eventType,
    String userName,
    String ticketId
) {



  //
  // - 사용자 입장
  //
  //
}
