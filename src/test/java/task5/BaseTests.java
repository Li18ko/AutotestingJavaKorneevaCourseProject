package task5;

import managers.DriverManager;
import managers.InitManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Cookie;

import java.util.Date;


public class BaseTests {
    private static final DriverManager driverManager = DriverManager.getInstance();
    @BeforeClass
    public static void beforeClass(){
        InitManager.initFramework();
    }


    @Before
    public void before(){
        driverManager.getDriver().get("https://reqres.in/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
