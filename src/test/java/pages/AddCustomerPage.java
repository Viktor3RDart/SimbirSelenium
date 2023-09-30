package pages;

import helpers.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.TestData.*;


public class AddCustomerPage extends BaseStep {

    private final WebDriver driver;

    @FindBy(css = "div:nth-child(1)>input")
    private WebElement firstNameField;

    @FindBy(css = "div:nth-child(2)>input")
    private WebElement lastNameField;

    @FindBy(css = "div:nth-child(3)>input")
    private WebElement postCodeField;

    @FindBy(css = "form>button")
    private WebElement addCustomerButton;

    public AddCustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @Step("Полный путь создания клиента без счета")
    public void createClient() {
        sendKeys(getFirstNameField(), FIRST_NAME);
        sendKeys(getLastNameField(), LAST_NAME);
        sendKeys(getPostCodeField(), POST_CODE);
        clickElement(getAddCustomerButton());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(WebElement firstNameField) {
        this.firstNameField = firstNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(WebElement lastNameField) {
        this.lastNameField = lastNameField;
    }

    public WebElement getPostCodeField() {
        return postCodeField;
    }

    public void setPostCodeField(WebElement postCodeField) {
        this.postCodeField = postCodeField;
    }

    public WebElement getAddCustomerButton() {
        return addCustomerButton;
    }

    public void setAddCustomerButton(WebElement addCustomerButton) {
        this.addCustomerButton = addCustomerButton;
    }
}
