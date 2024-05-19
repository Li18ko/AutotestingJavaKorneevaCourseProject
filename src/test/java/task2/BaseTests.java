package task2;

import managers.InitManager;
import task2.pages.PageManager;
import managers.TestPropManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import managers.DriverManager;
import utils.PropsConst;

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
        driverManager.getDriver().get("https://mospolytech.ru/");
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
