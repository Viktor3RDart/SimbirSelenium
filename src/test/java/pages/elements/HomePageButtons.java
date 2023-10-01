package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageButtons {

    private final WebDriver driver;

    @FindBy(xpath = "//div[2]//button[1]")
    private WebElement addCustomerButton;

    @FindBy(xpath = "//div[2]//button[2]")
    private WebElement openAccountButton;

    @FindBy(xpath = "//div[2]//button[3]")
    private WebElement CustomersButton;


    public HomePageButtons(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getAddCustomerButton() {
        return addCustomerButton;
    }

    public void setAddCustomerButton(WebElement addCustomerButton) {
        this.addCustomerButton = addCustomerButton;
    }

    public WebElement getOpenAccountButton() {
        return openAccountButton;
    }

    public void setOpenAccountButton(WebElement openAccountButton) {
        this.openAccountButton = openAccountButton;
    }

    public WebElement getCustomersButton() {
        return CustomersButton;
    }

    public void setCustomersButton(WebElement customersButton) {
        CustomersButton = customersButton;
    }
}
