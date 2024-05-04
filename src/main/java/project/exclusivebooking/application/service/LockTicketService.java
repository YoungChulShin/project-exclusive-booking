package project.exclusivebooking.application.service;

import org.springframework.stereotype.Service;
import project.exclusivebooking.application.port.in.LockTicketUseCase;
import project.exclusivebooking.application.port.in.model.LockTicketCommand;

@Service
class LockTicketService implements LockTicketUseCase {

  @Override
  public boolean lockTicket(LockTicketCommand command) {
    return false;
  }
}
