package UITest;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта Homefeel
 */
public class MainPage {
    private final SelenideElement searchInput = $x("//input[@id='title-search-input']");
    private final SelenideElement productItem = $x("//div[@id='bx_3966226736_376629']");
    public MainPage(String url) {
        Selenide.open(url);
    }

    /**
     * Выполняется поиск среди статей, нажимается кнопка Enter на
     * @param searchString
     * @return
     */

    public ProductPage searchButton(String searchString) {
        searchInput.setValue(searchString);
        searchInput.sendKeys(Keys.ENTER);
        productItem.click();
        return new ProductPage();
    }

}
