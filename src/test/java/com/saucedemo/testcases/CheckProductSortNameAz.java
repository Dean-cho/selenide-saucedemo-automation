package com.saucedemo.testcases;

import com.saucedemo.pages.Home;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 설명 : 상품 이름을 알파벳 순 A to Z으로 정렬 기능을 확인한다.
 * 조건
 * 1) saucedemo 사이트 호출
 * 2) 로그인 완료
 */
public class CheckProductSortNameAz implements TestCase {

    private List<String> actualTitle; // 필드에 상풍명 저장
    private static final Logger logger = LoggerFactory.getLogger(CheckProductSortNameAz.class);

    @Override
    public TestCase proceed() {
        Home.productSortOptionAz().click();

//        ElementsCollection titleElements = $$(".inventory_item_name");
//        actualTitle = titleElements.asFixedIterable().stream()
//                .map(el -> el.getText())
//                .collect(Collectors.toList());

        List<String> productName = Home.getProductName();
        actualTitle = Home.Actions.sortAscendingByProductName(productName);
        //actualTitle = Home.getProductName();

        return this;
    }

    @Override
    public void validate(){
//        List<String> expectedSortedTitle = actualTitle.stream()
//                .sorted()
//                .collect(Collectors.toList());
//
//        Assertions.assertEquals(expectedSortedTitle, actualTitle, "알파멧 A to Z 순으로 정렬되지 않음.");

        List<String> expectedSortedTitle = Home.Actions.sortAscendingByProductName(actualTitle);
        Assertions.assertEquals(expectedSortedTitle, actualTitle, "알파멧 A to Z 순으로 정렬되지 않음.");

        logger.info("실제 정렬 결과 : {}", actualTitle);
        logger.info("예상 정렬 결과 : {}", expectedSortedTitle);

    }
}
