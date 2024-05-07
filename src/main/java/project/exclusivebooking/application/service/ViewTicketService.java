package project.exclusivebooking.application.service;

import org.springframework.stereotype.Service;
import project.exclusivebooking.application.exceptions.LeaveTicketFailException;
import project.exclusivebooking.application.exceptions.ViewTicketFailedException;
import project.exclusivebooking.application.port.in.ViewTicketUseCase;

@Service
class ViewTicketService implements ViewTicketUseCase {

  @Override
  public void view(String ticketId, String username) throws ViewTicketFailedException {
    // 티켓이 점유중인지 확인
    // 티켓 점유
  }

  @Override
  public void leave(String ticketId, String username) throws LeaveTicketFailException {

  }
}
