# Pre 기획

- 프로젝트 기획 배경
    - 자주 이용하는 독립서점에서 무인으로 운영하는 시간대가 있다. 무인 운영중에는 계좌이체로만 결제가 가능하여 이용자의 불편함이 따른다. 그래서 여러가지 결제가 가능한 무인결제 시스템을 기획하게 되었다.
    - 무인결제 시스템을 기획하며 회원관리와 매출관리 기능을 추가하면 매장 관리시스템으로도 활용 가능 할것이라 생각하여 최종적으로 독립서점 매장관리시스템을 기획 하게 되었다.
- 프로젝트 구조

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bfd457df-036e-4605-a468-b2cb2ddf62d0/Untitled.png)

- 사용 기술스택
    - SpringBoot
    - Java
    - MariaDB
    - Redis
    - Thymeleaf
    - Spring security
    - Mybatis
    - Lombok
    - AWS

- 프로젝트 기능
    - 회원관리
        - 회원가입
            - 핸드폰번호, 비밀번호, 닉네임으로 회원가입을 한다.
            - 가입된 핸드폰번호로 회원가입시 에러발생
        - 로그인
            - 핸드폰번호와 비밀번호로 로그인 시도한다.
            - 가입되어있지 않은 핸드폰번호로 로그인시 에러발생
            - 비밀번호가 일치하지 않을시 에러발생
        - 정보수정
            - 비밀번호를 다시 입력하여 정보수정으로 이동한다.
            - 핸드폰번호, 닉네임을 수정할 수 있다.
            - 비밀번호를 초기화 시킬 수 있다.
            - 비밀번호가 일치하지 않을시 에러발생
        - 책주문
            - 현재 없는 도서를 주문할 수 있다.
            - 재고가 있는 도서를 주문시 에러발생
    - 무인결제기능
        - 도서조회
            - 도서제목 또는 저자로 재고가 있는지 조회
        - 결제
            - 로그인 후 구입하려는 도서를 장바구니에 담은 후 결제
            - payment api를 사용하여 각각의 수단으로 결제
            - 결제 실패시 에러발생
    - 게시판
        - 리뷰게시판
            - 로그인 후 구입한 도서의 리뷰와 평점 작성
            - 자신이 작성한 리뷰, 평점 수정, 삭제
            - 다른사람의 리뷰 수정, 삭제시 에러 발생
            - 관리자는 리뷰내용 숨기기를 할 수 있다.
    - 관리자
        - 회원관리
            - 비정상적인 회원을 삭제처리할 수 있다.
        - 주문도서관리
            - 주문신청된 도서를 확인하여 수락 또는 거절할 수 있다.
            - 주문신청된 도서의 상태를 확인할 수있다.(신청, 수락, 거절, 주문대기, 주문중, 판매완료)
        - 매출관리
            - 매출을 조회할 수 있다.
            - 판매된 도서의 목록 조회
        - 공지관리
            - 공지사항 등록, 수정, 삭제
        - 이벤트관리
            - 이벤트 등록, 수정, 삭제
        - 도서관리
            - 도서를 등록할 수 있다.
            - 도서의 재고를 수정할 수 있다.
- ERD
- ![스크린샷 2022-10-24 오전 12.24.59.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6fa50bf0-b04d-4ff2-b741-aa05faa32b6b/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2022-10-24_%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB_12.24.59.png)
