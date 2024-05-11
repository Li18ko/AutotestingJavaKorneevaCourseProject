package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;

import java.util.List;


public class StartPage extends BasePage {

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


    public StartPage isPageTitleDisplayed() {
        Assert.assertTrue("Заголовок 'LambdaTest Sample App' не найден",
                pageTitle.isDisplayed() && pageTitle.getText().contains("LambdaTest Sample App"));
        return pageManager.getStartPage();
    }

    public StartPage isRemainingTextDisplayed() {
        Assert.assertTrue("Текст '5 of 5 remaining' не найден на странице",
                remainingText.isDisplayed() && remainingText.getText().contains("5 of 5 remaining"));
        return pageManager.getStartPage();
    }

    public StartPage addItemToList(String text) {
        inputField.sendKeys(text);
        return pageManager.getStartPage();
    }

    public StartPage clickButton() {
        addButton.click();
        return pageManager.getStartPage();
    }

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
        return pageManager.getStartPage();
    }

}
