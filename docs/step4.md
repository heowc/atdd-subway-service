## 요구사항

- [x] 이전 미션 피드백 반영하기
- [ ] 경로 조회 시 거리 기준 요금 정보 포함하기
- [ ] 노선별 추가 요금 정책 추가
- [ ] 연령별 할인 정책 추가

## 요구사항 설명

### 거리별 요금 정책

- 기본운임(10㎞ 이내) : 기본운임 1,250원
- 이용 거리초과 시 추가운임 부과
  - 10km초과∼50km까지(5km마다 100원)
  - 50km초과 시 (8km마다 100원)

> 지하철 운임은 거리비례제로 책정됩니다. (실제 이동한 경로가 아닌 최단거리 기준으로 계산)

> 참고 링크: http://www.seoulmetro.co.kr/kr/page.do?menuIdx=354

#### 수정된 인수 조건

```gherkin
Feature: 지하철 경로 검색

  Scenario: 두 역의 최단 거리 경로를 조회
    Given 지하철역이 등록되어있음
    And 지하철 노선이 등록되어있음
    And 지하철 노선에 지하철역이 등록되어있음
    When 출발역에서 도착역까지의 최단 거리 경로 조회를 요청
    Then 최단 거리 경로를 응답
    And 총 거리도 함께 응답함
    And ** 지하철 이용 요금도 함께 응답함 **
```

### 노선별 추가 요금 정책

- 노선에 `추가 요금` 필드를 추가
- 추가 요금이 있는 노선을 이용 할 경우 측정된 요금에 추가
  - ex) 900원 추가 요금이 있는 노선 8km 이용 시 1,250원 -> 2,150원
  - ex) 900원 추가 요금이 있는 노선 12km 이용 시 1,350원 -> 2,250원
- 경로 중 추가요금이 있는 노선을 환승 하여 이용 할 경우 가장 높은 금액의 추가 요금만 적용
  - ex) 0원, 500원, 900원의 추가 요금이 있는 노선들을 경유하여 8km 이용 시 1,250원 -> 2,150원

### 로그인 사용자의 경우 연령별 요금 할인 적용

- 청소년: 운임에서 350원을 공제한 금액의 20%할인
- 어린이: 운임에서 350원을 공제한 금액의 50%할인

```text
- 청소년: 13세 이상~19세 미만
- 어린이: 6세 이상~ 13세 미만
```

## 힌트

### /paths 요청 시 LoginMember 객체 처리

- 로그인 시 LoginMember 객체 를 활용하여 연령별 요금 할인을 적용할 수 있음
- 비 로그인 시 LoginMember는 비어있는 객체가 넘어가므로 별도의 처리가 필요함
- 필요 시 아래 구문에서 null object를 리턴해주는 부분을 예외를 던지도록 수정해도 무방함

```java
public LoginMember findMemberByToken(String credentials) {
    if (!jwtTokenProvider.validateToken(credentials)) {
        return new LoginMember(); // <--- 이 부분 변경 가능
    }

    String email = jwtTokenProvider.getPayload(credentials);
    Member member = memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
    return new LoginMember(member.getId(), member.getEmail(), member.getAge());
}
```
  
### 프론트엔드

![img image](https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/45cf6f9bf42b4abcb0f2c447dd190680)

### 5km 마다 100원 추가 로직

```java
private int calculateOverFare(int distance) {
    return (int) ((Math.ceil((distance - 1) / 5) + 1) * 100);
}
```