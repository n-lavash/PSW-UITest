package ru.petShop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class FoundProductsPage {
    private final ElementsCollection productName = $$x("//span[@class='products-element__name']");

    public void checkName(String enter) {
        List<SelenideElement> products = getProductName();
        boolean check = false;

        Selenide.sleep(5000);

        for (SelenideElement name:
                products) {
            if (name.text().contains(enter)) {
                check = true;
            } else {
                check = false;
                break;
            }
        }

        Selenide.sleep(5000);

        Assert.assertTrue(check);
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
}
