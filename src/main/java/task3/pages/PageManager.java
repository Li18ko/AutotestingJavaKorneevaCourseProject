package task3.pages;
import task3.pages.StartPage;

public class PageManager {
    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private PhonePage phonePage;

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

    public PhonePage getPhonePage(){
        if (phonePage == null){
            phonePage = new PhonePage();
        }
        return phonePage;
    }
}
