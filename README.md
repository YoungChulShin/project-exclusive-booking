# 프로젝트

## 설명

간단한 `예약 시스템`을 개발합니다.

예약 시스템은 아래 특징이 있습니다.

1. 동시에 1명만 티켓에 접근 권한이 있습니다.
2. 티켓은 최대 1분까지만 볼 수 있습니다. 1분이 지나면 다른 사용자가 예약 정보를 볼 수 있고, 예약할 수 있습니다.
3. 특정 티켓을 보고 있는 상태에 대해서 클라이언트에서 실시간으로 확인할 수 있어야합니다.

티켓은 아래의 특징이 있습니다.

1. 특정 카테고리에 속했습니다.
    - 예: 공연, 연극, 축제 등
2. 사용자는 카테고리에 들어갈 수 있고, 해당 카테고리 내에서 변경되는 티켓 선점 정보를 확인할 수 있습니다.

## 목적

웹소켓을 이용해서 다중 클라이언트와 연동하는 부분을 확인합니다. 

