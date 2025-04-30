package com.saucedemo.testcases;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.saucedemo.pages.Home;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * 설명 :
 * 상품을 카트에 담았을 때 버튼이 Remove로 변경이 되는지 확인하고,
 * 쇼핑 카트 상세 페이지로 진입하여 카트에 담은 상품과 Remove 버튼을 확인 후
 * 상품을 Remove한 뒤 상품 리스트로 돌아가서 Remove 버튼이
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료
 */
public class CheckProductInCartRemove implements TestCase {

    private int index;
    private String selectProductName;


    @Override
    public TestCase proceed(){
        // 무작위 상품 선택 및 selectedProductName에 저장
        index = new Random().nextInt(Home.addToCartButton().size());
        selectProductName = Home.productName().get(index).getText();

        // 무작위로 추출한 상품명을 selectProductList의 selectProductName에 저장
        List<String> selectProductList = new ArrayList<>();
        selectProductList.add(selectProductName);

        // 무작위로 선택한 상품 담기 > 버튼이 Remove로 변경되었는지 체크
        SelenideElement selectButton = Home.addToCartButton().get(index);
        selectButton.click();
        selectButton.shouldHave(Condition.text("Remove"));

        // 쇼핑 카트 상세 페이지로 이동
        Home.shoppingCart().shouldBe(Condition.visible).click();

        // 상품 리스트에서 무작위로 담은 상품이 카트에 존재하는지 체크
        List<String> cartProductName = StreamSupport.stream(Home.productName().spliterator(),false)
                        .map(SelenideElement::getText)
                        .collect(Collectors.toList());

        Assertions.assertTrue(
                cartProductName.containsAll(selectProductList),
                "선택한 상품이 카트에 존재하지 않습니다."
        );



        // 해당 상품의 Remove 버튼 클릭
        for (int i = 0; i < cartProductName.size(); i++) {
            if (cartProductName.get(i).equals(selectProductName)) {
                Home.cartProductRemove().get(i).click();
                break;
            }
        }


        // 상품 리스트로 돌아가기
        Home.continueShopping().click();


        return this;
    }

    @Override
    public void validate(){
        // 리스트에서 다시 버튼을 가져옴 (index 그대로 사용 가능)
        SelenideElement buttonAfterRemove = Home.addToCartButton().get(index);

        // "Add to cart"로 다시 바뀌었는지 확인
        buttonAfterRemove.shouldHave(Condition.text("Add to cart"));
    }
 }
