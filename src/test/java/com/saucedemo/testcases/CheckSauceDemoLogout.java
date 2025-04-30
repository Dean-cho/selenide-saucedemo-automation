package com.saucedemo.testcases;


import com.codeborne.selenide.Condition;
import com.saucedemo.pages.Home;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 설명 : saucedemo 사이트 로그인 된 상태에서 로그아웃을 한다.
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료 상태
 */
public class CheckSauceDemoLogout implements TestCase {

    @Override
    public TestCase proceed(){
        Home.bmBurgerButton().shouldBe(Condition.visible).click();
        Home.logoutButton().shouldBe(Condition.visible).click();


        return this;

    }

    @Override
    public void validate(){
        assertTrue(Home.loginButton().shouldBe(Condition.visible).exists());

    }


}
