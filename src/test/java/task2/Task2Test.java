package task2;

import org.junit.Test;

public class Task2Test extends BaseTests{

    @Test
    public void test(){
        pageManager.getStartPage().menuHamburgerClick()
                .hoverOnObuchauschimsyaLink()
                .clickOnScheduleLink();
        pageManager.getSchedulePage().checkOpenShedulePage()
                .viewSchedule();
        pageManager.getClassSchedulePage().checkOpenClassSchedulePage()
                .inputGroup("221-361")
                .scheduleGroup();

        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
