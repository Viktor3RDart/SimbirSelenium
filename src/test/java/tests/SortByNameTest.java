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
import pages.CustomersPage;
import pages.elements.HomePageButtons;

import static helpers.Endpoints.BASIC_URL;
import static helpers.Wait.waitRunBeforeStart;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName(value = "Тест-кейсы Сортировка клиентов")
public class SortByNameTest extends BaseStep {

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
    @DisplayName("Тест №14 - Отображение таблицы с 5 столбцами")
    @Description(value = "Отображение таблицы с 5 столбцами -  First Name, Last Name, Post Code, Account Number, " +
            "Delete Customer на вкладке Customers")
    @Severity(NORMAL)
    public void customersTabVisabilityTest() {
        CustomersPage customersPage = new CustomersPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getCustomersButton());
        assertAll(
                () -> checkVisibility(customersPage.getFirstNameTableTitle()),
                () -> checkVisibility(customersPage.getLastNameTableTitle()),
                () -> checkVisibility(customersPage.getPostCodeTableTitle()),
                () -> checkVisibility(customersPage.getAccountNumberTableTitle()),
                () -> checkVisibility(customersPage.getDeleteCustomerTableTitle()));
    }

    @Test
    @DisplayName("Тест №15 - Сортировка клиентов по имени. Сорт от последнего к первому.")
    @Description(value = "Сортировка клиентов по имени. Сорт от последнего к первому.")
    @Severity(NORMAL)
    public void sortCustomersByNameLastToFirstTest() {
        CustomersPage customersPage = new CustomersPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getCustomersButton());
        clickElement(customersPage.getFirstNameTableTitle());
        checkField(giveListOf(customersPage.getFirstNameList()),
                customersPage.sortListReverseOrder(giveListOf(customersPage.getFirstNameList())));
    }

    @Test
    @DisplayName("Тест №16 - Сортировка клиентов по имени. Сорт от первого к последнему.")
    @Description(value = "Сортировка клиентов по имени. Сорт от первого к последнему.")
    @Severity(NORMAL)
    public void sortCustomersByNameFirstToLastTest() {
        CustomersPage customersPage = new CustomersPage(driver);
        HomePageButtons homePage = new HomePageButtons(driver);
        clickElement(homePage.getCustomersButton());
        clickElement(customersPage.getFirstNameTableTitle());
        clickElement(customersPage.getFirstNameTableTitle());
        checkField(giveListOf(customersPage.getFirstNameList()),
                customersPage.sortListNaturalOrder(giveListOf(customersPage.getFirstNameList())));
    }

    @AfterEach
    @Step("Закрыть драйвер")
    public void tearDown() {
        driver.quit();
    }
}
