package task1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import task1.managers.DriverManager;
import task1.managers.InitManager;
import task1.managers.PageManager;
import task1.managers.TestPropManager;
import task1.utils.PropsConst;

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
        driverManager.getDriver().get(propManager.getProperty(PropsConst.BASE_URL));
    }

    @AfterClass
    public static void after(){
        InitManager.quitFramework();
    }
}
