package project.exclusivebooking.adapter.websocket.message.body;

public record ViewTicketEventBody(
    String ticketId
) implements BookingEventBody {

}
