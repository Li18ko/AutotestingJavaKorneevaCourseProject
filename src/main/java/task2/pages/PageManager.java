package task2.pages;

public class PageManager {
    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private SchedulePage schedulePage;

    private ClassSchedulePage classSchedulePage;

    private PageManager(){

    }

    public static PageManager getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public StartPage getStartPage(){
        if (startPage== null){
            startPage = new StartPage();
        }
        return startPage;
    }

    public SchedulePage getSchedulePage(){
        if (schedulePage== null){
            schedulePage = new SchedulePage();
        }
        return schedulePage;
    }

    public ClassSchedulePage getClassSchedulePage(){
        if (classSchedulePage== null){
            classSchedulePage = new ClassSchedulePage();
        }
        return classSchedulePage;
    }
}
