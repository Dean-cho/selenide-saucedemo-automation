package com.saucedemo.testcases;


import com.saucedemo.common.Config;
import com.saucedemo.common.PropertyLoader;
import com.saucedemo.pages.Home;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 설명 : saucedemo 사이트를 호출하여 로그인 실패 메시지를를 확인한다.
 * 조건
 * 1) saucedemo 사이트 호출
 */
public class CheckSauceDemoLoginFail implements TestCase {

    @Override
    public TestCase proceed() {
        Config.Account account = PropertyLoader.getConfig().accounts.get("standardUserFail");
        Home.Actions.login(account.id, account.pw);

        return this;
    }

    @Override
    public void validate(){
        assertTrue(Home.loginFailMessage().exists());
    }
}
