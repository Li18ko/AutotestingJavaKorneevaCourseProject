package task1;

import org.junit.After;
import org.junit.Before;
import task1.managers.DriverManager;
import task1.managers.PageManager;

public class BaseTests {
    private final DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();

    @Before
    public void before(){
        driverManager.getDriver().get("https://lambdatest.github.io/sample-todo-app/");
    }

    @After
    public void after(){
        driverManager.getDriver().quit();
    }
}
