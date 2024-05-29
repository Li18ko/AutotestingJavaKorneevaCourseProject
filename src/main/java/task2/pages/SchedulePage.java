package task2.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import org.apache.log4j.Logger;

public class SchedulePage extends BasePage {
    private static final Logger logger = Logger.getLogger(SchedulePage.class);

    @FindBy(xpath = "//*[@id='bx_2015423015_2810']/div[2]/div/h1")
    private WebElement title;

    @FindBy(xpath = "//*[@id='bx_3777608605_2811']/div[3]/div/div[1]/a")
    private WebElement viewSchedule;

    @FindBy(xpath = "//*[@id='bx_3777608605_2811']/div[1]")
    private WebElement h1Schedule;

    @Step("Проверка перехода на страницу расписания")
    public SchedulePage checkOpenShedulePage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Заголовок Расписания не найден", title.isDisplayed() &&
            title.getText().contains("Расписания"));
        logger.info("Нужный заголовок присутствует на странице");
        return pageManager.getSchedulePage();
    }

    @Step("Кликаем на ссылку 'Смотреть на сайте'")
    public ClassSchedulePage viewSchedule() {
        scrollToElementJs(h1Schedule);
        waitUntilElementToBeVisible(viewSchedule).click();
        moveToNewTab();
        logger.info("Кликнули на ссылку 'Смотреть на сайте'");
        return pageManager.getClassSchedulePage().checkOpenClassSchedulePage();
    }

}
