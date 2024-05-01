package project.exclusivebooking.adapter.websocket.message;

public record CategoryJoinRequestMessage(
    String channelId,
    String userName
) {

}
