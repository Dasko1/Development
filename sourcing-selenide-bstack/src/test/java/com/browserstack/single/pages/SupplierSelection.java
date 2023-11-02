package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class SupplierSelection {

    @Test
    public void supplierSelectionTest(){

        // Click Supplier tab and select Supplier, then push the Proceed button followed by the Send button.
        $(By.id("vendor_spec")).shouldBe(Condition.visible).click();
        Selenide.sleep(2000);
        $(By.xpath("//*[@id=\"6433\"]/td[1]")).click();                                           // Click DEV FTP TEST SUPPLIER
        Selenide.sleep(1500);
        $(By.xpath("/html/body/div[1]/div[3]/div/div[1]/div[2]/a[1]")).shouldBe(Condition.visible).click();     // Push Proceed button
        Selenide.sleep(1500);
        $(By.id("confirm-send-rfq")).shouldBe(Condition.visible).click();                         // Push Send button
    }
}
