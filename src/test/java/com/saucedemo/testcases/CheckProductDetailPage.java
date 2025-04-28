package com.saucedemo.testcases;

import com.saucedemo.pages.Home;
import org.junit.jupiter.api.Assertions;

import java.util.Random;


public class CheckProductDetailPage implements TestCase{

    private int index;
    private String selectProductName;

    @Override
    public TestCase proceed() {

        // 무작위 상품 선택 및 selectedProductName에 저장
        index = new Random().nextInt(Home.productName().size());
        selectProductName = Home.productName().get(index).getText();

        Home.Actions.clickProductByName(selectProductName);


        return this;
    }

    @Override
    public void validate() {
        Assertions.assertEquals(selectProductName, Home.getInventoryDetailName(), "상품명 일치");

    }
}
