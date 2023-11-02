package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LatestRFQ {

    public static void clickLatestRFQ(){

        Selenide.sleep(7500);
        $(By.xpath("//*[@id=\"ext-gen16\"]/div[1]/table/tbody/tr/td[6]/div")).shouldBe(Condition.visible).doubleClick();
        Selenide.sleep(2500);
        $("#customer_profile > div.job-info-title.section-header").shouldHave(Condition.text("General Project Information"));
    }
}
