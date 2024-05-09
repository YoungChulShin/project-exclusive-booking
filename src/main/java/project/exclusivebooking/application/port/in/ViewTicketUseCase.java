package project.exclusivebooking.application.port.in;

public interface ViewTicketUseCase {

  /**
   * Ticket을 확인합니다. Ticket은 동시에 1명만 볼 수 있기 때문에, 다른 누군가가 보고 있다면 실패할 수 있습니다.
   * @param ticketId 티켓 아이디
   * @param username 티켓을 보려는 사용자
   */
  void view(final String ticketId, final String username);

  /**
   * 확인하고 있는 티켓에서 나옵니다.
   * @param ticketId 티켓 아이디
   * @param username 티켓을 보고있는 사용자
   */
  void leave(final String ticketId, final String username);


}
