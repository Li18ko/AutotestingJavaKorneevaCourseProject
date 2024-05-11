package task1;

import org.junit.Test;

public class Task1 extends BaseTests {

    @Test
    public void test(){
        pageManager.getStartPage().isPageTitleDisplayed()
                .isRemainingTextDisplayed()
                .checkTheListItemAndClick("li1")
                .checkTheListItemAndClick("li2")
                .checkTheListItemAndClick("li3")
                .checkTheListItemAndClick("li4")
                .checkTheListItemAndClick("li5")
                .addItemToList("Новый элемент списка")
                .clickButton()
                .checkTheListItemAndClick("li6");

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

