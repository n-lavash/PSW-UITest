package ru.petShop;

import com.codeborne.selenide.Selenide;
import org.junit.Test;
import ru.petShop.pages.MainPage;

public class TestCase {
    private final String URL = "file:///C:/PetShop/version0806/index.html";

    @Test
    public void search() {
        MainPage mainPage  = new MainPage(URL);
        Selenide.sleep(5000);
        mainPage.clickOnSearch("Корм");
    }

    @Test
    public void openDescription() {
        MainPage mainPage  = new MainPage(URL);
        Selenide.sleep(5000);
        mainPage.clickOnDescription();
    }

    @Test
    public void booking() {
        MainPage mainPage = new MainPage(URL);
        Selenide.sleep(5000);
        mainPage.bookingProduct();
    }
}
