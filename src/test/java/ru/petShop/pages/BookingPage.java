package ru.petShop.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$x;

public class BookingPage {
    private final SelenideElement setCount = $x("//input[@name='count']");
    private final SelenideElement setName = $x("//input[@name='fio']");
    private final SelenideElement setEmail = $x("//input[@name='email']");

    private final SelenideElement sendBtn = $x("//input[@type='submit']");

    private final SelenideElement setCode = $x("//input[@name='code']");
    private final SelenideElement checkBtn = $x("//input[@type='submit']");
    private int initCount;

    public int fillFields(int initCount) {
        Random random = new Random();
        this.initCount = initCount;

        int count = setGetCount(random);
        StringBuilder name = setGetName(random);
        StringBuilder email = setGetEmail(random);

        Selenide.sleep(5000);

        sendBtn.click();

        checkFields(count, email, name);

        Selenide.sleep(5000);

        booking();

        return count;
    }

    private void booking() {
        if (setCode.isDisplayed()) {
            // ожидание для проверки почты и ввод кода
            Selenide.sleep(70000);

            checkBtn.click();
            Selenide.sleep(9000);
        } else fillFields(initCount);
    }

    private void checkFields(int count, StringBuilder email, StringBuilder name) {

        StringBuilder tmp = new StringBuilder();
        for (int i = email.length()-10; i < email.length(); i++) {
            tmp.append(i);
        }

        if (count < initCount && tmp.equals("@gmail.com") && !name.isEmpty()) Assert.assertTrue(setCode.isDisplayed());
        else Assert.assertFalse(setCode.isDisplayed());
    }

    private int setGetCount(Random random) {
        int count = random.nextInt(100);
        setCount.setValue(String.valueOf(count));

        return count;
    }

    private StringBuilder setGetName(Random random) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 10; i++) name.append(alphabet.charAt(random.nextInt(alphabet.length())));
        setName.setValue(name.toString());
        return name;
    }

    private StringBuilder setGetEmail(Random random) {
        String alphaNumb = "123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < 20; i++)  email.append(alphaNumb.charAt(random.nextInt(alphaNumb.length())));
        email.append("@gmail.com");
        setEmail.setValue(email.toString());
        return email;
    }
}
