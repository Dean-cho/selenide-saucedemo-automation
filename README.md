# Selenide 기반 SauceDemo 자동화 테스트 프로젝트

## 프로젝트 소개
- **Selenide**와 **Java**를 사용하여 [SauceDemo](https://www.saucedemo.com/) 웹사이트의 쇼핑몰 기능을 자동화 테스트한 프로젝트입니다.
- **로그인, 상품 담기, 장바구니 검증, 체크아웃 프로세스**까지 실제 유저 흐름을 재현하여 테스트합니다.
- 코드 품질을 위해 **페이지 객체 패턴**과 **모듈화 된 구조**를 적용했습니다.

## 주요 기술 스택
- **Java 17**
- **Selenide**
- **JUnit 5**
- **Maven**

## 프로젝트 구조
```
src/
 └── test/
      ├── java/
      │    └── com.saucedemo/
      │         ├── common/    # 공통 유틸리티 클래스
      │         ├── pages/     # 페이지 객체 (PO) 클래스
      │         ├── testcases/ # 실제 테스트 케이스 클래스
      │         └── testGroup/ # 테스트 그룹 실행용 클래스
      └── resources/           # 설정 파일 등 리소스
pom.xml                        # Maven 프로젝트 설정 파일
```

## 핵심 기능
- **로그인 자동화** : 로그인 정보 입력 및 검증
- **상품 랜덤 담기** : 상품을 무작위로 골라 장바구니에 추가
- **장바구니 확인** : 추가된 상품명과 가격을 실제 장바구니에서 검증
- **체크아웃 플로우 검증** : 필수 정보 입력 및 가격 일치 여부 확인
- **결제 완료 플로우** : 정상적으로 구매 완료까지 자동화

## 실행 방법
1. 저장소 클론
    ```bash
    git clone https://github.com/Dean-cho/selenide-saucedemo-automation.git
    ```
2. IDE에서 프로젝트 열기 (IntelliJ 추천)
3. Maven 프로젝트로 인식되면 의존성 자동 다운로드
4. `TestAll.java` 클래스 실행 → 전체 자동화 시나리오 수행

## 포트폴리오 포인트
- Selenide를 활용한 실전 테스트 자동화 능력
- 실제 쇼핑몰 사이트를 기반으로 한 E2E 테스트 설계
- 코드 가독성 및 유지 보수를 고려한 리팩토링 경험

## GitHub Actions
이 프로젝트는 GitHub Actions를 활용하여 자동화 테스트를 주기적으로 실행합니다.
- **워크플로우 파일**: `.github/workflows/selenide-test.yml`
- **실행 주기**: 매일 오전 9시 자동 실행 (`cron`)
- **주요 테스트**: `TestAll` 클래스에서 모든 테스트를 통합 실행

---

> 테스트 품질 향상을 위해 지속적으로 개선할 예정입니다.