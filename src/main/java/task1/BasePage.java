package task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import task1.managers.DriverManager;
import task1.managers.PageManager;

public class BasePage {
    protected final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

}
