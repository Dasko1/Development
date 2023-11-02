package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SupplierAction {

    public void supplierActionTest(){

        // Go to the Supplier site & log in; allow time for Dashboard to load all elements.
        Selenide.open("https://supplier.dba.development.dbenterprise.com");
        $(By.name("username")).shouldBe(Condition.visible).setValue("devftptest");
        $(By.name("password")).shouldBe(Condition.visible).setValue("test");
        $(".btn").click();
        Selenide.sleep(9000);

        // Click on topmost element in the Dashboard list & fill in fields; return to RFQ Dashboard.
        $(By.xpath("//*[@id=\"ext-gen41\"]/div[1]/table/tbody/tr/td[4]/div")).shouldBe(Condition.visible).doubleClick();
        $(By.className("supplier-quote-number")).shouldBe(Condition.visible).sendKeys("1115");
        $(By.id("newQty1")).sendKeys("1000");
        $(By.id("newPrice1")).sendKeys("500");
        $(By.id("ext-gen102")).shouldBe(Condition.visible).click();
        $(By.id("newChargeDescription1")).sendKeys("RFQ Description");
        $(By.id("newChargeAmount1")).sendKeys("250");
        Selenide.sleep(1500);
        $(By.xpath("//*[@id=\"submitBidButton_1\"]/a")).shouldBe(Condition.visible).click();
        // Push the modal Yes button, of course.
        Selenide.sleep(2000);
        $(By.id("ext-comp-1062")).shouldBe(Condition.visible).click();
        Selenide.sleep(2000);

        open("https://dbdemo.dba.development.dbenterprise.com/");                   // Return to RFQ Dashboard

    }
}
