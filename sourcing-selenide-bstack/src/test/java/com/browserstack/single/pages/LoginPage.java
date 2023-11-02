package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement username = $(By.name("username"));
    private final SelenideElement password = $(By.name("password")); // $("input[name='password']")
    private final SelenideElement loginButton = $(".btn");

    public LoginPage to() {
        Selenide.open("/");
        return this;
    }

    public void login(String username, String password) {
        this.username.shouldBe(Condition.visible).setValue(String.valueOf(username));
        this.password.setValue(String.valueOf(password));
        this.loginButton.click();

        // Confirm logged in by checking if the Create RFQ button is visible.
        $(By.id("Create RFQ")).shouldBe(Condition.visible);
    }
}
