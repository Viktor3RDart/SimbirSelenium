package tests;

import helpers.BaseStep;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AddCustomerPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CustomersPage;
import pages.OpenAccountPage;
import pages.elements.HomePageButtons;

import static helpers.Endpoints.*;
import static helpers.TestData.*;
import static helpers.Wait.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import static io.qameta.allure.SeverityLevel.CRITICAL;

@DisplayName(value = "Тест-кейсы Создание клиента")
public class CreateClientTest extends BaseStep {

    private WebDriver driver;

    @BeforeEach
    @Step("Подготовка старта драйвера, переход на страницу")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        waitRunBeforeStart(driver, 3);
        driver.get(BASIC_URL);
    }

    @Test
    @DisplayName("Тест №05 - Отображение  3 полей  First Name, Last Name, Post Code")
    @Description(value = "Отображение  3 полей  First Name, Last Name, Post Code на вкладке Add Customer")
    @Severity(CRITICAL)
    public void addCustomerFieldsVisabilityTest() {
        AddCustomerPage customerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getAddCustomerButton());
        assertAll(
                () -> checkVisibility(customerPage.getFirstNameField()),
                () -> checkVisibility(customerPage.getLastNameField()),
                () -> checkVisibility(customerPage.getPostCodeField()));
    }

    @Test
    @DisplayName("Тест №06 - Отображение кнопки  Add Customer")
    @Description(value = "Отображение   1 кнопки  Add Customer на вкладке Add Customer")
    @Severity(CRITICAL)
    public void addCustomerButtonVisabilityTest() {
        AddCustomerPage customerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getAddCustomerButton());
        assertAll(() -> checkVisibility(customerPage.getAddCustomerButton()));
    }

    @Test
    @DisplayName("Тест №07 - Создание клиента без счета")
    @Description(value = "Создание клиента без счета — все поля First Name, Last Name, Post Code  заполнены")
    @Severity(CRITICAL)
    public void createClientWithoutAccountTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        clickElement(homePage.getAddCustomerButton());
        addCustomerPage.createClient();
        assertAll(() -> checkField(alertMessageText(driver), CUSTOMER_PAGE_GOOD_TRY_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getFirstNameTableTitle());
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkIsValueInList(customersPage.getFirstNameList(), FIRST_NAME),
                () -> checkIsValueInList(customersPage.getLastNameList(), LAST_NAME),
                () -> checkIsValueInList(customersPage.getPostCodeList(), POST_CODE));
    }

    @Test
    @DisplayName("Тест №08 - Создание клиента — поле First Name не заполнено")
    @Description(value = "Создание клиента — поле First Name не заполнено, остальные поля заполнены")
    @Severity(CRITICAL)
    public void createClientWithoutFirstNameTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        clickElement(homePage.getAddCustomerButton());
        sendKeys(addCustomerPage.getLastNameField(), LAST_NAME);
        sendKeys(addCustomerPage.getPostCodeField(), POST_CODE);
        clickElement(addCustomerPage.getAddCustomerButton());
        assertAll(() -> checkField(alertMessageIsNotDisplayed(driver), CUSTOMER_PAGE_DO_NOT_HAVE_ALERT_MESSAGE));
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getFirstNameTableTitle());
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkIsValueInList(customersPage.getLastNameList(), NULL_TEST_DATA),
                () -> checkIsValueInList(customersPage.getPostCodeList(), NULL_TEST_DATA));
    }

    @Test
    @DisplayName("Тест №09 - Создание клиента — поле Last Name не заполнено")
    @Description(value = "Создание клиента — поле Last Name не заполнено, остальные поля заполнены")
    @Severity(CRITICAL)
    public void createClientWithoutLastNameTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        clickElement(homePage.getAddCustomerButton());
        sendKeys(addCustomerPage.getFirstNameField(), FIRST_NAME);
        sendKeys(addCustomerPage.getPostCodeField(), POST_CODE);
        clickElement(addCustomerPage.getAddCustomerButton());
        assertAll(() -> checkField(alertMessageIsNotDisplayed(driver), CUSTOMER_PAGE_DO_NOT_HAVE_ALERT_MESSAGE));
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getFirstNameTableTitle());
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkIsValueInList(customersPage.getFirstNameList(), NULL_TEST_DATA),
                () -> checkIsValueInList(customersPage.getPostCodeList(), NULL_TEST_DATA));
    }

    @Test
    @DisplayName("Тест №10 - Создание клиента — поле Post Code не заполнено")
    @Description(value = "Создание клиента — поле Post Code не заполнено, остальные поля заполнены")
    @Severity(CRITICAL)
    public void createClientWithoutPostCodeTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        clickElement(homePage.getAddCustomerButton());
        sendKeys(addCustomerPage.getFirstNameField(), FIRST_NAME);
        sendKeys(addCustomerPage.getLastNameField(), LAST_NAME);
        clickElement(addCustomerPage.getAddCustomerButton());
        assertAll(() -> checkField(alertMessageIsNotDisplayed(driver), CUSTOMER_PAGE_DO_NOT_HAVE_ALERT_MESSAGE));
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getFirstNameTableTitle());
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkIsValueInList(customersPage.getFirstNameList(), NULL_TEST_DATA),
                () -> checkIsValueInList(customersPage.getLastNameList(), NULL_TEST_DATA));
    }

    @Test
    @DisplayName("Тест №11 - Отображение  2 полей   Customer, Currency")
    @Description(value = "Отображение  2 полей   Customer, Currency  на вкладке Open Account")
    @Severity(CRITICAL)
    public void openAccountFieldsVisabilityTest() {
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(
                () -> checkVisibility(accountPage.getCurrencyField()),
                () -> checkVisibility(accountPage.getCustomerNameField()));
    }

    @Test
    @DisplayName("Тест №12 - Отображение   1 кнопки  Process")
    @Description(value = "Отображение   1 кнопки  Process на вкладке Open Account")
    @Severity(CRITICAL)
    public void openAccountButtonVisabilityTest() {
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(() -> checkVisibility(accountPage.getProcessButton()));
    }

    @Test
    @DisplayName("Тест №13 - Создание клиента с счетом")
    @Description(value = "Создание клиента с счетом — все поля First Name, Last Name, Post Code  заполнены")
    @Severity(CRITICAL)
    public void createClientWithAccountTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        CustomersPage customersPage = new CustomersPage(driver);
        OpenAccountPage accountPage = new OpenAccountPage(driver);
        clickElement(homePage.getAddCustomerButton());
        addCustomerPage.createClient();
        assertAll(() -> checkField(alertMessageText(driver), CUSTOMER_PAGE_GOOD_TRY_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        clickElement(homePage.getOpenAccountButton());
        assertAll(() -> checkVisibility(accountPage.getCustomerNameField()));
        accountPage.openAccountForClient();
        assertAll(() -> checkField(alertMessageText(driver), OPEN_ACCOUNT_ALERT_MESSAGE_TEXT));
        alertMessageClose(driver);
        clickElement(homePage.getCustomersButton());
        waitElement(driver, customersPage.getFirstNameTableTitle());
        assertAll(
                () -> checkField(getActualPageUrl(driver), CUSTOMER_PAGE_URL),
                () -> checkIsValueInList(customersPage.getFirstNameList(), FIRST_NAME),
                () -> checkIsValueInList(customersPage.getLastNameList(), LAST_NAME),
                () -> checkIsValueInList(customersPage.getPostCodeList(), POST_CODE),
                () -> checkIsValueInList(customersPage.getAccountNumberList(), TEST_ACCOUNT));
    }


    @Test
    @DisplayName("Тест №22 - Создание двух идентичных клиентов")
    @Description(value = "Создание двух идентичных клиентов  — все поля First Name, Last Name, Post Code  совпадают")
    @Severity(CRITICAL)
    public void createIdenticalClientsTest() {
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getAddCustomerButton());
        addCustomerPage.createClient();
        addCustomerPage.createClient();
        assertAll(() -> checkField(alertMessageText(driver), CUSTOMER_PAGE_DUPLICATES_ALERT_MESSAGE_TEXT));
    }

    @AfterEach
    @Step("Закрыть драйвер")
    public void tearDown() {
        driver.quit();
    }
}