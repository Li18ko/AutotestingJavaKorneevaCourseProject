package task4.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SofasPage extends BasePage {
    private static final Logger logger = Logger.getLogger(SofasPage.class);

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@class='product-card__wrapper']")
    private List<WebElement> sofasList;

    @FindBy(xpath = "//button[contains(@class, 'dropdown-filter__btn dropdown-filter__btn--sorter')]")
    private WebElement filterSort;

    @FindBy(xpath = "//div[@class='filter' and contains(@data-link, 'updateSort')]")
    private WebElement filterSortContainer;

    @FindBy(xpath = "//li[contains(@class, 'filter__item j-catalog-sort') and .//span[text()='По возрастанию цены']]")
    private WebElement filterSortAscPrice;

    @FindBy(xpath = "//div[@class='dropdown-filter']//button[@class='dropdown-filter__btn dropdown-filter__btn--sorter']")
    private WebElement filterSortAfter;



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
        filterSort.click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue("Контейнер сортировки не раскрылся", filterSortContainer.isDisplayed());
        logger.info("Раскрыли контейнер сортировки");

        filterSortAscPrice.click();
        Assert.assertTrue("Сортировка цены не выбрана 'По возрастанию цены'", filterSortAfter.getText().contains("По возрастанию цены"));
        logger.info("Сортировка товаров по возрастанию цены установлена");

        return pageManager.getSofasPage();
    }

    public SofasPage checkPriceProducts(int number) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Integer> prices = new ArrayList<>();

        for(int i = 0; i < number; i++){
            WebElement product = sofasList.get(i);
            moveToElement(product);

            String productName = product.findElement(By.xpath(".//h2[@class='product-card__brand-wrap']")).getText();
            String productPriceStr = product.findElement(By.xpath(".//p[@class='product-card__price price']//ins[@class='price__lower-price wallet-price']")).getText();
            productPriceStr = productPriceStr.replaceAll("[^0-9]", "");
            int productPrice = Integer.parseInt(productPriceStr);

            prices.add(productPrice);
            Assert.assertTrue("Товар '" + (i + 1) + "' не найден", product.isDisplayed());
            logger.info("Название: " + productName + ". Цена: " + productPrice + ".");
        }

        List<Integer> sortedPrices = prices.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals("Цены не отсортированы по возрастанию", sortedPrices, prices);
        logger.info("Цены отсортированы по возрастанию, сортировка работает");

        return pageManager.getSofasPage();
    }
}
