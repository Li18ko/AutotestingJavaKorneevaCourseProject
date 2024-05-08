package task1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import task1.StartPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task1 {
    public WebDriver driver;
    public static StartPage startPage;

    @Before
    public void before(){
        System.setProperty("webdriver.edge.driver", "src/test/resources/task1/msedgedriver.exe");
        driver = new EdgeDriver();
        startPage = new StartPage(driver);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://lambdatest.github.io/sample-todo-app/");
    }

    @Test
    public void test(){
        Assert.assertTrue("Заголовок 'LambdaTest Sample App' не найден", startPage.isPageTitleDisplayed());
        Assert.assertTrue("Текст '5 of 5 remaining' не найден на странице", startPage.isRemainingTextDisplayed());

        int listItemsCount = startPage.getListItemsCount();

        for (int i = 1; i <= listItemsCount; i++) {
            String spanClassAttributeValue = driver.findElement(By.xpath("//li[" + i + "]/span")).getAttribute("class");
            Assert.assertTrue("Элемент списка " + i + " зачеркнут", spanClassAttributeValue.contains("done-false"));

            // Ставим галочку у текущего элемента
            WebElement itemCheckbox = driver.findElement(By.xpath("//li[" + i + "]//input[@type='checkbox']"));
            itemCheckbox.click();

            // Проверяем, что текст счетчика изменился
            String expectedRemainingText = (listItemsCount - i) + " of " + listItemsCount + " remaining";
            WebElement remainingTextElement = driver.findElement(By.xpath("//span[contains(text(), '" + expectedRemainingText + "')]"));
            Assert.assertTrue("Текст '" + expectedRemainingText + "' не найден на странице", remainingTextElement.isDisplayed());

            // Проверяем, что элемент списка зачеркнут
            spanClassAttributeValue = driver.findElement(By.xpath("//li[" + i + "]/span")).getAttribute("class");
            Assert.assertTrue("Элемент списка " + i + " не становится зачеркнутым", spanClassAttributeValue.contains("done-true"));
        }

        // Шаг 6: Добавить новый элемент списка
        String newItemText = "Новый элемент списка";
        startPage.addItemToList(newItemText);

        // Нахождение кнопки "Add" по значению атрибута "value" и нажатие на нее
        startPage.clickButton();
        int new_item = 1;
        listItemsCount += 1;

        String spanClassAttributeValue = driver.findElement(By.xpath("//li[" + listItemsCount + "]/span")).getAttribute("class");
        Assert.assertTrue("Шестой элемент списка зачеркнут", spanClassAttributeValue.contains("done-false"));
        WebElement remainingText = driver.findElement(By.xpath("//span[contains(text(), '1 of " + listItemsCount + "')]"));
        Assert.assertTrue("Текст '" + new_item + " of " + listItemsCount + " remaining' не найден на странице", remainingText.isDisplayed());
        WebElement sixthItemCheckbox = driver.findElement(By.xpath("//li[" + listItemsCount + "]//input[@type='checkbox']"));
        sixthItemCheckbox.click();
        new_item--;
        remainingText = driver.findElement(By.xpath("//span[contains(text(), '0 of " + listItemsCount + "')]"));
        Assert.assertTrue("Текст '" + new_item + " of " + listItemsCount + " remaining' не найден на странице", remainingText.isDisplayed());
        spanClassAttributeValue = driver.findElement(By.xpath("//li[" + listItemsCount + "]/span")).getAttribute("class");
        Assert.assertTrue("Шестой элемент списка не становится зачеркнутым", spanClassAttributeValue.contains("done-true"));

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @After
    public void after(){
        //driver.quit();
    }
}

