package task4;

import org.junit.Test;
import task4.BaseTests;

public class Task4Test extends BaseTests {
    @Test
    public void test(){
        pageManager.getStartPage().checkOpenMainPage()
                .openMenu()
                .openSection("Мебель")
                .goSofasPage();
        pageManager.getSofasPage().checkOpenSofasPage("Диваны")
                .checkProducts(5)
                .ascOrderPrice();

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
