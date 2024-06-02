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
                "dD0xNzE3MzIzODEwO2k9OTUuMjQuMzIuMTY7RD0xMzZDQzk1NTdBNURDMEEzRUE1OUE2QUIwMTY5RTJFMkFDNkJFRDA0QTE3MDU5Q0EyMjg5M0U0NkQyRjNEOTY2NzhDQTczMTRFMEZDQzA1RDA1NUU3RENFOUI1MkJDNEY4MEQ1Qzg4NkIwNzU5QjMzRDc4OUIyMjdEMDIzOEQ0OTc4NzNFOEM4MUU2RUI1NDgyMUM4QzI5RTgwNjdBNDU1QTJDNkMyMTkyNzE4NjNCMTt1PTE3MTczMjM4MTA5NTA4NDk3MTg7aD1mYmY1YjEzMThjNTZlNDdkYjI1YzMwNTNjNTViODUxYg==",
                ".yandex.ru", "/", new Date(2024, 7, 2, 10, 23, 30)));
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
