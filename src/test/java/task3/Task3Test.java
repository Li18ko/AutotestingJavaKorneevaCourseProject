package task3;

import org.junit.Test;

public class Task3Test extends BaseTests{
    @Test
    public void test(){
        pageManager.getStartPage().checkOpenMainPage()
                .checkOpenTabletsSection("Все для гейминга")
                .openPhonePage("Игровые телефоны");

        pageManager.getPhonePage().checkOpenPhonePage("Игровые телефоны")
                .checkProducts(5)
                .changeManufacturer();

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
