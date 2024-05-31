package task4;

import managers.DriverManager;
import managers.InitManager;
import managers.TestPropManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Cookie;
import task4.pages.PageManager;

import java.util.Date;

public class BaseTests {
    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    private final TestPropManager propManager = TestPropManager.getInstance();

    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }


    @Before
    public void before(){
        driverManager.getDriver().get("https://www.wildberries.ru/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
