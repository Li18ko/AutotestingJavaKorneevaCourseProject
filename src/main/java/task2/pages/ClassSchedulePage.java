package task2.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ClassSchedulePage extends BasePage {

    private static final Logger logger = Logger.getLogger(ClassSchedulePage.class);

    @FindBy(xpath = "//h4")
    private WebElement title;

    @FindBy(xpath = "//input[@type='text' and @class='groups' and @placeholder='группа ...']")
    private WebElement input;

    @FindBy(xpath = "//div[@class='found-groups row not-print']/div[@id='221-361']")
    private WebElement foundGroup;

    @FindBy(xpath = "//div[@class='found-groups row not-print']/div")
    private List<WebElement> groups;

    @FindBy(xpath = "//*[@id='221-361']")
    private WebElement linkGroup;

    @FindBy(xpath = "//div[contains(@class, 'schedule-day_today')]/div[contains(@class, 'title')]")
    private WebElement dayToday;

    @Step("Проверка перехода на страницу расписание занятий")
    public ClassSchedulePage checkOpenClassSchedulePage() {
        waitUntilElementToBeVisible(title);
        String actualTitleText = title.getText();
        String expectedTitleText = "Расписание занятий";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Заголовок Расписание занятий не найден", title.isDisplayed() &&
                actualTitleText.contains(expectedTitleText));
        logger.info("Нужный заголовок присутствует на странице");
        return pageManager.getClassSchedulePage();
    }

    @Step("Вводим номер группы и проверяем, что в результате поиска только эта группа")
    public ClassSchedulePage inputGroup(String groupNumber) {
        waitUntilElementToBeVisible(input);
        input.clear();
        input.sendKeys(groupNumber);
        logger.info("Ввели номер группы: " + groupNumber);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("В результатах поиска не отображается нужная группа",
                foundGroup.isDisplayed() && foundGroup.getText().equals(groupNumber));
        Assert.assertEquals("В результатах поиска отображается больше одной группы", 1, groups.size());
        logger.info("В результатах поиска только нужная группа");
        return pageManager.getClassSchedulePage();
    }

    @Step("Нажимаем на найденную группу в результатх поиска")
    public ClassSchedulePage scheduleGroup() {
        waitUntilElementToBeVisible(linkGroup).click();
        logger.info("Нажали на ссылку группы");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Не открылось расписание выбранной группы", driverManager.getDriver().getTitle().equals("Расписание 221-361"));
        if (!getCurrentDayOfWeek().equals("Воскресенье")) {
            Assert.assertTrue("Текущий день недели не выделен цветом", dayToday.getText().equals(getCurrentDayOfWeek()));
            logger.info("Раскрыто подробное расписание выбранной группы");
        }else{
            logger.info("Сегодня воскресенье");
        }
        return pageManager.getClassSchedulePage();
    }
}
