package Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

import java.util.Optional;

public class TestListener implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle()
                .addAttachment(
                        "Скриншот на месте падения теста", "image/png", "png",
                        ((TakesScreenshot) BaseTest.eventFiringWebDriver).getScreenshotAs(OutputType.BYTES)
                );
        Allure.addAttachment("Логи после падения теста: ",
                String.valueOf(
                        BaseTest.eventFiringWebDriver.manage()
                                .logs()
                                .get(LogType.BROWSER).
                                getAll()
                ));
        WebDriverManager.chromedriver().quit();
        BaseTest.eventFiringWebDriver.quit();
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {

    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment("Скриншот после успешного теста", "image/png", "png",
                        ((TakesScreenshot) BaseTest.eventFiringWebDriver).getScreenshotAs(OutputType.BYTES)
                );
        Allure.addAttachment("Логи после успешного прохождения теста: ", String.valueOf(
                        BaseTest.eventFiringWebDriver.manage()
                                .logs()
                                .get(LogType.BROWSER).
                                getAll()
                ));
        WebDriverManager.chromedriver().quit();
        BaseTest.eventFiringWebDriver.quit();

    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {

    }
}
