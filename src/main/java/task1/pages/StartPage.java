package task1.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import org.apache.log4j.Logger;


import java.util.List;


public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//h2[contains(text(), 'LambdaTest Sample App')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//span[contains(text(), '5 of 5 remaining')]")
    private WebElement remainingText;

    @FindBy(xpath = "//li[@class='ng-scope']")
    private List<WebElement> listItems;

    @FindBy(xpath = "//input[@placeholder='Want to add more']")
    private WebElement inputField;

    @FindBy(xpath = "//input[@value='add']")
    private WebElement addButton;

    @FindBy(xpath = "//span[@class='ng-binding']")
    private WebElement countLi;


    @Step(value = "Проверяем, что заголовой LambdaTest Sample App есть")
    public StartPage isPageTitleDisplayed() {
        Assert.assertTrue("Заголовок 'LambdaTest Sample App' не найден",
                pageTitle.isDisplayed() && pageTitle.getText().contains("LambdaTest Sample App"));
        logger.info("Нужный заголовок присутствует на странице");
        return pageManager.getStartPage();
    }

    @Step(value = "Проверяем, что текст '5 of 5 remaining' найден на странице")
    public StartPage isRemainingTextDisplayed() {
        Assert.assertTrue("Текст '5 of 5 remaining' не найден на странице",
                remainingText.isDisplayed() && remainingText.getText().contains("5 of 5 remaining"));
        logger.info("Надпись '5 of 5 remaining' присутствует на странице");
        return pageManager.getStartPage();
    }

    @Step(value = "Добавляем новый элемент списка '{text}'")
    public StartPage addItemToList(String text) {
        inputField.sendKeys(text);
        logger.info("Новый элемент списка добавлен");
        return pageManager.getStartPage();
    }

    @Step(value = "Нажимаем на кнопку добавить новый элемент списка")
    public StartPage clickButton() {
        addButton.click();
        logger.info("Кнопка добавить новый элемент списка нажата");
        return pageManager.getStartPage();
    }

    @Step(value = "Проверяем, что элемент списка '{nameInput}' не зачеркнут, кликаем на " +
            "чекбокс элемента  '{nameInput}', проверяем, что элемент списка стал зачеркнут " +
            "и уменьшилось количество не зачеркнутых элементов")
    public StartPage checkTheListItemAndClick(String nameInput) {
        int number = 0;
        for (WebElement menuItem : listItems) {
            String inputName = menuItem.findElement(By.tagName("input")).getAttribute("name");
            if (inputName.equalsIgnoreCase(nameInput)) {
                String numberText = countLi.getText().split("\\s+")[0];
                number = Integer.parseInt(numberText);
                String spanClass = menuItem.findElement(By.tagName("span")).getAttribute("class");
                Assert.assertTrue("Элемент списка " + nameInput + " зачеркнут", spanClass.contains("done-false"));
                menuItem.findElement(By.xpath(".//input[@type='checkbox']")).click();
            }
        }

        for (WebElement menuItem : listItems) {
            String inputName = menuItem.findElement(By.tagName("input")).getAttribute("name");
            if (inputName.equalsIgnoreCase(nameInput)) {
                String spanClass = menuItem.findElement(By.tagName("span")).getAttribute("class");
                String numberText = countLi.getText().split("\\s+")[0];
                Assert.assertTrue("На один не уменьшилось количество " + nameInput, number - 1 == Integer.parseInt(numberText));
                Assert.assertTrue("Элемент списка " + nameInput + " не становится зачеркнутым", spanClass.contains("true"));
            }
        }

        logger.info("Проверено, что элемент не зачеркнут, далее нажали на чекбокс элемента, " +
                "проверили, что теперь элемент зачеркнут и количество незачеркнутых уменьшилось на 1");
        return pageManager.getStartPage();
    }

}
