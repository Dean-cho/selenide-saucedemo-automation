package com.saucedemo.tests;

import com.saucedemo.common.SF;
import com.saucedemo.common.Utils;
import com.saucedemo.pages.Home;
import com.saucedemo.testcases.*;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.MethodName.class) // 테스트 메서드 실행 순서를 알파벳순으로 정렬해서 실행
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 클래스 당 하나의 인스턴스만 생성
public class ProductSortTest {

    @BeforeAll
    static void beforeAll() {
        Utils.setTestConfig();
        Home.Actions.openPage();
        SF.get(CheckSauceDemoLogin.class).proceed().validate();
    }

    @Test
    @Order(1)
    void testProductSort(){
        SF.get(CheckProductSortLohi.class).proceed().validate();
    }

    @Test
    @Order(2)
    void testProductSortHilo(){
        SF.get(CheckProductSortHilo.class).proceed().validate();
    }

    @Test
    @Order(3)
    void testProductSortNameAz(){
        SF.get(CheckProductSortNameAz.class).proceed().validate();
    }

    @Test
    @Order(4)
    void testProductSortNameZa(){
        SF.get(CheckProductSortNameZa.class).proceed().validate();
    }
}
