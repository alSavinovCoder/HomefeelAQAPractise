import Base.BaseTest;
import UITest.MainPage;
import UITest.MakingOrderPage;
import UITest.ProductPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Основной класс UI тестов сайта Homefeel
 */
public class UITestClass extends BaseTest {
    private final static String BASE_URL = "https://homefeel.ru/";
    private final static String SEARCH_ID = "109-160";
    private final static String WRONG_SEARCH_ID = "109-160-160";
    private final static String EXPECTED_WORD = "Pulltex Штопор в кожаном футляре";


    //private final static String EMAIL = "savinooov13@gmail.com";
    //private final static String PASSWORD = "AQA1lovelove";


    /**
     * Тестовый метод для проверки работы поиска по аритулу
     * @return true если название товара "Pulltex Штопор в кожаном футляре"
     */
    @Test
    public void productSearchTestTrue() {
        Assert.assertTrue(new MainPage(BASE_URL)
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
    public void productSearchTestFalse() {
        Assert.assertTrue(new MainPage(BASE_URL)
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
    public void fillingCartTest() {
        Assert.assertTrue(new MainPage(BASE_URL)
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
    @Test
    //TODO: тест постоянно падает из-за того, что на странице не так быстро пересчитываестся стоимость товара
    public void cartPriceTest() throws InterruptedException {
        ProductPage productPage = new MainPage(BASE_URL).searchButton(SEARCH_ID);
        int expectedPrice = productPage.getProductPrice() * 3; //берём стоимость товара со старницы и умножаем на 3 (количество товара в корзине)
        MakingOrderPage makingOrderPage = productPage.addProductAndGoToCart().increaseProduct();
        int actualPrice = makingOrderPage.getTotalCost(); //берём стоимость товара со страницы корзины
        Assert.assertTrue(expectedPrice == actualPrice);
    }

    /**
     * Тестовый метод для проверки работы авторизаци
     * @return true если авторизация прошла успешно
     */
    @Test
    public void authorizationTest() {

    }
}
