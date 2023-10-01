package tests;

import helpers.BaseStep;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AddCustomerPage;
import pages.CustomersPage;
import pages.OpenAccountPage;
import pages.elements.HomePageButtons;

import static helpers.Endpoints.*;
import static helpers.Wait.*;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Тест-кейсы Экран Manager")
public class MainPageTest extends BaseStep {

    private WebDriver driver;

    @BeforeEach
    @Step("Подготовка старта драйвера, переход на страницу")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        waitRunBeforeStart(driver, 3);
        driver.get(BASIC_URL);
    }

    @Test
    @DisplayName("Тест №01 - Отображение кнопок Экран Manager")
    @Description(value = "Отображение кнопок — Add Customer, Open Account, Customers")
    @Severity(BLOCKER)
    public void buttonVisabilityTest() {
        HomePageButtons homePage = new HomePageButtons(driver);
        assertAll(
                () -> checkVisibility(homePage.getAddCustomerButton()),
                () -> checkVisibility(homePage.getAddCustomerButton()),
                () -> checkVisibility(homePage.getAddCustomerButton()));
    }

    @Test
    @DisplayName("Тест №02 - Переход на вкладку: /addCust")
    @Description(value = "Клик по  кнопке — Add Customer  инициирует переход на вкладку:" +
            " https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust")
    @Severity(BLOCKER)
    public void GoOnAddCustomerTest() {
        HomePageButtons homePage = new HomePageButtons(driver);
        AddCustomerPage customerPage = new AddCustomerPage(driver);
        clickElement(homePage.getAddCustomerButton());
        assertAll(
                () -> checkVisibility(customerPage.getFirstNameField()),
                () -> checkField(getActualPageUrl(driver), ADD_CUSTOMER_PAGE_URL));
    }

    @Test
    @DisplayName("Тест №03 - Переход на вкладку: /openAccount")
    @Description(value = "Клик по  кнопке —  Open Account  инициирует переход на вкладку:" +
            " https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/openAccount")
    @Severity(BLOCKER)
    public void GoOnOpenAccountTest() {
        HomePageButtons homePage = new HomePageButtons(driver);
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(
                () -> checkVisibility(accountPage.getCustomerNameField()),
                () -> checkField(getActualPageUrl(driver), OPEN_ACCOUNT_PAGE_URL));
    }

    @Test
    @DisplayName("Тест №04 - Переход на вкладку: /list")
    @Description(value = "Клик по  кнопке —  Customers  инициирует переход на вкладку:" +
            " https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list")
    @Severity(BLOCKER)
    public void GoOnCustomersTest() {
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        clickElement(homePage.getCustomersButton());
        assertAll(
                () -> checkVisibility(customersPage.getFirstNameTableTitle()),
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL));
    }

    @AfterEach
    @Step("Закрыть драйвер")
    public void tearDown() {
        driver.quit();
    }
}
