package com.saucedemo.testcases;


import com.saucedemo.common.Config;
import com.saucedemo.common.PropertyLoader;
import com.saucedemo.pages.Home;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * 설명 : saucedemo 사이트를 호출하여 로그인을 하고, 쇼핑카트 아이콘으로 로그인 여부를 확인한다.
 * 조건
 * 1) saucedemo 사이트 호출
 */

public class CheckSauceDemoLogin implements TestCase {

    @Override
    public TestCase proceed() {
        Home.usernameStandard().click();
        Config.Account account = PropertyLoader.getConfig().accounts.get("standardUser");
        Home.Actions.login(account.id, account.pw);

        return this;
    }

    @Override
    public void validate(){
        assertTrue(Home.shoppingCart().exists());
    }
}
