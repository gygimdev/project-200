## 목적 | OBJECTIVE
- 스프링프레임워크로 웹서비스를 구현하는데 목적이 있습니다.
- 식비를 절약하고 가정에서 배출되는 음식물 쓰레기 양을 감소시키는데 목적이 있습니다.

## 배경 | BACKGROUND
#### 문제 | PROBLEM
음식물 쓰레기를 버리러 가던 어느날 문득, 우리 가정은 다른 가정에 비해 음식물 쓰레기가 많이 나올까? 라는 생각이 들었습니다. 집에 돌아와 한달에 소비되는 음식물 종량제의 L 를 계산해 보니, 한달에 약 60L 의 음식물 쓰레기를 배출하고 있었고, 유통기한 내에 식재료를 조리하지 못하거나, 요리를 해두고 잊어버리는 경우가 발생해 버리는 음식이 약 40% 정도를  차지하고 있었습니다.
#### 해결방안 | SOLUTION
적정량의 음식을 해먹기 위해 일주일에 두번씩 식재료를 구매하고 있었지만 어떤 식재료를 소모해야하는지 알지못해 유통기한이 지나버리는 음식이 약 40% 정도 되었습니다. 구입한 식재료를 기록하고 식재료의 정보와 유통기한 정보를 한눈에 확인할수 있다면 식비도 절약할 수 있고 더 나아가 환경 보호에 긍정적인 영향을 줄것이라고 생각하였습니다.


## 단계 | STEPS
- [X] 식재료 기능 
식재료 구매시 식재료의 수량과 유통기한을 기록하는 기능을 제공함으로써 사용자는 등록된 모든 식재료의 수량과 유통기한을 간편하게 확인할 수 있습니다.
- [ ] 요리 기능 
요리기능을 통해 사용자는 기록된 식재료를 묶음 단위로 소모할 수 있고, 더 나아가 요리된 음식의 목록과 유통기한을 한눈에 확인할 수 있습니다.  
- [ ] 음식물 쓰레기 배출 추적 및 통계
사용자는 음식물 쓰레기를 버리러갈때 기록하며, 기록된 데이터를 가지고 유의미한 통계값을 제공함으로 사용자에게 매달 음식물 쓰레기에 대한 배출량을 알려줍니다.  

## 개요 | OVERVIEW
project-200은 음식물 쓰레기를 줄이기 위해 사용자가 구입한 식재료의 수량과 유통기한을 가정별로 관리할 수 있는 기능을 제공합니다. 이 정보를 통해 사용자는 식재료를 제때에 소모함으로써 식재료의 낭비와 불필요한 추가 지출을 사전에 방지할 수 있습니다. 쳬게적이고 효율적인 식재료 관리를 통해 사용자는 식비를 절약하고 음식물 쓰레기를 줄여 환경 보호에 기여할 수 있습니다.

- [MVC] 스프링MVC 를 활용해 역할의 분리와 비즈니스 로직 중심의 구현에 중점을 둔 패턴(컨트롤러, 서비스, 리포지토리) 을 구현할 수 있습니다.

- [Custom Repository] 비즈니스 요구사항에 맞춰 JPA 이외의 데이터 접근과 조작(QueryDSL)을 위해 사용자 정의 리포지토리를 구현할 수 있습니다.

- [Spring Security] 스프링 시큐리티를 사용하여 사용자 보안정보 관리 및 로그인 구현을 할 수 있습니다.

- [Exception] basicErrorController 를 활용한 ErrorPage 구현 및 HandlerExceptionResolver 를 사용해 RuntimeException 의 상태코드를 비즈니스 요구사항에 맞게 변경할 수 있습니다.

- [Internationalize] 국제화 기능(messages/errors.property) 을 활용해  통일된 다국어/에러 메세지 번역 기능을 제공할 수 있습니다.

- [JPA] 영속성 컨테이너, 지연로딩의 개념을 이해하고 있으며, 객체지향 중심의 데이터베이스 조작을 할 수 있습니다.

- [DTO] 계층간 데이터 전달시 불필요한 정보 노출을 최소화 하기 위해 DTO 를 구현하고 사용할 수 있습니다.

- [QueryDSL] 복잡한 동적 쿼리를 QueryDSL 을 통해 구현할 수 있습니다.

- [thymeleaf] 스프링에서 권장하는 템플릿 엔진을 사용하여 서버사이드 랜더링을 구현할 수 있습니다.

- [Transaction] 트랜잭션의 ACID 개념과 스프링 트랜잭션 전파를 이해하고 있으며, 여러 작업을 물리적인 트랜잭션 단위로 묶어 비즈니스 요구사항에 맞는 기능을 구현할 수 있습니다.

- [aop] 공통관심사, 핵심관심사 분리의 중요성을 이해하고, AOP 를 구현하고 스프링 빈으로 등록해 사용할 수 있습니다.

- [profile] 각 개발환경의 스프링 프로파일을 사용하여 각 개발 운영환경의 설정을 관리할 수 있습니다.

- [CICD] github Action, codedeploy 를 사용해 안정적이고 빠르게 배포할 수 있습니다.

- [Custom Annotation] Enum 타입을 검증하기 위해 ConstraintValidator 인터페이스를 구현할수 있습니다.

- [MBC Logging] 스프링 필터기능을 구현해 웹서버(Nginx) 부터 웹애플리케이션서버(tomcat)까지 동일한 request_id 로 로그를 추적할 수 있는 MDC 로깅 패턴을 구현할 수 있습니다.
