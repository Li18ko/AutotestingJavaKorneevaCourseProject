package task2.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import org.apache.log4j.Logger;

public class StartPage extends BasePage {

    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//button[@class='hamburger']")
    private WebElement menuButtom;

    @FindBy(xpath = "//div[@class='main-nav']")
    private WebElement menu;

    @FindBy(xpath = "/html/body/header/nav/div[2]/div/ul[1]/li[2]/a")
    private WebElement obuchauschimsyaLink;

    @FindBy(xpath = "//a[@href='/obuchauschimsya/raspisaniya/' and @class='main-nav__item-link' and @title='Расписания']")
    private WebElement scheduleLink;

    @Step(value = "Нажимаем на гамбургер (меню)")
    public StartPage menuHamburgerClick() {
        menuButtom.click();
        Assert.assertTrue("Меню не открылось", menu.isDisplayed());
        logger.info("Открыли меню");
        return pageManager.getStartPage();
    }

    @Step("Наводим курсор на текст 'Обучающимся'")
    public StartPage hoverOnObuchauschimsyaLink() {
        waitUntilElementToBeVisible(obuchauschimsyaLink);
        action.moveToElement(obuchauschimsyaLink).perform();
        boolean beforeElementPresent = isBeforeElementPresent(scheduleLink);
        Assert.assertTrue("Псевдоэлемент ::before не применен к 'Расписание'", beforeElementPresent);
        logger.info("Навели курсор на текст 'Обучающимся'");
        return pageManager.getStartPage();
    }

    @Step("Нажимаем на ссылку 'Расписание'")
    public SchedulePage clickOnScheduleLink() {
        waitUntilElementToBeClickable(scheduleLink).click();
        logger.info("Нажали на ссылку 'Расписание'");
        return pageManager.getSchedulePage().checkOpenShedulePage();
    }


}