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

import static helpers.Endpoints.BASIC_URL;
import static helpers.Endpoints.CUSTOMER_PAGE_URL;
import static helpers.TestData.*;
import static helpers.Wait.waitElement;
import static helpers.Wait.waitRunBeforeStart;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName(value = "Тест-кейсы Поиск клиента")
public class SearchClientDataTest extends BaseStep {

    private WebDriver driver;

    @BeforeEach
    @Step("Подготовка старта драйвера, переход на страницу")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
        waitRunBeforeStart(driver, 3);
        driver.get(BASIC_URL);
    }

    @Test
    @DisplayName("Тест №17 - Отображение 1 поля поиска Search Customer")
    @Description(value = "Отображение 1 поля поиска Search Customer на вкладке Customers")
    @Severity(CRITICAL)
    public void customersSearchFieldVisabilityTest() {
        CustomersPage customersPage = new CustomersPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getCustomersButton());
        assertAll(() -> checkVisibility(customersPage.getSearchCustomerField()));
    }

    @Test
    @DisplayName("Тест №18 - Поиск данных клиента в поле Search Customer  по First Name")
    @Description(value = "Поиск данных клиента в поле Search Customer  по First Name")
    @Severity(NORMAL)
    public void findClientByFirstNameTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        // Сначала создается клиент, данные по которому далее будем искать.
        clickElement(homePage.getAddCustomerButton());
        addCustomerPage.createClient();
        assertAll(() -> checkField(alertMessageText(driver), CUSTOMER_PAGE_GOOD_TRY_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(() -> checkVisibility(accountPage.getCustomerNameField()));
        accountPage.openAccountForClient();
        assertAll(() -> checkField(alertMessageText(driver), OPEN_ACCOUNT_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        // Далее ищем данные по клиенту
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getSearchCustomerField());
        sendKeys(customersPage.getSearchCustomerField(), FIRST_NAME);
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkSearchValueInList(customersPage.getFirstNameList(), FIRST_NAME));
    }

    @Test
    @DisplayName("Тест №19 - Поиск данных клиента в поле Search Customer  по Last Name")
    @Description(value = "Поиск данных клиента в поле Search Customer  по Last Name")
    @Severity(NORMAL)
    public void findClientByLastNameTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        // Сначала создается клиент, данные по которому далее будем искать.
        clickElement(homePage.getAddCustomerButton());
        addCustomerPage.createClient();
        assertAll(() -> checkField(alertMessageText(driver), CUSTOMER_PAGE_GOOD_TRY_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(() -> checkVisibility(accountPage.getCustomerNameField()));
        accountPage.openAccountForClient();
        assertAll(() -> checkField(alertMessageText(driver), OPEN_ACCOUNT_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        // Далее ищем данные по клиенту
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getSearchCustomerField());
        sendKeys(customersPage.getSearchCustomerField(), LAST_NAME);
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkSearchValueInList(customersPage.getLastNameList(), LAST_NAME));
    }

    @Test
    @DisplayName("Тест №20 - Поиск данных клиента в поле Search Customer  по Post Code")
    @Description(value = "Поиск данных клиента в поле Search Customer  по Post Code")
    @Severity(NORMAL)
    public void findClientByPostCodeTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        // Сначала создается клиент, данные по которому далее будем искать.
        clickElement(homePage.getAddCustomerButton());
        addCustomerPage.createClient();
        assertAll(() -> checkField(alertMessageText(driver), CUSTOMER_PAGE_GOOD_TRY_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(() -> checkVisibility(accountPage.getCustomerNameField()));
        accountPage.openAccountForClient();
        assertAll(() -> checkField(alertMessageText(driver), OPEN_ACCOUNT_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        // Далее ищем данные по клиенту
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getSearchCustomerField());
        sendKeys(customersPage.getSearchCustomerField(), POST_CODE);
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkSearchValueInList(customersPage.getPostCodeList(), POST_CODE));
    }

    @Test
    @DisplayName("Тест №21 - Поиск данных клиента в поле Search Customer  по Account Number")
    @Description(value = "Поиск данных клиента в поле Search Customer  по Account Number")
    @Severity(NORMAL)
    public void findClientByAccountNumberTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        // Сначала создается клиент, данные по которому далее будем искать.
        clickElement(homePage.getAddCustomerButton());
        addCustomerPage.createClient();
        assertAll(() -> checkField(alertMessageText(driver), CUSTOMER_PAGE_GOOD_TRY_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(() -> checkVisibility(accountPage.getCustomerNameField()));
        accountPage.openAccountForClient();
        assertAll(() -> checkField(alertMessageText(driver), OPEN_ACCOUNT_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        // Далее ищем данные по клиенту
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getSearchCustomerField());
        sendKeys(customersPage.getSearchCustomerField(), TEST_ACCOUNT);
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkSearchValueInList(customersPage.getAccountNumberList(), TEST_ACCOUNT));
    }

    @AfterEach
    @Step("Закрыть драйвер")
    public void tearDown() {
        driver.quit();
    }
}

