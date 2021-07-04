# 🚀 3단계 - 인증을 통한 기능 구현

## 요구사항

- [X] 경로 조회 시 거리 기준 요금 정보 포함하기
  - [X] 기본운임(10 이하) 1250원
  - [X] 거리 10~50 이면 5마다 100원 추가
  - [X] 50 초과시 8마다 100원
- [X] 노선별 추가 요금 필드 추가
  - [X] 추가 요금이 있는 노선을 이용 할 경우 측정된 요금에 추가
    - ex) 900원 추가 요금이 있는 노선 8km 이용 시 1,250원 -> 2,150원
    - ex) 900원 추가 요금이 있는 노선 12km 이용 시 1,350원 -> 2,250원
    - 경로 중 추가요금이 있는 노선을 환승 하여 이용 할 경우 가장 높은 금액의 추가 요금만 적용
ex) 0원, 500원, 900원의 추가 요금이 있는 노선들을 경유하여 8km 이용 시 1,250원 -> 2,150원
- [X] 연령별 할인 정책 추가
  - [X] 청소년(13세 이상 19세 미만): 운임에서 350원을 공제한 금액의 20%할인
  - [X] 어린이(6세 이상 13세 미만): 운임에서 350원을 공제한 금액의 50%할인