package project.exclusivebooking.adapter.websocket.message;

public record CategoryJoinMessage(
    String channelId,
    String userName
) {

}
