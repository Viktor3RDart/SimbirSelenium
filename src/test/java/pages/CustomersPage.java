package pages;

import helpers.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class CustomersPage extends BaseStep {

    private final WebDriver driver;

    @FindBy(css = "td:nth-child(1)>a")
    private WebElement firstNameTableTitle;

    @FindBy(css = "td:nth-child(1)>a")
    private WebElement lastNameTableTitle;

    @FindBy(css = "td:nth-child(1)>a")
    private WebElement postCodeTableTitle;

    @FindBy(xpath = "//thead//td[4]")
    private WebElement accountNumberTableTitle;

    @FindBy(xpath = "//thead//td[4]")
    private WebElement deleteCustomerTableTitle;

    @FindBy(css = "div>input")
    private WebElement searchCustomerField;

    @FindBy(css = "td:nth-child(1)")
    private List<WebElement> firstNameList;

    @FindBy(css = "td:nth-child(2)")
    private List<WebElement> lastNameList;

    @FindBy(css = "td:nth-child(3)")
    private List<WebElement> postCodeList;

    @FindBy(css = "td:nth-child(4)")
    private List<WebElement> accountNumberList;

    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Отсортировать список по порядку 'от А до Я'")
    public ArrayList<String> sortListNaturalOrder(ArrayList<String> list) {
        list.sort(Comparator.naturalOrder());
        return new ArrayList<>(list);
    }

    @Step("Отсортировать список по порядку 'от Я до А'")
    public ArrayList<String> sortListReverseOrder(ArrayList<String> list) {
        list.sort(Comparator.reverseOrder());
        return new ArrayList<>(list);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getFirstNameTableTitle() {
        return firstNameTableTitle;
    }

    public void setFirstNameTableTitle(WebElement firstNameTableTitle) {
        this.firstNameTableTitle = firstNameTableTitle;
    }

    public WebElement getLastNameTableTitle() {
        return lastNameTableTitle;
    }

    public void setLastNameTableTitle(WebElement lastNameTableTitle) {
        this.lastNameTableTitle = lastNameTableTitle;
    }

    public WebElement getPostCodeTableTitle() {
        return postCodeTableTitle;
    }

    public void setPostCodeTableTitle(WebElement postCodeTableTitle) {
        this.postCodeTableTitle = postCodeTableTitle;
    }

    public WebElement getAccountNumberTableTitle() {
        return accountNumberTableTitle;
    }

    public void setAccountNumberTableTitle(WebElement accountNumberTableTitle) {
        this.accountNumberTableTitle = accountNumberTableTitle;
    }

    public WebElement getDeleteCustomerTableTitle() {
        return deleteCustomerTableTitle;
    }

    public void setDeleteCustomerTableTitle(WebElement deleteCustomerTableTitle) {
        this.deleteCustomerTableTitle = deleteCustomerTableTitle;
    }

    public WebElement getSearchCustomerField() {
        return searchCustomerField;
    }

    public void setSearchCustomerField(WebElement searchCustomerField) {
        this.searchCustomerField = searchCustomerField;
    }

    public List<WebElement> getFirstNameList() {
        return firstNameList;
    }

    public void setFirstNameList(List<WebElement> firstNameList) {
        this.firstNameList = firstNameList;
    }

    public List<WebElement> getLastNameList() {
        return lastNameList;
    }

    public void setLastNameList(List<WebElement> lastNameList) {
        this.lastNameList = lastNameList;
    }

    public List<WebElement> getPostCodeList() {
        return postCodeList;
    }

    public void setPostCodeList(List<WebElement> postCodeList) {
        this.postCodeList = postCodeList;
    }

    public List<WebElement> getAccountNumberList() {
        return accountNumberList;
    }

    public void setAccountNumberList(List<WebElement> accountNumberList) {
        this.accountNumberList = accountNumberList;
    }
}
