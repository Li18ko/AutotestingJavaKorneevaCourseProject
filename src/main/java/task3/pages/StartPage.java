package task3.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage{
    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//meta[@property='og:url']")
    private WebElement urlPage;

    @FindBy(xpath = "//span[text()='Каталог']")
    private WebElement catalog;

    @FindBy(xpath = "//div[@role='dialog']")
    private WebElement catalogOpen;

    @FindBy(xpath = "//li[@data-zone-name='category-link']")
    private List<WebElement> catalogList;

    @FindBy(xpath = "//ul[@data-autotest-id]//li//a")
    private List<WebElement> categoryItem;

    @Step(value = "Проверяем открылась ли главная страница")
    public StartPage checkOpenMainPage() {
        String contentValue = urlPage.getAttribute("content");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Главная страница не открылась", contentValue.contains("https://market.yandex.ru/"));
        logger.info("Главная страница открылась");
        return pageManager.getStartPage();
    }

    @Step(value = "Проверяем открылся ли раздел 'Игровые телефоны'")
    public StartPage checkOpenTabletsSection(String category) {
        waitUntilElementToBeVisible(catalog).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Каталог не раскрылся", catalogOpen.isDisplayed());
        logger.info("Открыли каталог");
        for (WebElement element: catalogList) {
            waitUntilElementToBeVisible(element);
            moveToElement(element);
            if (element.findElement(By.xpath(".//a/span")).getText().contains(category)) {
                logger.info("Навести на категорию '" + category + "'");
                break;
            }
        }
        return pageManager.getStartPage();
    }

    @Step(value = "Открываем подкатегорию")
    public PhonePage openPhonePage(String subcategory) {
        for (WebElement webElement : categoryItem) {
            if (webElement.getText().contains(subcategory)){
                moveToElement(webElement);
                scrollToElementJs(webElement);
                clickJS(webElement);
                logger.info("Нажать на подкатегорию '" + subcategory + "'");
                break;
            }
        }
        return pageManager.getPhonePage().checkOpenPhonePage("Игровые телефоны");
    }

}
