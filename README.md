## 프로젝트 개요
* 목적
  * Spring MVC, Vue.js 등을 토대로 웹을 설계한 후 MySQL과 연동하여 AWS를 통해 배포

* 진행 기간
  * 2021/06/01 ~ 2021/09 

* 참여자 : 전병준

* 사용 스택 : Java Spring, Vue.js, Vuetify, JavaScript, HTML5, CSS, MySQL
  
* 맡은 역할
  * Spring, Gradle, MyBatis3 등을 사용해 Back-End를 구현
  * Vue.js, JavaScript, HTML5 등을 사용해 Front-End를 구현
  * MySQL을 사용해 전체적인 데이터베이스 설계
  
## 필수 Install
```c
npm install // node_modules 설치
```

## 게시판 기능 소개
* 로그인 / 회원가입
* 글 작성, 수정, 삭제 (계층형)
  * 답글 작성, 수정, 삭제
  * 댓글 작성, 수정, 삭제
* 조회수 확인
* 페이징 구현

## 쇼핑몰 기능 소개
* 무한 스크롤 구현
* 운영자
  * 상품 등록 (단일 이미지, 다중 이미지 등록 가능)
   * 상품 이름, 가격, 브랜드, 원산지, 개수 직접 등록 가능
  * 상품 삭제
  * 상품 랭킹 확인
   * 랭킹 페이지에서 해당 상품 재고 파악 가능

* 사용자
  * 상품 구매, 장바구니 담기 
  * 상품 좋아요, 좋아요 취소
  * 내 장바구니
    * 체크한 상품만큼의 총 금액, 총 결제가격 확인 가능
  * 내 주문내역
    * 주문한 상품 이미지, 주문일자, 요구사항 확인 가능
  * 사용자들의 좋아요 개수에 따른 인기 상품 확인 가능
