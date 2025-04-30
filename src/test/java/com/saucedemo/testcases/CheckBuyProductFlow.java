package com.saucedemo.testcases;

import com.codeborne.selenide.Condition;
import com.saucedemo.pages.Home;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 설명 : 상품을 카트에 담고 쇼핑카트로 진입하여 담긴 상품을 체크한다.
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료
 */
public class CheckBuyProductFlow implements TestCase {

    private List<Home.ProductInfo> selectedProducts;

    @Override
    public TestCase proceed(){

        // Remove 버튼이 이미 존재하는 경우 초기화
        Home.Actions.removeButtons();

        // 랜덤하게 3개의 상품을 선택하여 장바구니에 담기 (상품 이름, 가격 정보 포함)
        selectedProducts = Home.Actions.addRandomProductsToCart(3);

        // 장바구니 페이지에서 랜덤으로 장바구니에 담은 상품 확인
        Home.Actions.verifyProductsInCart(selectedProducts);

        // Checkout 버튼 클릭
        Home.checkoutButton().shouldBe(Condition.visible).click();

        // 정보 입력
        Home.Actions.fillCheckoutInfo("JO", "HYUNJIN", "123456");

        // 최종 정보 제출
        Home.continueButton().shouldBe(Condition.visible).click();

        // 총 상품 가격 확인
        Home.Actions.verifyTotalPrice(selectedProducts);

        // Finish 버튼 클릭하여 구매 완료
        Home.finishButton().shouldBe(Condition.visible).click();




        return this;
    }

    @Override
    public void validate() {
        assertTrue(Home.backHomeButton().shouldBe(Condition.visible).exists());
        Home.backHomeButton().shouldBe(Condition.visible).click();
    }
}
