package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {

    @Step("Выставить ожидание менеджера драйвера для страницы")
    public static void waitRunBeforeStart(WebDriver driver, int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    @Step("Ожидание элемента общее")
    public static WebDriverWait waitMainByAllElements(WebDriver driver, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    @Step("Ожидать появление элемента на странице")
    public static void waitElement(WebDriver driver, WebElement element) {
        waitMainByAllElements(driver, 3).until(ExpectedConditions.visibilityOf(element));
    }
}
