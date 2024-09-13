# 🪙 TALARIA_RESOURCES

한 명의 판매자, 구매자가 서비스의 사용자들에게서 편리하게 금을 구매하고, 판매할 수 있는 TALARIA 서비스의 자원서버입니다. 

# 📑 목차
### [Quick Start](#quick-start)
### [1. 프로젝트 개요](#1-프로젝트-개요)
 - [⚙️기술 스택](#️-기술-스택)
 - [✔️ 요구사항](#️-요구사항)
### [2. 프로젝트 관리](#2-프로젝트-관리)
 - [🗓️ 일정(2024.08.04~2024.08.11)](#️-일정2024080420240811)
### [3. 기술 문서](#3-기술-문서)
 - [📄 API 명세서](#-api-명세서)
### [4. 기능 구현](#4-기능-구현)
 - [⭐ JWT 인증](#-제품-api)
 - [⭐ 제품 API](#-제품-api)
 - [⭐ 주문 API](#-주문-api)
### [5. 트러블 슈팅](#5-트러블-슈팅)

## Quick Start

1. MariaDB, Redis의 설치 및 실행이 필요합니다.
2. 원하는 위치에 프로젝트를 Clone 합니다.
3. **src\main\resources** 아래에 전달드린 파일 중 **talaria_res** 폴더에 있는 **application.properties** 파일을 넣어줍니다. 
4. 터미널에서 아래 명령어를 차례대로 실행해줍니다. </br>

```bash
./gradlew clean build

./gradlew bootRun
```

</br>

## 1. 프로젝트 개요

### ⚙️ 기술 스택
![java](https://img.shields.io/badge/Java-17-blue?logo=java)
![spring-boot](https://img.shields.io/badge/SpringBoot-3.3.3-6DB33F?logo=springboot)
![gRPC](https://img.shields.io/badge/gRPC-1.66.0-254c5b)

### ✔️ 요구사항

- 구매, 판매 시 품목은 두 가지: 99.9% 금과 99.99% 금
- 수량 단위는 그램(g)이며, 최대 소수점 2번째 자리까지 처리 가능합니다. (request DTO 처리 시 유의 바랍니다)
- (소비자 입장에서의) 구매 주문은 "주문 완료", "입금 완료", "발송 완료" 상태를 가지며,
- (소비자 입장에서의) 판매 주문은 "주문 완료", "송금 완료", "수령 완료" 상태를 가집니다.
- 각 주문에 대하여 주문번호, 주문일자, 주문자, 상태, 품목, 수량, 금액, 배송지 정보를 저장합니다.(추가적인 정보도 저장 가능합니다)
- 주문번호는 자동으로 생성되며 Human Readable 해야합니다.
- 주문일자는 현재 시간으로 설정합니다.
- 주문의 상태 역시 특정 조건(API 호출)에 따라 바뀔 수 있어야 합니다.
- Pagination을 활용하는 API 구현
- 모든 response는 요청한 user의 권한에 맞는 invoice를 출력해야 합니다.

</br>

## 2. 프로젝트 관리

### 🗓️ 일정(2024.08.04~2024.08.11)

| 날짜 | 활동 |
| --- | --- |
| 24.08.04 (수) | 스켈레톤 프로젝트 생성, 지라, 이슈, PR 템플릿 설정 |
| 24.08.05 (목) | 엔티티 설계 |
| 24.08.06 (금) | 엔티티 생성 |
| 24.08.09 (월) | 스프링 시큐리티+gRPC 결합 보안 설정 |
| 24.08.10 (화) | 제품 API 구현, 주문 API 구현 |
| 24.09.11 (수) | 주문 API 구현 마무리, README.md 작성 및 Swagger 적용 |

<details>
<summary><strong>작업 사이클</strong></summary>

1. 이슈 생성
2. 브랜치 생성
3. 코드 작성
4. PR 생성
5. dev 브랜치로 Merge
</details>

<details>
<summary><strong>이슈 관리</strong></summary>
<img src=https://github.com/user-attachments/assets/36ffaf43-ab7c-47d5-b052-a2bc46e8aee4>
</details>

<details>
<summary><strong>컨벤션</strong></summary>

- **Branch**
    - **전략**

      | Branch Type | Description |
      | --- | --- |
      | `dev` | 주요 개발 branch, `main`으로 merge 전 거치는 branch |
      | `feature` | 할 일 issue 등록 후 branch 생성 및 작업 |

    - **네이밍**
        - `{header}/#{issue number}`
        - 예) `feat/#1`

- **커밋 메시지 규칙**
    ```bash
    > [HEADER] : 기능 요약
    
    - [CHORE]: 내부 파일 수정
    - [FEAT] : 새로운 기능 구현
    - [ADD] : FEAT 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성 시
    - [FIX] : 코드 수정, 버그, 오류 해결
    - [DEL] : 쓸모없는 코드 삭제
    - [DOCS] : README나 WIKI 등의 문서 개정
    - [MOVE] : 프로젝트 내 파일이나 코드의 이동
    - [RENAME] : 파일 이름의 변경
    - [MERGE]: 다른 브렌치를 merge하는 경우
    - [STYLE] : 코드가 아닌 스타일 변경을 하는 경우
    - [INIT] : Initial commit을 하는 경우
    - [REFACTOR] : 로직은 변경 없는 클린 코드를 위한 코드 수정
    
    ex) [FEAT] 게시글 목록 조회 API 구현
    ex) [FIX] 내가 작성하지 않은 리뷰 볼 수 있는 버그 해결
    ```

- **Issue**
    ```bash
    ⭐️ Description
    <!-- 진행할 작업을 설명해주세요 -->
    
    ⭐️ To-do
    <!-- 작업을 수행하기 위해 해야할 태스크를 작성해주세요 -->
    [ ] todo1
    
    ⭐️ ETC
    <!-- 특이사항 및 예정 개발 일정을 작성해주세요 -->
    ```

- **PR**
  - **규칙**
    - branch 작업 완료 후 PR 보내기
    - 항상 local에서 충돌 해결 후 remote에 올리기
    - 충돌 확인 후 문제 없으면 merge
    -  merge
    ```bash
        > [MERGE] {브랜치이름}/{#이슈번호}
        ex) [MERGE] setting/#1
    ```
  - **Template**
    ```bash
    ⭐️ Description
    <!-- 진행할 작업을 설명해주세요 -->
    
    ⭐️ To-do
    <!-- 작업을 수행하기 위해 해야할 태스크를 작성해주세요 -->
    [ ] todo1
    
    ⭐️ ETC
    <!-- 특이사항 및 예정 개발 일정을 작성해주세요 -->
    ```
</details>

</br>

## 3. 기술 문서

### 📄 API 명세서

[Swagger API 명세서](http://localhost:9999/swagger-ui/index.html#/) </br>
[Postman API 명세어](https://documenter.getpostman.com/view/37810011/2sAXqmB5iB)

| API 명칭 | HTTP 메서드 | 엔드포인트 | 설명 |
| --- | --- | --- | --- |
| **제품 등록** | POST | `/api/product` | 제품을 등록합니다. |
| **제품 조회(단건)** | GET | `/api/product/{productId}` | 제품 상세(단건)를 조회 합니다. |
| **제품 조회(목록)** | GET | `/api/product`  | 제품 목록 상세를 조회 합니다. |
| **제품 수정** | PUT | `/api/product` | 제품 상세를 수정합니다. |
| **제품 삭제** | DELETE | `/api/product/{productId}` | 제품을 삭제합니다. |
| **주문 생성** | POST | `/api/order` | 주문을 생성합니다. |
| **주문 조회(단건)** | GET | `/api/order/{orderId}`  | 주문 상세(단건)를 조회합니다. |
| **주문 조회(목록)** | GET | `/api/order` | 주문 목록 상세를 조회 합니다. |
| **주문 수정** | PATCH | `/api/order` | 주문 상태를 변경합니다. |
| **주문 취소** | DELETE | `/api/order/{orderId}` | 주문을 취소합니다. |

<details>
<summary><strong>ERD</strong></summary>
<img src=https://github.com/user-attachments/assets/6461443f-aa03-4aba-97ac-f46838cbf5a7>
</details>

<details>
<summary><strong>디렉토리 구조</strong></summary>

```bash
├─main
│  ├─java
│  │  └─com
│  │      └─yonyk
│  │          └─talaria
│  │              └─resources
│  │                  │  TalariaResourcesApplication.java
│  │                  │
│  │                  ├─common
│  │                  │  ├─config
│  │                  │  │      GrpcSecurityConfig.java
│  │                  │  │      SpringSecurityConfig.java
│  │                  │  │      SwaggerConfig.java
│  │                  │  │
│  │                  │  ├─security
│  │                  │  │  ├─details
│  │                  │  │  │      PrincipalDetails.java
│  │                  │  │  │
│  │                  │  │  ├─filter
│  │                  │  │  │      AuthorizationFilter.java
│  │                  │  │  │
│  │                  │  │  ├─grpc
│  │                  │  │  │      AuthenticationClientInterceptor.java
│  │                  │  │  │      GrpcClientService.java
│  │                  │  │  │
│  │                  │  │  ├─handler
│  │                  │  │  │      CustomAccessDeniedHandler.java
│  │                  │  │  │      CustomAuthenticationEntryPoint.java
│  │                  │  │  │      SecurityExceptionHandler.java
│  │                  │  │  │
│  │                  │  │  └─util
│  │                  │  │          AuthorizationService.java
│  │                  │  │          MemberAuditorAware.java
│  │                  │  │
│  │                  │  └─swagger
│  │                  │          OrderControllerSwagger.java
│  │                  │          ProductControllerSwagger.java
│  │                  │
│  │                  ├─controller
│  │                  │  │  OrderController.java
│  │                  │  │  ProductController.java
│  │                  │  │
│  │                  │  ├─request
│  │                  │  │      OrderDTO.java
│  │                  │  │      OrderListRequestDTO.java
│  │                  │  │      OrderStatusDTO.java
│  │                  │  │      ProductDTO.java
│  │                  │  │      ProductPageDTO.java
│  │                  │  │
│  │                  │  └─response
│  │                  │          OrderDetailDTO.java
│  │                  │          OrderListResponseDTO.java
│  │                  │
│  │                  ├─entity
│  │                  │  │  BaseEntity.java
│  │                  │  │  Member.java
│  │                  │  │  Order.java
│  │                  │  │  Product.java
│  │                  │  │
│  │                  │  └─enums
│  │                  │          MemberRole.java
│  │                  │          OrderStatusType.java
│  │                  │          OrderType.java
│  │                  │          ProductNameType.java
│  │                  │          ProductType.java
│  │                  │
│  │                  ├─exception
│  │                  │  │  CustomException.java
│  │                  │  │  CustomExceptionHandler.java
│  │                  │  │
│  │                  │  └─enums
│  │                  │          CommonExceptionType.java
│  │                  │          ExceptionType.java
│  │                  │          OrderExceptionType.java
│  │                  │          ProductExceptionType.java
│  │                  │          SecurityExceptionType.java
│  │                  │
│  │                  ├─repository
│  │                  │      OrderRepository.java
│  │                  │      ProductRepository.java
│  │                  │
│  │                  └─service
│  │                          OrderRepoService.java
│  │                          OrderService.java
│  │                          ProductRepoService.java
│  │                          ProductService.java
│  │
│  ├─proto
│  │      auth.proto
│  │
│  └─resources
│      │  application.properties
│      │  application.yml
│      │
│      ├─static
│      └─templates
└─test
    └─java
        └─com
            └─yonyk
                └─talaria
                    └─resources
                            TalariaResourcesApplicationTests.java
```

</details>

</br>

## 4. 기능 구현

### ⭐ JWT 인증
- gRPC 클라이언트를 이용해 인증서버에 accessToken 발송하여 사용자 정보 받아오는 기능 구현
- 스프링 시큐리티 필터 체인 내에서 gRPC 통신 사용
- 인증 성공 시 자동으로 시큐리티 컨택스트에 인증객체 등록되며 컨트롤러에서 사용 가능

<details>
    <summary>구현 의도</summary>
    <div>
        <div><strong>필터 체인 내에서 gRPC 통신 사용</strong></div>
        <div>자원서버의 모든 요청이 로그인 후에 이용가능하기 때문에 모든 요청마다 JWT 인증이 필요했습니다. 기본 스프링 시큐리티 또한 필터 체인으로 모든 요청을 검사하기 때문에 그렇다면 gRPC 통신을 이용한 인증 또한 이 안에서 하면 되겠다고 생각해 필터체인 내에서 gRPC 통신이 이루어지도록 작성했습니다. 결과적으로 파싱을 자체적으로 안할 뿐 필터 체인 내에서 인증을 하고 인증 객체를 시큐리티 컨택스트에 등록하는 과정은 동일해졌습니다.</div>
    </div>
</details>
<details>
    <summary>구현 코드</summary>
    <div>
        <a href="https://github.com/yony-k/TALARIA-RESOURCES/tree/dev/src/main/java/com/yonyk/talaria/resources/common/security" target="_blank">시큐리티 패키지</a></br>
    </div>
</details>

---

### ⭐ 제품 API

- 기본적으로 제품 조회 제외 제품 API는 ADMIN 권한만 접근이 가능하도록 구현했습니다.
<details>
    <summary>구현 코드</summary>
    <div>
        <a href="https://github.com/yony-k/TALARIA-RESOURCES/blob/dev/src/main/java/com/yonyk/talaria/resources/controller/ProductController.java" target="_blank">ProductController</a></br>
        <a href="https://github.com/yony-k/TALARIA-RESOURCES/blob/dev/src/main/java/com/yonyk/talaria/resources/service/ProductService.java" target="_blank">ProductService</a></br>
        <a href="https://github.com/yony-k/TALARIA-RESOURCES/blob/dev/src/main/java/com/yonyk/talaria/resources/service/ProductRepoService.java" target="_blank">ProductRepoService</a></br>
    </div>
</details>


#### ✨ 제품 등록
- 제품 이름, 제품의 타입, 제품 무게, 제품 수량, 제품 가격을 받아서 제품 등록
- 제품명, 제품 타입 이넘으로 제한

#### ✨ 제품 조회(단건, 목록)
- 제품 타입에 따른 목록 조회 구현
- 페이지네이션 적용

#### ✨ 제품 수정
- 제품의 전체 정보를 받아 수정

#### ✨ 제품 삭제
- 소프트 딜리트 방식 채택하여 삭제 시 deleted_at 필드에 날짜가 기록되는 식으로 구현

---

### ⭐ 주문 API

- 기본적으로 주문 API는 로그인이 되어야 접근 가능합니다.

<details>
    <summary>구현 코드</summary>
    <div>
        <a href="https://github.com/yony-k/TALARIA-RESOURCES/blob/dev/src/main/java/com/yonyk/talaria/resources/controller/OrderController.java" target="_blank">OrderController</a></br>
        <a href="https://github.com/yony-k/TALARIA-RESOURCES/blob/dev/src/main/java/com/yonyk/talaria/resources/service/OrderService.java" target="_blank">OrderService</a></br>
        <a href="https://github.com/yony-k/TALARIA-RESOURCES/blob/dev/src/main/java/com/yonyk/talaria/resources/service/OrderRepoService.java" target="_blank">OrderRepoService</a></br>
    </div>
</details>

#### ✨ 주문 생성
- 주문의 타입, 제품의 ID, 주문 수량, 배송지를 받아서 주문 생성
- 주문 타입 이넘으로 제한
- 주문 수량과 제품의 수량을 비교하여 재고부족 시 주문 불가 처리
- 주문 성공 시 주문 수량만큼 제품의 수량 감소 처리

<details>
    <summary>구현 의도</summary>
    <div>
        <div><strong>주문 타입 이넘으로 제한</strong></div>
        <div>서비스의 사용자는 구매/판매만 가능하기 때문에 이넘으로 선택지를 정해주는 것이 맞다고 생각해서 이렇게 구현했습니다.</div>
        <div><strong>수량 체크</strong></div>
        <div>실물이 오고가는 거래에서 수량 체크는 필수적인 부분이라고 생각해서 수량 체크 기능을 구현했습니다. 수량체크-수량감소-주문생성 과정에는 Transactional 처리를 하여 안전도를 높였습니다.</div>
    </div>
</details>

#### ✨ 주문 조회(단건, 목록)
- 기본적으로 자신의 주문 외에 다른 사람의 주문은 보지 못하도록 구현
- 주문 조회(목록)의 경우 주문 날짜, 페이지당 항목 수, 페이지 번호, 주문의 타입을 받아 페이지네이션 적용하여 목록 반환하도록 구현

<details>
    <summary>구현 의도</summary>
    <div>
        <div><strong>페이지네이션</strong></div>
        <div>사용자는 구매/판매 두 가지 행동을 취할 수 있기 때문에 두 가지 타입의 주문서가 혼재되어 있을 수 밖에 없습니다. 사용자가 본인이 작성한 주문서를 확인한다고 했을 때 두 가지 타입의 주문서를 동시에 보기보다 타입에 따라 다른 목록을 볼 수 있는 것이 사용자 친화적이라고 생각했습니다.</div>
    </div>
</details>

#### ✨ 주문 수정(상태변경)
- 주문 타입이 발송완료, 수령완료, 주문취소인 경우 수정 불가하도록 구현

<details>
    <summary>구현 의도</summary>
    <div>
        <div><strong>특정 상태일 경우 수정 불가</strong></div>
        <div>이미 종료된 주문의 경우 수정이 불필요하다고 생각하여 수정이 불가하도록 수정했습니다.</div>
    </div>
</details>

#### ✨ 주문 취소, 삭제
- 주문 타입이 발송완료, 수령완료, 주문취소인 경우 취소 불가하도록 구현

<details>
    <summary>구현 의도</summary>
    <div>
        <div><strong>특정 상태일 경우 취소 불가</strong></div>
        <div>제품의 특성(금)상 한번 종료된 주문이 취소되어 반송되는 것은 제품에 문제가 있지 않는 한 효율적이지 못하다고 판단하여 주문이 특정 상태일 경우 자체적인 주문취소가 불가하도록 구현하였습니다.</div>
    </div>
</details>

</br>

## 5. 트러블 슈팅

<details>
    <summary>@LastModifiedBy 사용</summary>
    <div>
        <div><strong>문제상황</strong></div>
        <div>엔티티가 수정되면 자동적으로 수정한 사람이 기록되도록 BaseEntity 에 @LastModifiedBy 어노테이션을 적어놨는데 엔티티가 수정되어도 이 어노테이션이 붙은 컬럼에 값이 들어가지 않았다.</div>
        <div><strong>원인</strong></div>
        <div>알고보니 AuditorAware 라는 클래스를 구현하여 Bean으로 등록해둬야했다. 더불어 JPA 리포지토리의 자동으로 생성되어있는 메소드(save, deleteBy...)를 이용한 수정만 감시되어 어노테이션이 작동하고 @Modifying 이 붙는 직접 만든 쿼리문이 돌아가는 리포지토리 메소드는 감시되지 않아 작동하지 않는 거였다.</div>
        <div><strong>해결</strong></div>
        <div>직접 만든 쿼리문을 폐기하고 자동 생성되는 메소드를 사용하여 수정될 수 있도록 했다.</div>
    </div>
</details>
