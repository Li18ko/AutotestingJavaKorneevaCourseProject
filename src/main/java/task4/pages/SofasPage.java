package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SofasPage extends BasePage {
    private static final Logger logger = Logger.getLogger(SofasPage.class);

    @FindBy(xpath = "//*[@id=\"catalog\"]/div[2]/h1")
    private WebElement title;

    @FindBy(xpath = "//div[@class='product-card__wrapper']")
    private List<WebElement> sofasList;

    @FindBy(xpath = "/html/body/div[1]/div/div")
    private WebElement filter;

    @FindBy(xpath = "/html/body/div[1]/div/div//h3[@class='filters-desktop__title']")
    private WebElement h3Filter;


    @Step("Проверка перехода на страницу 'Диваны'")
    public SofasPage checkOpenSofasPage(String subcategory) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Заголовок страницы '" + subcategory + "' не найден", title.isDisplayed() &&
                title.getText().contains(subcategory));
        logger.info("Нужный заголовок присутствует на странице");
        return pageManager.getSofasPage();
    }

    @Step("Вывести в лог первые 5 найденных товара (название и цену)")
    public SofasPage checkProducts(int number) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < number; i++){
            WebElement product = sofasList.get(i);
            moveToElement(product);
            String productName = product.findElement(By.xpath(".//h2[@class='product-card__brand-wrap']")).getText();
            String productPrice = product.findElement(By.xpath(".//p[@class='product-card__price price']//ins[@class='price__lower-price wallet-price']")).getText();
            List<WebElement> originalPriceElements = product.findElements(By.xpath(".//p[@class='product-card__price price']//del"));
            String originalPrice = originalPriceElements.isEmpty() ? null : originalPriceElements.get(0).getText();
            int num = number + 1;
            Assert.assertTrue("Товар '" + num + "' не найден", product.isDisplayed());
            if (originalPrice != null) {
                logger.info("Название: " + productName + ". Текущая цена со скидкой: " + productPrice + ". Оригинальная цена: " + originalPrice + ".");
            } else {
                logger.info("Название: " + productName + ". Цена: " + productPrice + ".");
            }
        }
        return pageManager.getSofasPage();
    }

    @Step("Установить цену 'По возрастанию'")
    public SofasPage ascOrderPrice() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickJS(filter);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Контейнер фильтров не раскрылся", h3Filter.isDisplayed());
        logger.info("Раскрыли контейнер фильтров");
        return pageManager.getSofasPage();
    }
}
