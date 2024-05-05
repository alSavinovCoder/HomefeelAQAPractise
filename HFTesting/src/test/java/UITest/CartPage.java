package UITest;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage {
    private final ElementsCollection products = $$x("//a[@class='product-item__title']");
    private final ElementsCollection increaseProductsButton = $$x("//span[@class='num_pluss']");
    private final SelenideElement makingOrderButton = $x("//button[@class='result__content-bottom__link']");
    /**
     * Возвращает название первого добаленого товара в корзине
     * @return Название первого добаленого товара в корзине
     */
    public String getFirstProductName(){
        return products.first().getText();
    }

    /**
     * Увеличивает количество товара в корзине на 2 шт
     * @return Страница оформления заказа с итоговой стоимостью
     */
    public MakingOrderPage increaseProduct() throws InterruptedException {
        increaseProductsButton.first().click();
        increaseProductsButton.first().click();
        makingOrderButton.click();
        return new MakingOrderPage();
    }

}
