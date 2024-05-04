package project.exclusivebooking.application.port.in.model;

public record LockTicketCommand(
    String ticketId,
    String username
) {

}
