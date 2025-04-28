package com.saucedemo.testcases;


import com.saucedemo.pages.Home;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 설명 : 상품 오름차순(낮은 -> 높은)으로 정렬 기능을 확인한다.
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료
 */
public class CheckProductSortLohi implements TestCase {

    private List<Double> actualPrices; // 필드에 가격 저장
    private static final Logger logger = LoggerFactory.getLogger(CheckProductSortLohi.class);

    @Override
    public TestCase proceed() {
        Home.productSortOptionLohi().click();

//        ElementsCollection pricesElements = $$(".inventory_item_price"); // 모든 상품 가격 요소를 수집해서 PriceElements에 담아라
//        // $$("selector") 는 Selenide에서 해당 selector에 매칭되는 모든 요소들을 수집할 때 쓰는 문법
//        // .inventory_item_price는 상품 가격이 들어있는 span 태그의 class명
//        actualPrices = pricesElements.asFixedIterable().stream() // 위 코드에서 pricesElements에 모든 상품 가격 요소를 수집한걸 분리하는 코드
//                .map(el -> el.getText().replace("$","")) // replace로 $를 제거
//                .map(Double::parseDouble) // 가격이 문자열로 되어 있으니 실수 숫자형으로 변경
//                .collect(Collectors.toList()); // 위 작업을 actualPrices에 저장

        List<String> priceText = Home.getProductPriceText();
        actualPrices = Home.Actions.parsePriceTextToDouble(priceText);

        return this;
    }

    @Override
    public void validate(){
//        List<Double> expectedSortedPrice = actualPrices.stream() // proceed에서 저장한 actualPrices 값을 Stream(분리) 형태로 변환하고 expectedSortedPrice 여기에 새로운 리스트를 저장
//                .sorted() // stream 안에 요소들을 오름차순(낮은 값 → 높은 값)으로 정렬
//                .collect(Collectors.toList()); // 정렬된 stream을 다시 리스트에 모아서 반환 > 최종 결과가 expectedSortedPrice 여기에 담긴다
//
//        Assertions.assertEquals(expectedSortedPrice, actualPrices, "상품 가격이 낮은 순으로 정렬되지 않음.");

        List<Double> expectedSortedPrice = Home.Actions.sortAscending(actualPrices);
        Assertions.assertEquals(expectedSortedPrice, actualPrices);

        logger.info("실제 정렬 결과 : {}", actualPrices);
        logger.info("예상 정렬 결과 : {}", expectedSortedPrice);


    }
}
