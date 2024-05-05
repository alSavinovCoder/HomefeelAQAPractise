import Base.BaseTest;
import Base.TestListener;
import UITest.MainPage;
import UITest.MakingOrderPage;
import UITest.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Основной класс UI тестов сайта Homefeel
 */
@DisplayName("Некоторые UI тесты")
@ExtendWith(TestListener.class)
public class UITestClass extends BaseTest {
    private final static String BASE_URL = "https://homefeel.ru/";
    private final static String SEARCH_ID = "109-160";
    private final static String WRONG_SEARCH_ID = "109-160-160";
    private final static String EXPECTED_WORD = "Pulltex Штопор в кожаном футляре";

    /**
     * Тестовый метод для проверки работы поиска по аритулу
     * @return true если название товара "Pulltex Штопор в кожаном футляре"
     */
    @Test
    @DisplayName("Проверка поиска по артикулу")
    public void productSearchTestTrue() {
        Assertions.assertTrue(new MainPage(BASE_URL)
                .searchButton(SEARCH_ID)
                .getProductName()
                .equals(EXPECTED_WORD));
    }
    /**
     * Тестовый метод для проверки работы поиска по аритулу
     * Метод всегда будет возвращать false, т.к. поиск по такому артикулу ничего не найдёт
     * Создан для проверки работы Allure в случае ошибки
     */
    @Test
    @DisplayName("Проверка поиска по артикулу, которого нет")
    public void productSearchTestFalse() {
        Assertions.assertTrue(new MainPage(BASE_URL)
                .searchButton(WRONG_SEARCH_ID)
                .getProductName()
                .equals(EXPECTED_WORD));
    }

    /**
     * Тестовый метод для проверки добавления товара в корзину
     * и коректного его отображения в корзине
     * @return true если товар добавлен в корзину и отображается в корзине
     */
    @Test
    @DisplayName("Проверка добавления товара в корзину")
    public void fillingCartTest() {
        Assertions.assertTrue(new MainPage(BASE_URL)
                .searchButton(SEARCH_ID)
                .addProductAndGoToCart()
                .getFirstProductName()
                .equals(EXPECTED_WORD));
    }

    /**
     * Тестовоый метод для проверки правильности расчёта
     * стоимости товара при увеличении количестваего количества в корзине
     * @return true если стоимость товара увеличилась корректно
     */
    @RepeatedTest(3) //Тест периодически падает, так как не успевает пересчитать итоговую стоимость корзины
    @DisplayName("Проверка правильности расчёта стоимости товара при увеличении количества")
    public void cartPriceTest() throws InterruptedException {
        ProductPage productPage = new MainPage(BASE_URL).searchButton(SEARCH_ID);
        int expectedPrice = productPage.getProductPrice() * 3; //берём стоимость товара со старницы и умножаем на 3 (количество товара в корзине)
        MakingOrderPage makingOrderPage = productPage.addProductAndGoToCart().increaseProduct();
        int actualPrice = makingOrderPage.getTotalCost(); //берём стоимость товара со страницы корзины
        Assertions.assertTrue(expectedPrice == actualPrice);
    }

    /*
    //private final static String EMAIL = "savinooov13@gmail.com";
    //private final static String PASSWORD = "AQA1lovelove";
    /**
     * Тестовый метод для проверки работы авторизаци
     * @return true если авторизация прошла успешно

    @Test
    @DisplayName("Проверка авторизации")
    public void authorizationTest() {

    }
    */
}
