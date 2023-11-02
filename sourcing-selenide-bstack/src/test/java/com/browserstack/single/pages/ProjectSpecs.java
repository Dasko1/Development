package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class ProjectSpecs {

    @Test
    public void projectSpecsTest(){

        // Click Product Specifications tab and fill in fields!
        $(By.id("prod_spec")).shouldBe(Condition.visible).click();                                          // Click Product Specifications tab.
        $(By.id("addNewItemButton")).shouldBe(Condition.visible);                                           // Assert Add New Item to RFQ button.
        $(By.id("itemdescinput")).shouldBe(Condition.visible).sendKeys("Cool Envelopes!");     // Item Description field

        // Enter Envelopes in the "Select a Product" field.
        $("#searchinput").shouldBe(Condition.visible).setValue("Envelope");
        Selenide.sleep(1500);
        $(".td-odd").pressEnter();
        Selenide.sleep(3500);

        // Enter specifications
        $(By.id("spec_2_label")).click();                                                                   // Page Envelope Size
        $(By.id("searchinput")).setValue("#9").pressEnter();
        Selenide.sleep(4500);
        // $(By.id("spec_3_label")).click();                                                                   // Page Seal Type
        $(By.id("searchinput")).setValue("Gum Seal").pressEnter();
        Selenide.sleep(5500);
        // $(By.id("spec_4_label")).click();                                                                // Page Envelope Type
        $(By.id("searchinput")).setValue("Side Seam").pressEnter();
        Selenide.sleep(5500);
        $(By.id("spec_9_label")).click();
        $(By.id("searchinput")).setValue("Digital").pressEnter();                                           // Printing Method
        Selenide.sleep(5500);
        // $("#quantityBox > div:nth-child(1) > a:nth-child(1) > div:nth-child(1)").click();               // Quantity
        $(By.id("searchinput")).setValue("1000").pressEnter();
        Selenide.sleep(3000);
    }
}
