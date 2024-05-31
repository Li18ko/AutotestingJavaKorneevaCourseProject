package task3.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PhonePage extends BasePage{
    private static final Logger logger = Logger.getLogger(PhonePage.class);

    @FindBy(xpath = "//*[@id='/content/page/fancyPage/cms/1/SearchTitleWithBreadcrumbs-SearchTitleWithBreadcrumbs']/div/div/div/h1")
    private WebElement title;

    @FindBy(xpath = "//div[@data-auto-themename='listDetailed']")
    private List<WebElement> phoneList;
    @FindBy(xpath = "//h4[@class='_1JK_x _6tyDq _1ea6I']")
    private WebElement titleManufacturer;

    @FindBy(xpath = "//div[@data-filter-value-id='153061']//label")
    private WebElement specificManufacturer;


    @Step("Проверка перехода на страницу 'Игровые телефоны'")
    public PhonePage checkOpenPhonePage(String subcategory) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Заголовок страницы '" + subcategory + "' не найден", title.isDisplayed() &&
                title.getText().contains(subcategory));
        logger.info("Нужный заголовок присутствует на странице");
        return pageManager.getPhonePage();
    }

    @Step("Вывести в лог первые 5 найденных товара (название и цену)")
    public PhonePage checkProducts(int number) {
        for(int i = 0; i < number; i++){
            WebElement product = phoneList.get(i);
            moveToElement(product);
            String productName = product.findElement(By.xpath(".//h3")).getText();
            String productPrice = product.findElement(By.xpath(".//span[@data-auto='snippet-price-current']/span[1]")).getText();
            List<WebElement> discountElements = product.findElements(By.xpath(".//div[@data-auto='discount-badge']"));
            boolean isDiscount = !discountElements.isEmpty();
            logger.info("Название: " + productName + ". Цена: " + productPrice + (isDiscount ? " с учетом скидки" : "."));
            int num = number + 1;
            Assert.assertTrue("Товар '" + num + "' не найден", product.isDisplayed());
        }
        return pageManager.getPhonePage();
    }

    @Step("Выбрать в фильтрах производителя")
    public PhonePage changeManufacturer() {
        scrollToElementJs(titleManufacturer);
        waitUntilElementToBeVisible(specificManufacturer).click();
        moveToElement(specificManufacturer);
        logger.info("Производитель в фильтрах выбран");
        for(int i = 0; i < 7 && i < phoneList.size(); i++){
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WebElement product = phoneList.get(i);
            moveToElement(product);
            String productName = product.findElement(By.xpath(".//h3")).getText();
            boolean allContainSamsung = false;
            if (!productName.contains("Samsung")) {
                allContainSamsung = false;
            } else {
                allContainSamsung = true;
            }
            Assert.assertTrue("Название товара не содержит 'Samsung': " + productName, allContainSamsung);
            logger.info("Название товара содержит 'Samsung'");
        }
        return pageManager.getPhonePage();
    }


}
