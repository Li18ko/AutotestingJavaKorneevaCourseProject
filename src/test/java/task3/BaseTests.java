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
                "dD0xNzE4MDMzOTE3O2k9OTUuMjQuMzIuMTY7RD05RkQ5NzVEMEQxNjdBNTI2NDJBQjkyNTI0NDMxMzkwQTA0NzY5OEY5QTE4MTlEMkEzNjZCMUYwOURFNEVBMjdCMEU3NDlEMzhDRTVCREI3NjI2MTZERjIxNDQzMzQ3MzM1MjA1MTI0MzNCRjQ0NTEyM0I1RUQwQ0M2QjZBRjQyNTNFQ0ZBOTg4OTdFQUVCMDkxQzQxODVENDhFNkJCODRFMDhDRkI2RjhEQjcyMjZEODt1PTE3MTgwMzM5MTczNTk2NzA4MjI7aD02MmJlYzA4YmE3YjM3OTNiZDlkNDgzMDg1MDE3OWI5NQ==",
                ".yandex.ru", "/", new Date(2024, 7, 10, 15, 38, 37)));
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
