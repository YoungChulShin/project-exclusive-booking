package project.exclusivebooking.application.port.in;

import project.exclusivebooking.application.port.in.model.LockTicketCommand;

public interface LockTicketUseCase {

  boolean lockTicket(final LockTicketCommand command);
}
