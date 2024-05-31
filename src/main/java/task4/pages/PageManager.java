package task4.pages;

import task4.pages.StartPage;

public class PageManager {
    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private SofasPage sofasPage;

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

    public SofasPage getSofasPage(){
        if (sofasPage== null){
            sofasPage = new SofasPage();
        }
        return sofasPage;
    }
}
