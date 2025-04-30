package com.saucedemo.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.saucedemo.common.PropertyLoader;
import com.saucedemo.common.Utils;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Home {

   // 로그인 버튼
    private static final By LOGIN_BUTTON = byXpath("//input[@data-test='login-button']");
    public static SelenideElement loginButton() {return $(LOGIN_BUTTON).shouldBe(Condition.visible);}

    // ID 입력란
    private static final By USERNAME_STANDARD = byXpath("//input[@data-test='username']");
    public static SelenideElement usernameStandard() {return $(USERNAME_STANDARD).shouldBe(Condition.visible);}

    // PW 입력란
    private static final By PASSWORD_STANDARD = byXpath("//input[@data-test='password']");
    public static SelenideElement passwordStandard() {return $(PASSWORD_STANDARD).shouldBe(Condition.visible);}

    // 쇼핑 카트 아이콘
    private static final By SHOPPINGCART = byXpath("//a[@data-test='shopping-cart-link']");
    public static SelenideElement shoppingCart() {return $(SHOPPINGCART).shouldBe(Condition.visible);}

    // 좌측 상단 삼선 메뉴 버튼
    private static final By BMBURGERBUTTON = byXpath("//button[@id='react-burger-menu-btn']");
    public static SelenideElement bmBurgerButton() {return $(BMBURGERBUTTON).shouldBe(Condition.visible);}

    // 로그아웃 버튼
    private static final By LOGOUTBUTTON = byXpath("//a[@data-test='logout-sidebar-link']");
    public static SelenideElement logoutButton() {return $(LOGOUTBUTTON).shouldBe(Condition.visible);}

    // 로그인 실패 메시지
    private static final By LOGINFAILMESSAGE = byXpath("//h3[@data-test='error']");
    public static SelenideElement loginFailMessage() {return $(LOGINFAILMESSAGE).shouldBe(Condition.visible);}

    // 상품 정렬 버튼
    private static final By PRODUCTSORT = byXpath("//select[@data-test='product-sort-container']");
    public static SelenideElement productSort() {return $(PRODUCTSORT).shouldBe(Condition.visible);}

    // 상품 정렬 옵션 메뉴 Name A to Z
    private static final By PRODUCTSORTOPTIONAZ = byXpath("//select[@data-test='product-sort-container']/option[@value='az']");
    public static SelenideElement productSortOptionAz() {return $(PRODUCTSORTOPTIONAZ).shouldBe(Condition.visible);}

    // 상품 정렬 옵션 메뉴 Name Z to A
    private static final By PRODUCTSORTOPTIONZA = byXpath("//select[@data-test='product-sort-container']/option[@value='za']");
    public static SelenideElement productSortOptionZa() {return $(PRODUCTSORTOPTIONZA).shouldBe(Condition.visible);}

    // 상품 정렬 옵션 메뉴 Price Low to High
    private static final By PRODUCTSORTOPTIONLOHI = byXpath("//select[@data-test='product-sort-container']/option[@value='lohi']");
    public static SelenideElement productSortOptionLohi() {return $(PRODUCTSORTOPTIONLOHI).shouldBe(Condition.visible);}

    // 상품 정렬 옵션 메뉴 Price High to Low
    private static final By PRODUCTSORTOPTIONHILO = byXpath("//select[@data-test='product-sort-container']/option[@value='hilo']");
    public static SelenideElement productSortOptionHilo() {return $(PRODUCTSORTOPTIONHILO).shouldBe(Condition.visible);}

    // Sauce Labs Backpack 상품을 카트에 담기 버튼
    private static final By SAUCELABSBACKPACKADDTOCART = byXpath("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    public static SelenideElement sauceLabsBackpackAddToCart(){return $(SAUCELABSBACKPACKADDTOCART).shouldBe(Condition.visible);}

    // Sauce Labs Bike Light 상품 카트에 담기 버튼
    private static final By SAUCELABSBIKELIGHTADDTOCART = byXpath("//button[@data-test='add-to-cart-sauce-labs-bike-light']");
    public static SelenideElement sauceLabsBikeLightAddToCart() {return $(SAUCELABSBIKELIGHTADDTOCART).shouldBe(Condition.visible);}

    // Sauce Labs Bolt T-Shirt 상품 카트에 담기 버튼
    private static final By SAUCELABSBOLTTSHIRTADDTOCART = byXpath("//button[@data-test='add-to-cart-sauce-labs-bolt-t-shirt']");
    public static SelenideElement sauceLabsBlotTshirtAddToCart() {return $(SAUCELABSBOLTTSHIRTADDTOCART).shouldBe(Condition.visible);}

    // Sauce Labs Fleece Jacket 상품 카트에 담기 버튼
    private static final By SAUCELABSFLEECEJACKETADDTOCART = byXpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']");
    public static SelenideElement sauceLabsFleeceJacketAddToCart() {return $(SAUCELABSFLEECEJACKETADDTOCART).shouldBe(Condition.visible);}

    // Sauce Labs Onesie 상품 카트에 담기 버튼
    private static final By SAUCELABSONESIEADDTOCART = byXpath("//button[@data-test='add-to-cart-sauce-labs-onesie']");
    public static SelenideElement sauceLabsOnesieAddToCart() {return $(SAUCELABSONESIEADDTOCART).shouldBe(Condition.visible);}

    // Test.allTheThings() T-Shirt (Red) 상품 카트에 담기 버튼
    private static final By SAUCELABSREDTSHIRTADDTOCART = byXpath("//button[@data-test='add-to-cart-test.allthethings()-t-shirt-(red)']");
    public static SelenideElement sauceLabsRedTshirtAddToCart() {return $(SAUCELABSREDTSHIRTADDTOCART).shouldBe(Condition.visible);}

    // 쇼핑 카트에 담긴 상품 수량
    private static final By SHOPPINGCARTBADGE = byXpath("//span[@data-test='shopping-cart-badge']");
    public static SelenideElement shoppingCartBadge(){return $(SHOPPINGCARTBADGE).shouldBe(Condition.appear, Duration.ofSeconds(15));}
    public static int getShoppingCartBadge() { return Integer.parseInt(shoppingCartBadge().getText());}

    // 쇼핑 카트에서 제거 버튼
    private static final By PRODUCTINCARTREMOVE = byXpath("//button[@data-test='remove-sauce-labs-backpack']");
    public static SelenideElement productInCartRemove() {return $(PRODUCTINCARTREMOVE).shouldBe(Condition.visible);}

    // 쇼핑 카트 상세 페이지에서 뒤로가기 버튼
    private static final By CONTINUESHOPPING = byXpath("//button[@data-test='continue-shopping']");
    public static SelenideElement continueShopping() {return $(CONTINUESHOPPING).shouldBe(Condition.visible);}

    // 쇼핑 카트에서 체크아웃 버튼
    private static final By CHECKOUTBUTTON = byXpath("//button[@data-test='checkout']");
    public static SelenideElement checkoutButton() {return $(CHECKOUTBUTTON).shouldBe(Condition.visible);}

    // 결제 진행 중 Continue 버튼
    private static final By CONTINUEBUTTON = byXpath("//input[@data-test='continue']");
    public static SelenideElement continueButton() {return $(CONTINUEBUTTON).shouldBe(Condition.visible);}

    // First Name 입력란
    private static final By FIRSTNAME = byXpath("//input[@data-test='firstName']");
    public static SelenideElement firstName() {return $(FIRSTNAME).shouldBe(Condition.visible);}

    // Last Name 입력란
    private static final By LASTNAME = byXpath("//input[@data-test='lastName']");
    public static SelenideElement lastName() {return $(LASTNAME).shouldBe(Condition.visible);}

    // 우편번호 입력란
    private static final By POSTALCODE = byXpath("//input[@data-test='postalCode']");
    public static SelenideElement postalCode() {return $(POSTALCODE).shouldBe(Condition.visible);}

    private static final By CHECKOUTERRORMESSAGE = By.cssSelector(".error-message-container");
    public static SelenideElement checkoutErrorMessage() { return $(CHECKOUTERRORMESSAGE).shouldBe(Condition.visible);}

    private static final By FINISHBUTTON = byXpath("//button[@data-test='finish']");
    public static SelenideElement finishButton() { return $(FINISHBUTTON).shouldBe(Condition.visible);}

    private static final By BACKHOMEBUTTON = byXpath("//button[@data-test='back-to-products']");
    public static SelenideElement backHomeButton() {return $(BACKHOMEBUTTON).shouldBe(Condition.visible);}

    private static final By CHECKOUTPRICETOTAL = byXpath("//div[@data-test='subtotal-label']");
    public static SelenideElement checkoutPriceTotal(){ return $(CHECKOUTPRICETOTAL).shouldBe(Condition.visible);}


    // 상품 리스트에서 카트에 담기 버튼
    public static ElementsCollection addToCartButton() {
     ElementsCollection buttons = $$(".btn_inventory");
     buttons.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return buttons;
    }

    public static SelenideElement getAddToCartButton(int index) {
     ElementsCollection items = $$(".inventory_item");
     items.shouldBe(CollectionCondition.sizeGreaterThan(index), Duration.ofSeconds(10));
     return items.get(index).$("button").shouldBe(Condition.appear, Duration.ofSeconds(10));
    }

    // 상품 리스트에서 상품명
    public static ElementsCollection productName() {
     ElementsCollection names = $$(".inventory_item_name");
     names.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return names;
    }

    public static List<String> getProductName() {
     return productName().texts();
    }

    // 카트 상세 페이지의 상품명
    public static ElementsCollection cartProductName() {
     ElementsCollection names = $$(".cart_item .inventory_item_name");
     names.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return names;
    }

    public static ElementsCollection cartProductRemove() {
     ElementsCollection buttons = $$x("//button[text()='Remove']");
     buttons.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return buttons;
    }

    public static ElementsCollection removeButton() {
     ElementsCollection buttons = $$x("//button[text()='Remove']");
     buttons.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return buttons;
    }

    public static ElementsCollection productPrice() {
     ElementsCollection prices = $$(".inventory_item_price");
     prices.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return prices;
    }

    public static List<String> getProductPriceText() {
     return productPrice().texts();
    }

    public static ElementsCollection cartProductPrice() {
     ElementsCollection prices = $$(".cart_item .inventory_item_price");
     prices.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return prices;
    }

    public static ElementsCollection productItem() {
     ElementsCollection items = $$("div.inventory_item");
     items.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return items;
    }

    public static ElementsCollection inventoryDetailName() {
     ElementsCollection names = $$(".inventory_details_name");
     names.shouldBe(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(10));
     return names;
    }

    public static String getInventoryDetailName() {
     return inventoryDetailName().first().getText();
    }





    public static class ProductInfo {
     public final String name;
     public final String price;
     public final int index;

     public ProductInfo(String name, String price, int index) {
      this.name = name;
      this.price = price;
      this.index = index;
     }

     @Override
     public String toString() {
      return name + " - " + price;
     }
    }




    public static class Actions {


        public static final String SAUCEDEMO_URL = "sauceDemo";
        public static Boolean hasAdvertisement = null;

        public static void openPage() {
            String url = PropertyLoader.getConfig().getUrls().get(SAUCEDEMO_URL);
            open(url);

//            if (hasAdvertisement == null) {
//                Utils.sleep(5);
//                hasAdvertisement = $$(CLOSE_MESSAGE_BUTTON).size() > 0;
//            }
//            if (hasAdvertisement) {
//                $(CLOSE_MESSAGE_BUTTON).shouldBe(Condition.visible).click();
//            }
        }
        public static void login(final String username, final String password){
            Home.usernameStandard().sendKeys(username);
            Home.passwordStandard().sendKeys(password);
            Home.loginButton().click();
        }

        public static void clearCart() {
         Home.shoppingCart().click();

         while ($$(".inventory_item .btn_secondary").size() > 0) {
          $$(".inventory_item .btn_secondary").forEach(SelenideElement::click);
         }
        }

        public static void clickProductByName(String name) {
         productName().findBy(Condition.text(name)).click();
        }

        public static void removeButtons() {
         while (true) {
          ElementsCollection buttons = $$x("//button[text()='Remove']");
          if (buttons.isEmpty()) break;
          buttons.get(0).shouldBe(Condition.appear, Duration.ofSeconds(10)).click();
          Utils.sleep(1);
         }
        }

        public static List<String> sortAscendingByProductName(List<String> productName) {
         return productName.stream()
                 .sorted()
                 .collect(Collectors.toList());
        }

     public static List<String> sortDescendingByProductName(List<String> productName) {
      return productName.stream()
              .sorted(Comparator.reverseOrder())
              .collect(Collectors.toList());
     }

        public static List<Double> parsePriceTextToDouble(List<String> priceText) {
         return priceText.stream()
                 .map(p -> p.replace("$", ""))
                 .map(Double::parseDouble)
                 .collect(Collectors.toList());
        }
        public static List<Double> sortDescending(List<Double> prices) {
         return prices.stream()
                 .sorted(Comparator.reverseOrder())
                 .collect(Collectors.toList());
        }

        public static List<Double> sortAscending(List<Double> prices) {
         return prices.stream()
                 .sorted()
                 .collect(Collectors.toList());
        }

        public static List<ProductInfo> addRandomProductsToCart(int count) {
         List<ProductInfo> selectedProducts = new ArrayList<>();
         List<Integer> indices = IntStream.range(0, Home.addToCartButton().size())
                 .boxed()
                 .collect(Collectors.toList());
         Collections.shuffle(indices);

         for (int i = 0; i < count && i < indices.size(); i++) {
          int index = indices.get(i);
          String name = Home.productName().get(index).getText();
          String price = Home.productPrice().get(index).getText();

          Home.addToCartButton().get(index).click();
          Utils.sleep(1);

          selectedProducts.add(new ProductInfo(name, price, index));
          System.out.printf("선택된 상품 [%d]: %s - %s\n", i + 1, name, price);
         }

         return selectedProducts;
        }

        public static void verifyProductsInCart(List<ProductInfo> expectedProducts) {
         shoppingCart().click();
         cartProductName().get(0).shouldBe(Condition.visible); // 장바구니 로딩 확인

         List<String> cartNames = StreamSupport.stream(Home.productName().spliterator(),false)
                 .map(SelenideElement::getText)
                 .collect(Collectors.toList());

         List<String> cartPrices = StreamSupport.stream(Home.productPrice().spliterator(),false)
                 .map(SelenideElement::getText)
                 .collect(Collectors.toList());

         for (ProductInfo expected : expectedProducts) {
          boolean foundMatch = false;
          for (int i = 0; i < cartNames.size(); i++) {
           if (cartNames.get(i).equals(expected.name) && cartPrices.get(i).equals(expected.price)) {
            foundMatch = true;
            break;
           }
          }
          assertTrue(foundMatch,
                  String.format("상품 [%s - %s] 이(가) 장바구니에 없습니다.", expected.name, expected.price));
         }
        }



        public static void verifyProductsNameInCart(List<String> expectedNames) {
         shoppingCart().click();
         cartProductName().get(0).shouldBe(Condition.visible); // 장바구니 로딩 확인

         List<String> cartNames = StreamSupport.stream(Home.productName().spliterator(), false)
                 .map(SelenideElement::getText)
                 .collect(Collectors.toList());

         System.out.println("선택한 상품 목록: " + expectedNames);
         System.out.println("장바구니 상품 목록: " + cartNames);

         assertTrue(
                 cartNames.containsAll(expectedNames),
                 "선택한 상품이 카트에 존재하지 않습니다."
         );
        }

        public static void verifyTotalPrice(List<ProductInfo> products) {

         double expected = products.stream()
                 .map(p -> p.price.replace("$", "").trim())
                 .mapToDouble(Double::parseDouble)
                 .sum();
         System.out.printf("예상 총합: $%.2f\n", expected);

         String totalText = Home.checkoutPriceTotal().getText();
         String totalStr = totalText.replace("Item total: $", "").trim();
         double actual = Double.parseDouble(totalStr);
         System.out.printf("실제 총합: $%.2f (%s)\n", actual, totalText);


         assertTrue(Math.abs(expected - actual) < 0.01, "가격 합계 불일치: expected=" + expected + ", actual=" + actual);
         System.out.println("총 가격이 예상과 일치합니다.");
        }

        public static void removeProductsFromCart(int count) {
         for (int i = 0; i < count; i++) {
          Home.cartProductRemove().get(0).click();
          Utils.sleep(1);
         }
        }


        public static void verifyProductsReset(List<ProductInfo> selectedProducts) {
         for (ProductInfo product : selectedProducts) {
          Home.getAddToCartButton(product.index).shouldHave(Condition.text("Add to cart"));
         }
        }

        public static void fillCheckoutInfo(String firstName, String lastName, String postalCode) {

         // 1단계: First Name 비우고 Continue 클릭 → 에러 메시지 확인
         Home.firstName().sendKeys("");
         Home.continueButton().click();
         Home.checkoutErrorMessage()
                 .shouldBe(Condition.visible)
                 .shouldHave(Condition.text("First Name"));

         // First Name 입력
         Home.firstName().sendKeys(firstName);

         // 2단계: Last Name 비우고 Continue 클릭 → 에러 메시지 확인
         Home.continueButton().click();
         Home.checkoutErrorMessage()
                 .shouldBe(Condition.visible)
                 .shouldHave(Condition.text("Last Name"));

         // Last Name 입력
         Home.lastName().sendKeys(lastName);

         // 3단계: Postal Code 비우고 Continue 클릭 → 에러 메시지 확인
         Home.continueButton().click();
         Home.checkoutErrorMessage()
                 .shouldBe(Condition.visible)
                 .shouldHave(Condition.text("Postal Code"));

         // Postal Code 입력
         Home.postalCode().sendKeys(postalCode);

        }



    }
}
