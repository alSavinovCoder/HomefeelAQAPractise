package Base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

abstract public class BaseTest {

    //комада в консоли для создания отчёта "allure generate build/allure-results --clean"

    protected static EventFiringWebDriver eventFiringWebDriver;

    public void setUp() {
        WebDriverManager.edgedriver().setup();
        Configuration.browser = "edge";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        eventFiringWebDriver = new EventFiringWebDriver(new EdgeDriver());
        eventFiringWebDriver.manage().window().maximize();
    }
    @BeforeEach
    public void init() {
        setUp();
    }
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
