package project.exclusivebooking.application.service;

import org.springframework.stereotype.Service;
import project.exclusivebooking.application.exceptions.LeaveTicketFailException;
import project.exclusivebooking.application.exceptions.ViewTicketFailedException;
import project.exclusivebooking.application.port.in.ViewTicketUseCase;
import project.exclusivebooking.application.port.out.TicketLockPort;

@Service
class ViewTicketService implements ViewTicketUseCase {

  private static final long TICKET_LOCK_SECONDS = 180;

  private final TicketLockPort ticketLockPort;

  @Override
  public void view(String ticketId, String username) {
    ticketLockPort.lockTicket(ticketId, username, TICKET_LOCK_SECONDS);
  }

  @Override
  public void leave(String ticketId, String username) {
    ticketLockPort.releaseTicket(ticketId, username);
  }
}
