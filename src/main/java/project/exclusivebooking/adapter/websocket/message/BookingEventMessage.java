package project.exclusivebooking.adapter.websocket.message;


import project.exclusivebooking.adapter.websocket.message.body.BookingEventBody;

public record BookingEventMessage(
    BookingEventType eventType,
    String userName,
    BookingEventBody body
) {
}