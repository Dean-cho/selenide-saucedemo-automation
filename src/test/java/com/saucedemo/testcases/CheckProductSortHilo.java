package com.saucedemo.testcases;


import com.saucedemo.pages.Home;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 설명 : 상품 내림차순(높은 -> 낮은)으로 정렬 기능을 확인한다.
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료
 */
public class CheckProductSortHilo implements TestCase {

    private List<Double> actualPrices; // 필드에 가격 저장
    private static final Logger logger = LoggerFactory.getLogger(CheckProductSortLohi.class);

    @Override
    public TestCase proceed() {
        Home.productSortOptionHilo().click();
        List<String> priceText = Home.getProductPriceText();
        actualPrices = Home.Actions.parsePriceTextToDouble(priceText);

        return this;
    }

    @Override
    public void validate(){
        List<Double> expectedSortedPrice = Home.Actions.sortDescending(actualPrices);
        Assertions.assertEquals(expectedSortedPrice, actualPrices);

        logger.info("실제 정렬 결과 : {}", actualPrices);
        logger.info("예상 정렬 결과 : {}", expectedSortedPrice);


    }
}
