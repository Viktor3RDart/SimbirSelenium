package pages;

import helpers.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.elements.HomePageButtons;

import java.util.List;

import static helpers.TestData.*;

public class OpenAccountPage extends BaseStep {

    private final WebDriver driver;

    @FindBy(id = "userSelect")
    private WebElement customerNameField;

    @FindBy(id = "currency")
    private WebElement currencyField;

    @FindBy(xpath = "//form/button")
    private WebElement processButton;

    @FindBy(xpath = "//*[@id=\"userSelect\"]/option")
    private List<WebElement> customersList;

    @FindBy(xpath = "//*[@id=\"currency\"]/option")
    private List<WebElement> currencyList;


    public OpenAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Выбрать данные из дроп-дауна на экране Open Account")
    public void selectSomeDataInField(List<WebElement> list, String data) {
        HomePageButtons homePage = new HomePageButtons(driver);
        for (WebElement element : list
        ) {
            if (element.getText().equals(data)) {
                element.click();
                homePage.getOpenAccountButton().click();
            }
        }
    }

    @Step("Добавить новый счет клиенту")
    public void openAccountForClient() {
        selectSomeDataInField(customersList, FULL_NAME);
        selectSomeDataInField(currencyList, CURRENCY);
        clickElement(processButton);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getCustomerNameField() {
        return customerNameField;
    }

    public void setCustomerNameField(WebElement customerNameField) {
        this.customerNameField = customerNameField;
    }

    public WebElement getCurrencyField() {
        return currencyField;
    }

    public void setCurrencyField(WebElement currencyField) {
        this.currencyField = currencyField;
    }

    public WebElement getProcessButton() {
        return processButton;
    }

    public void setProcessButton(WebElement processButton) {
        this.processButton = processButton;
    }

    public List<WebElement> getCustomersList() {
        return customersList;
    }

    public List<WebElement> getCurrencyList() {
        return currencyList;
    }

    public void setCustomersList(List<WebElement> customersList) {
        this.customersList = customersList;
    }

    public void setCurrencyList(List<WebElement> currencyList) {
        this.currencyList = currencyList;
    }
}
