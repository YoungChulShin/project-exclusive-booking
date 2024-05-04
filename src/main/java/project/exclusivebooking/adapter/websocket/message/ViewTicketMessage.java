package project.exclusivebooking.adapter.websocket.message;

public record ViewTicketMessage(
    String channelId,
    String ticketId,
    String userName
) {

}
