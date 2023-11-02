package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Award {

    private static final SelenideElement assertText = $("#proposalCustomerFeedback_1 > div > div.customer-feedback-label");
    private static final SelenideElement awardWindow = $(By.id("awardWindow"));
    private static final SelenideElement dropdown = $(By.id("basisType"));


    public static void findRFQToAward(){
        Configuration.browserSize = "1920x1080";
        open("https://dbdemo.dba.development.dbenterprise.com/");                       // Return to RFQ Dashboard
        LatestRFQ.clickLatestRFQ();
        assertText.shouldBe(Condition.visible);                                                         // Assert text "Customer Notes:"
    }

    public static void pushAwardButton(){
        $(By.className("awardButton")).scrollIntoView(true).click();
        awardWindow.shouldBe(Condition.exist);
        dropdown.shouldBe(Condition.visible).click();
        $(".x-combo-list-inner .x-combo-list-item").click();                                 // Select top choice from dropdown
        $(By.id("ext-comp-1099")).click();                                                            // Push "Yes" button
    }
}
