package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class StartPage {
    public WebDriver driver;

    public StartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//h2[contains(text(), 'LambdaTest Sample App')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//span[contains(text(), '5 of 5 remaining')]")
    private WebElement remainingText;

    @FindBy(xpath = "//li")
    private List<WebElement> listItems;

    @FindBy(xpath = "//input[@placeholder='Want to add more']")
    private WebElement inputField;

    @FindBy(xpath = "//input[@value='add']")
    private WebElement addButton;


    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed() && pageTitle.getText().contains("LambdaTest Sample App");
    }

    public boolean isRemainingTextDisplayed() {
        return remainingText.isDisplayed() && remainingText.getText().contains("5 of 5 remaining");
    }

    public int getListItemsCount() {
        return listItems.size();
    }

    public void addItemToList(String text) {
        inputField.sendKeys(text);
    }

    public void clickButton() {
        addButton.click();
    }

}
