package com.saucedemo.testcases;


import com.codeborne.selenide.Condition;
import com.saucedemo.pages.Home;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

/**
 * 설명 : 상품을 카트에 담고 쇼핑카트의 카운트를 체크한다.
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료
 */
public class CheckHomeProductAddToCart implements TestCase {

    private int expectedCount = 0;

    private static final Logger logger = LoggerFactory.getLogger(CheckHomeProductAddToCart.class);

    @Override
    public TestCase proceed(){

        Home.sauceLabsBackpackAddToCart().shouldBe(Condition.visible).click();
        expectedCount++;

        Home.sauceLabsBikeLightAddToCart().shouldBe(Condition.visible).click();
        expectedCount++;

        Home.sauceLabsBlotTshirtAddToCart().shouldBe(Condition.visible).click();
        expectedCount++;

        Home.sauceLabsFleeceJacketAddToCart().shouldBe(Condition.visible).click();
        expectedCount++;

        Home.sauceLabsOnesieAddToCart().shouldBe(Condition.visible).click();
        expectedCount++;

        Home.sauceLabsRedTshirtAddToCart().shouldBe(Condition.visible).click();
        expectedCount++;




        return this;
    }

    @Override
    public void validate(){
        String badgeText = $(".shopping_cart_badge")
                .shouldBe(Condition.visible)
                .getText();

        int actualCount = Integer.parseInt(badgeText);

        Assertions.assertEquals(expectedCount, actualCount, "쇼핑카트에 담긴 상품 수량이 올바르지 않음.");

        logger.info("실제 상품 수량 결과 : {}", actualCount);
        logger.info("예상 상품 수량 결과 : {}", expectedCount);

        //Home.Actions.clearCart();

    }
}
