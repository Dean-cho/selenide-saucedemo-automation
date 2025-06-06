package com.saucedemo.tests;

import com.saucedemo.common.SF;
import com.saucedemo.common.Utils;
import com.saucedemo.pages.Home;
import com.saucedemo.testcases.CheckBuyProductFlow;
import com.saucedemo.testcases.CheckSauceDemoLogin;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.MethodName.class) // 테스트 메서드 실행 순서를 알파벳순으로 정렬해서 실행
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 클래스 당 하나의 인스턴스만 생성
public class BuyProductFlowTest {

    @BeforeAll
    static void beforeAll(){
        Utils.setTestConfig();
        Home.Actions.openPage();
        SF.get(CheckSauceDemoLogin.class).proceed().validate();
    }

    @Test
    void testProductInCart(){
        SF.get(CheckBuyProductFlow.class).proceed().validate();
    }
}
