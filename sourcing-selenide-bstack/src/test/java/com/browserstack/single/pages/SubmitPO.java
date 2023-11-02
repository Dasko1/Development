package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SubmitPO {

    private static final SelenideElement preparePOButton = $(By.id("preparePO"));
    private static final SelenideElement datePicker = $(By.name("inHandsDate"));
    private static final SelenideElement customerPOCode = $(By.id("customer_po_number"));
    private static final SelenideElement itemCodeIcon = $("#poProductTab > div > div:nth-child(4) > a");
    private static final SelenideElement itemSearchBox = $(By.name("itemSearchBox"));
    private static final SelenideElement shippingTab = $(By.id("poShippingTabLabel"));
    private static final SelenideElement warehouseLocationSearchField = $(By.name("warehouseLocationSearchBox"));
    private static final SelenideElement submitPOButton = $(By.xpath("//*[@id=\"main-wrapper-class-142\"]/div[1]/div[2]/div[1]/div/div[1]/div/a[2]"));
    private static final SelenideElement shippingWindow = $(By.id("shippingWindow"));

    public void findRFQToSubmitPO() {
        Selenide.sleep(3000);
        open("https://dbdemo.dba.development.dbenterprise.com/dbvendor/job/DistributorHome.dbv");                       // Return to RFQ Dashboard
        $(By.xpath("//*[@id=\"ext-gen16\"]/div[1]/table/tbody/tr/td[6]/div")).shouldBe(visible).doubleClick();
    }

    public void dateAndPOCode() {
        preparePOButton.click();
        datePicker.sendKeys("1/6/21");
        datePicker.sendKeys(Keys.RETURN);
        customerPOCode.sendKeys("9385798347");
    }

    public void productInfoTab() {
        itemCodeIcon.scrollIntoView(true).click();
        itemSearchBox.sendKeys("AN-CHEQ-02");
        Selenide.sleep(4500);
        $(".x-window-plain .x-grid3-row-first").shouldBe(visible).doubleClick();
        Selenide.sleep(3500);
    }

    public void setShippingTab(){
        shippingTab.click();                                                                // This opens the Shipping Location modal.
        $(".x-window .po-product-code-button").click();                           // Click little pencil icon.
        warehouseLocationSearchField.sendKeys("wa");                           // Put "wa" in the Search field.
        $(".x-window-plain .x-grid3-row-first").doubleClick();                    // Click the second, lower option.
        Selenide.sleep(2500);
        $$("#shippingWindow .x-btn-text").filter(Condition.text("Save")).first().shouldBe(Condition.exist).click();
        Selenide.sleep(2500);
        // Push the Save button in the Shipping Location modal.
        submitPOButton.scrollIntoView(true).click();                                       // Push the Submit PO button.
    }
}
