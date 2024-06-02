package task5;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import task5.pages.StartPage;

public class Task5Test extends BaseTests{
    @Test
    @DisplayName("Проверка, что результат при нажатии на кнопку отправки образца запроса совпадает с результатом отправки того же запроса через API")
    public void test() {
        StartPage startPage = new StartPage();
        startPage.checkOpenPage()
                .clickOnButtonAndCheckAPI("List users", "get")
                .clickOnButtonAndCheckAPI("Single user", "get")
                .clickOnButtonAndCheckAPI("Single user not found", "get")
                .clickOnButtonAndCheckAPI("List <resource>", "get")
                .clickOnButtonAndCheckAPI("Single <resource>", "get")
                .clickOnButtonAndCheckAPI("Single <resource> not found", "get")
                .clickOnButtonAndCheckAPI("Create", "post")
                .clickOnButtonAndCheckAPI("Update", "put")
                .clickOnButtonAndCheckAPI("Update", "patch")
                .clickOnButtonAndCheckAPI("Delete", "delete")
                .clickOnButtonAndCheckAPI("Register - successful", "post")
                .clickOnButtonAndCheckAPI("Register - unsuccessful", "post")
                .clickOnButtonAndCheckAPI("Login - successful", "post")
                .clickOnButtonAndCheckAPI("Login - unsuccessful", "post")
                .clickOnButtonAndCheckAPI("Delayed response", "get");
    }
}
