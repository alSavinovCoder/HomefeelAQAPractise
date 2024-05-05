package UITest;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MakingOrderPage {
    private final ElementsCollection totalCost = $$x("//span[@class='bx-soa-cart-d']");


    public int getTotalCost() {
        return Integer.parseInt(totalCost.last().getText().replaceAll("[^0-9]", ""));
    }
}
