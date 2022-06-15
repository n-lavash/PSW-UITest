package ru.petShop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.As;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement searchBar = $x("//input[@class='searchtext']");
    private final SelenideElement searchBtn = $x("//input[@class='search-button']");
    private final ElementsCollection productName = $$x("//span[@class='products-element__name']");

    private final ElementsCollection productElements = $$x("//button[@class='products-element__btn']");

    public MainPage(String URL) {
        Selenide.open(URL);
    }


    public void clickOnSearch(String enter) {
        searchBar.setValue(enter);
        searchBtn.click();

        FoundProductsPage foundProductsPage = new FoundProductsPage();
        foundProductsPage.checkName(enter);
    }

    public void clickOnDescription() {
        List<SelenideElement> productsName = getProductName();
        List<SelenideElement> productsDesc  = getProductDesc();

        Random random = new Random();
        int number = random.nextInt(productsDesc.size());

        String name = productsName.get(number).text();
        productsDesc.get(number).click();
        Selenide.sleep(5000);
        DescriptionPage descriptionPage = new DescriptionPage();
        descriptionPage.checkName(name);

    }

    public void bookingProduct() {
        List<SelenideElement> productsBook = getProductBook();
        List<SelenideElement> productsName = getProductName();

        Random random = new Random();
        int number = random.nextInt(productsBook.size());

        String name = productsName.get(number).text();

        List<String> counts = getProductCount();
        int initCount = Integer.parseInt(counts.get(number));

        productsBook.get(number).click();

        BookingPage bookingPage = new BookingPage();
        int newCount = bookingPage.fillFields(initCount);

        List<String> newProductsCount = getProductCount();
        if (newProductsCount.get(number).equals(String.valueOf(initCount-newCount))) {
            Assert.assertEquals(newProductsCount.get(number), String.valueOf(initCount-newCount));
        } else {
            Assert.assertNotEquals(newProductsCount.get(number), String.valueOf(initCount-newCount));
        }
    }

    private List<SelenideElement> getProductDesc() {
        List<SelenideElement> products = new ArrayList<>();
        for (SelenideElement desc:
                productElements) {
            if (desc.text().contains("описание"))
                products.add(desc);
        }

        return products;
    }

    private List<SelenideElement> getProductName() {
        List<SelenideElement> products = new ArrayList<>();
        for (SelenideElement name:
                productName) {
            if (!name.text().contains("в наличии")) {
                products.add(name);
            }
        }
        return products;
    }

    private List<SelenideElement> getProductBook() {
        List<SelenideElement> products = new ArrayList<>();
        for (SelenideElement desc:
                productElements) {
            if (desc.text().contains("забронировать"))
                products.add(desc);
        }

        return products;
    }

    private List<String> getProductCount() {
        List<String> products = new ArrayList<>();
        for (SelenideElement name:
                productName) {
            if (name.text().contains("в наличии")) {
                String[] tmp = name.text().split(" ");
                products.add(tmp[0]);
            }
        }
        return products;
    }
}
