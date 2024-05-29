package task3;

import managers.DriverManager;
import managers.InitManager;
import managers.TestPropManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Cookie;
import task3.pages.PageManager;

import java.util.Calendar;
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
        driverManager.getDriver().get("https://market.yandex.ru");
        driverManager.getDriver().manage().addCookie(new Cookie("spravka",
                "dD0xNzE2OTk4Njg2O2k9MTkzLjIzMy4xMDYuMjc7RD00MzVGMDAzOURFNjhGMDNGNkI0NjQ3MjI0RDc5ODhDRjkxQkY1MjJGRjI1MDE0OUYyNUVBM0VFMEIyQjhBMENGRUZGNjVCNjNDMTBEMjZEQTE1RTBDNzRBODVCRDAyQzg0RkNERTE0RTUxOEI3QjFCOTI0RkJBRjcyNDhCNjgyMUQ4NTVCMjkxNjNDQ0M0NTE3QzY0REUxOEE1ODgwNUEwMUU1Mjt1PTE3MTY5OTg2ODY3NDgxMDI0MjY7aD04NDY3NGQ1OTg0Y2ViMmUwMzIwNjg0ZjU0OGEzNjI2OA==",
                ".yandex.ru", "/", new Date(2024, 6, 28, 16, 4, 46)));
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverManager.getDriver().get("https://market.yandex.ru/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
