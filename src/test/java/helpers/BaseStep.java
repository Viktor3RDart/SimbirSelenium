package helpers;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BaseStep {

    @Step("Проверка соответствия введенного элемента и полученного")
    public void checkField(Object actual, Object expected) {
        Assert.assertEquals(expected, actual);
    }

    @Step("Проверка видимости элемента")
    public void checkVisibility(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    @Step("Получить актуальный URl страницы")
    public String getActualPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    @Step("Ввод данных {text} в поле")
    public void sendKeys(WebElement element, String text) {
        element.click();
        element.sendKeys(text);
    }

    @Step("Найти необходимое значение в столбце")
    public void checkIsValueInList(List<WebElement> list, String testData) {
        ArrayList<String> texts = new ArrayList<>();
        String check = "null";
        for (WebElement l : list
        ) {
            texts.add(l.getText());
        }
        for (String l : texts) {
            if (l.equals(testData)) {
                check = l;
                break;
            }
        }
        checkField(check, testData);
    }

    @Step("Найти необходимое значение в столбце")
    public void checkSearchValueInList(List<WebElement> list, String testData) {
        for (int i = 0; i < giveListOf(list).size(); i++) {
            checkField(giveListOf(list).get(i), testData);
        }
    }

    @Step("Получить все значения столбца")
    public ArrayList<String> giveListOf(List<WebElement> list) {
        ArrayList<String> texts = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            texts.add(list.get(i).getText());
        }
        return texts;
    }

    @Step("Клик по элементу")
    public void clickElement(WebElement element) {
        element.click();
    }

    @Step("Получить сообщение окна оповещения Chrome")
    public String alertMessageText(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    @Step("Получить уведомление что окна оповещения Chrome не отображается")
    public String alertMessageIsNotDisplayed(WebDriver driver) {
        String error = null;
        try {
            driver.switchTo().alert();
        } catch (Exception e) {
            error = String.valueOf(e);
        }
        if (error == null) {
            throw new AssertionError(
                    "This window have Alert Message about Create Client, check your Data or Test");
        }
        return error.substring(0, 58);
    }

    @Step("Закрыть окно оповещения Chrome")
    public void alertMessageClose(WebDriver driver) {
        driver.switchTo().alert().accept();
    }
}
