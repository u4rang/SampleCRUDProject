# Sample RESTful API

RESTful API 기반 메소드에 따른 응답코드를 정확하게 사용하기 위해 작성된 프로젝트 이다.

구현된 메소드는 다음과 같다.

- POST
  - 저장
- GET
  - 단건 조회
  - 다건 조회
- PATCH
  - 수정

비즈니스는 "MEMBER(회원)" 이다.

-----

## HTTP  Status Code

---------

### 2XX

Successful:  요청 정상 처리

**Code**

- 200 OK
- 201 Crerated (POST)
- 202 Accepted (batch)
- 204 No Content

------

### 3XX

Redirection: 요청 완료를 위해 추가 행동 필요

**Redirect**

- 웹 프라우저는 3xx 응답의 결과에 Location 헤더가 있으면. Location 위치로 자동 이동
- 영구 리다이렉션: 특정 리소스의 URI가 영구적으로 이동
  - Code
    - 301
    - 308
- 일시 리다이렉션: 일시적인 변경
  - PRG(Post/Redirect/Get)에 사용
  - 새로고침 중복 주문 방지
  - Code
    - 302
    - 303
    - 307
- 특수 리다이렉션: 결과 대신 캐시 사용

**Code**

- 300 Multiple Choices (X)
- 301 Moved Permanently
  - 리다이렉트 시 Get으로 변하고, 본문 손실
- 302 Found
  - 리다이렉트 시 GET으로 변하고, 본문 제거
- 303 See Other
  - 리다이렉트 시 GET으로 변경
- 304 Not Modified
  - 클라이언트에게 리소스가 수정되지 않았음을 알려줌 (캐시 재사용)
- 307 Temporary Redirect
  - 리다이렉트 시 메서드와 본문 유지
- 308 Permanent Redirect
  - 리다이렌트 시 POST, 본문 유지

----

### 4XX

Client Error

- 오류의 원인은 클라이언트

**Code**

- 400 Bad Request
  - 클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음
- 401 Unauthorized
  - 클라이언트가 해당 리소스에 대한 인증이 필요
  - 인증(Authentication): 로그인
  - 인가(Authorization): 권한
- 403 Forbidden
  - 서버가 요청을 이해했지만 승인을 거부 (접근 권한 제한)
- 404 Not Found
  - 요청 리소스를 찾을 수 없음

----

### 5XX

Server Error

- 서버 문제로 오류 발생

**Code**

- 500 Internal Server Error
  - 서버 내부 문제로 오류 발생 (애매하면 500)
- 503 Service Unavailable
  - 서비스 이용 불가

----

## 개발 환경

SpringBoot 기반의 H2 DB를 사용한 프로젝트

사용된 라이브러리 리스트

- JPA
- lombock
- junit 4

----

## 참고사항

 RESTful API 테스트를 위해 다음 파일을 참고한다.

- member.http

-----

## Reference Site

[RESTful API 설계 가이드](https://sanghaklee.tistory.com/57)

