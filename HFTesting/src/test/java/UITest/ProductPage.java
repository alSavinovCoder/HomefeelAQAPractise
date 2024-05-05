package UITest;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс для работы с страницей со штопором
 */
public class ProductPage {
    private final SelenideElement productName = $x("//h1[@class='product-title']");
    private final SelenideElement addToCartButton = $x("//a[@class='langery-item-detail-add__btn _in-cart btn_st']");
    private final SelenideElement goToCartButton = $x("//a[@href='/cart/']");
    private final SelenideElement productPrice = $x("//div[@class='product-price']");

    /**
     * Возвращает название товара
     * @return
     */
    public String getProductName() {
        return productName.getText();
    }
    public int getProductPrice() {
        return Integer.parseInt(productPrice.getText().replaceAll("[^0-9]", ""));
    }

    /**
     * Добавляет товар в корзину и переходит на страницу корзины
     * @return
     */
    public CartPage addProductAndGoToCart() {
        addToCartButton.click();
        goToCartButton.click();
        return new CartPage();
    }
}
