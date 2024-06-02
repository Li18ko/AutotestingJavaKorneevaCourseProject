package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage{
    private static final Logger logger = Logger.getLogger(StartPage.class);

    @FindBy(xpath = "//div[@class='cookies']")
    private WebElement cookies;

    @FindBy(xpath = "//meta[@property='og:url']")
    private WebElement urlPage;

    @FindBy(xpath = "/html/body/div[1]/header/div/div[2]/div[1]/button")
    private WebElement burger;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[2]")
    private WebElement menu;

    @FindBy(xpath = "//ul[@class='menu-burger__main-list']//a")
    private List<WebElement> catalogList;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[3]/div/div[12]/div/div[1]/div/a")
    private WebElement h1Section;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[3]/div/div[12]/div/div[1]/ul/li[3]/span")
    private WebElement sofasAndArmchairs;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[3]/div/div[12]/div/div[2]/div[2]/div/a")
    private WebElement h1SubSection;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div[3]/div/div[11]/div/div[2]/div[2]/ul/li[1]/a")

    private WebElement sofas;

    @Step(value = "Проверяем открылась ли главная страница")
    public StartPage checkOpenMainPage() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String contentValue = urlPage.getAttribute("content");
        Assert.assertTrue("Главная страница не открылась", contentValue.contains("https://www.wildberries.ru"));
        logger.info("Главная страница открылась");
        if (cookies.isDisplayed()){
            WebElement button = cookies.findElement(By.xpath(".//button"));
            button.click();
            logger.info("Скрылось уведомление cookies");
        }
        return pageManager.getStartPage();
    }


    @Step(value = "Открываем меню")
    public StartPage openMenu() {
        burger.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Меню не открылось", menu.isDisplayed());
        logger.info("Открылось меню");
        return pageManager.getStartPage();
    }

    @Step(value = "Навести в меню на раздел 'Мебель', подраздел 'Диваны и кресла'")
    public StartPage openSection(String category) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (WebElement element: catalogList) {
            waitUntilElementToBeVisible(element);
            moveToElement(element);
            if (element.getText().contains(category)) {
                Assert.assertTrue("Раздел '" + category + "' не раскрылся", h1Section.isDisplayed() && h1Section.getText().contains(category));
                logger.info("Навести на категорию '" + category + "'");
                moveToElement(h1Section);
                clickJS(sofasAndArmchairs);
                Assert.assertTrue("Подраздел 'Диваны и кресла' не раскрылся", h1SubSection.isDisplayed() && h1SubSection.getText().contains("Диваны и кресла"));
                logger.info("Навести на подкатегорию 'Диваны и кресла'");
                moveToElement(h1SubSection);
                clickJS(sofas);
                break;
            }
        }
        return pageManager.getStartPage();
    }

    @Step(value = "Перейти на страницу 'Диваны'")
    public SofasPage goSofasPage() {
        clickJS(sofas);
        logger.info("Нажали на ссылку для перехода на страницу 'Диваны'");
        return pageManager.getSofasPage().checkOpenSofasPage("Диваны");
    }
}
