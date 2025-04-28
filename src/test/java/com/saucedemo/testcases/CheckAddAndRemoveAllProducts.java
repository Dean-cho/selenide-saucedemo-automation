package com.saucedemo.testcases;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.saucedemo.pages.Home;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 설명 :
 * 상품 리스트에서 카트에 상품을 담고, 담은 수량을 카트 뱃지에 카운트 되는지 확인한다.
 * 이후 쇼핑 카트로 진입하여 담았던 상품을 확인하고, Remove 버튼으로 삭제한다.
 * 카트에서 상품 삭제 후 Continue 버튼을 통해 상품 리스트로 돌아가서 상품에 Remove 버튼이 아닌 Add to cart 버튼인지 확인한다.
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료
 */

public class CheckAddAndRemoveAllProducts implements TestCase {

    private static final Logger logger = LoggerFactory.getLogger(CheckAddAndRemoveAllProducts.class);
    private List<Home.ProductInfo> selectedProducts;


    @Override
    public TestCase proceed() {


        // Remove 버튼이 이미 존재하는 경우 초기화
        Home.Actions.removeButtons();

        // 랜덤하게 3개의 상품을 선택하여 장바구니에 담기
        //List<Home.ProductInfo> selectedProducts = Home.Actions.addRandomProductsToCart(3);
        selectedProducts = Home.Actions.addRandomProductsToCart(3);

        // 선택된 상품명 추출
        List<String> selectedNames = selectedProducts.stream()
                .map(p -> p.name)
                .collect(Collectors.toList());

        // 장바구니 뱃지 수량 확인
        int expectedCount = selectedProducts.size();
        int actualCount = Home.getShoppingCartBadge();
        Assertions.assertEquals(expectedCount, actualCount, "쇼핑카트에 담긴 상품 수량이 올바르지 않음.");

        logger.info("실제 상품 수량 결과 : {}", actualCount);
        logger.info("예상 상품 수량 결과 : {}", expectedCount);

        // 장바구니 페이지에서 상품명 확인
        Home.Actions.verifyProductsNameInCart(selectedNames);

        // Remove 버튼 클릭하여 상품 제거
        Home.Actions.removeProductsFromCart(selectedNames.size());

        // Continue Shopping 버튼 클릭
        Home.continueShopping().click();



        return this;
    }

    @Override
    public void validate() {

        // 상품 버튼이 "Add to cart"로 초기화되었는지 확인
        Home.Actions.verifyProductsReset(selectedProducts);
        for (Home.ProductInfo p : selectedProducts) {
            SelenideElement button = Home.getAddToCartButton(p.index);
            button.shouldHave(Condition.text("Add to cart"));
        }

    }
}
