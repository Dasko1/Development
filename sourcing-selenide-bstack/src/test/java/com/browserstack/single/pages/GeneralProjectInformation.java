package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class GeneralProjectInformation {

    @Test
    public void generalProjectInformationTest(){

        // Push Create RFQ button & fill in fields.
        $(By.id("Create RFQ")).shouldBe(Condition.visible).click();                             // Push Create RFQ button.
        $(By.id("customerCombo")).setValue("04-01500000");                                      // Enter a Customer
        Selenide.sleep(2000);
        $(By.id("customerCombo")).pressEnter();
        $(By.id("projectName")).sendKeys("Create RFQ Test");                       // Enter the Project name
        $(By.id("projectDesc")).sendKeys("Lorem ipsum dolor sit amet, sed in velit eligendi, ne eos justo nulla lobortis. Sit et aliquam suscipit.");
        Selenide.sleep(4500);
    }
}
