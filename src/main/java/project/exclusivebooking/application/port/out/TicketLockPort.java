package project.exclusivebooking.application.port.out;

public interface TicketLockPort {

  void lockTicket(final String ticketId, final String username, final long lockMinutes);

  void releaseTicket(final String ticketId, final String username);

}
