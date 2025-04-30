package com.saucedemo.testGroup;

import com.codeborne.selenide.Screenshots;
import com.saucedemo.common.SF;
import com.saucedemo.common.Utils;
import com.saucedemo.pages.Home;
import com.saucedemo.testcases.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.io.File;

@DisplayName("전체 테스트")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAll {

    @BeforeAll
    void beforeAll(){
        Utils.setTestConfig();
        Home.Actions.openPage();
    }

    @AfterEach
    void captureScreenshot(TestInfo testInfo) {
        // 테스트 실행 후 마지막 스크린샷 파일 참조
        File screenshot = Screenshots.getLastScreenshot();
        if (screenshot != null && screenshot.exists()) {
            System.out.println("Screenshot saved for test: " + testInfo.getDisplayName());
            System.out.println("Path: " + screenshot.getAbsolutePath());
        } else {
            System.out.println("No screenshot was taken.");
        }
    }

    @Test
    @Order(1)
    @DisplayName("로그인 성공")
    void testLogin(){
        SF.get(CheckSauceDemoLogin.class).proceed().validate();
    }

    @Test
    @Order(2)
    @DisplayName("상품 오름차순 정렬 : 낮은 -> 높은 가격순 ")
    void testProductSortLohi(){
        SF.get(CheckProductSortLohi.class).proceed().validate();
    }

    @Test
    @Order(3)
    @DisplayName("상품 내림차순 정렬 : 높은 -> 낮은 가격순 ")
    void testProductSortHilo(){
        SF.get(CheckProductSortHilo.class).proceed().validate();
    }

    @Test
    @Order(4)
    @DisplayName("상품 알파벳순 정렬 : A to Z")
    void testProductSortNameAz(){
        SF.get(CheckProductSortNameAz.class).proceed().validate();
    }

    @Test
    @Order(5)
    @DisplayName("상품 알파벳순 정렬 : A to Z")
    void testProductSortNameZa(){
        SF.get(CheckProductSortNameZa.class).proceed().validate();
    }

    @Test
    @Order(6)
    @DisplayName("상품 카트에 담기 & 제거")
    void testAddAndRemoveAllProducts(){
        SF.get(CheckAddAndRemoveAllProducts.class).proceed().validate();
    }

    @Test
    @Order(7)
    @DisplayName("상품 결제 Flow")
    void testBuyProductFlow(){
        SF.get(CheckBuyProductFlow.class).proceed().validate();
    }

    @Test
    @Order(8)
    @DisplayName("상품 상세 페이지 진입")
    void testProductDetailPage() {SF.get(CheckProductDetailPage.class).proceed().validate(); }

    @Test
    @Order(9)
    @DisplayName("로그아웃")
    void testLogout(){
        SF.get(CheckSauceDemoLogout.class).proceed().validate();
    }

    @Test
    @Order(10)
    @DisplayName("로그인 실패")
    void testLoginFail(){
        SF.get(CheckSauceDemoLoginFail.class).proceed().validate();
    }




}
