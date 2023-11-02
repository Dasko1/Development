package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class SendProposal{

    private static final SelenideElement select_bid = $(By.xpath("//*[@id=\"ext-gen94-gp-vendorName-DEV FTP TEST SUPPLIER-bd\"]/div/table/tbody/tr/td[3]"));     // Click the yellow-highlighted proposal
    private static final SelenideElement prep_btn = $(By.id("proposeItemButton_1"));                                                                             // Push the Prepare This Item For Proposal button
    private static final SelenideElement send_proposal_btn = $(By.id("prepareProposal"));                                                                        // Push the Send Proposal button
    private static final SelenideElement description = $(By.id("newChargeDescription1"));
    private static final SelenideElement amount = $(By.id("newChargeAmount1"));
    private static final SelenideElement margin_field = $(By.className("marginForAllNumberField"));
    private static final SelenideElement margin_button = $(".setMarginForAllButton .x-btn-text");
    private static final SelenideElement email_first_name = $(By.id("proposalContactFirstName"));
    private static final SelenideElement comments_field = $(By.id("tinymce"));
    private static final SelenideElement send_email_proposal_btn = $(By.id("sendPropsalBtn"));

    public static void clickSelectBid(){
        Configuration.browserSize = "1920x1080";
        LatestRFQ.clickLatestRFQ();
        select_bid.click();
        Selenide.sleep(2500);
    }

    public static void pushPrepButton(){
        prep_btn.shouldBe(Condition.visible).hover();
        prep_btn.shouldBe(Condition.visible).click();
    }

    public static void enterDescriptionAndAmount(){
        description.scrollIntoView(true);
        description.shouldBe(Condition.visible).setValue("Test Description");
        amount.shouldBe(Condition.visible).sendKeys("150");
        Selenide.sleep(1500);
    }

    public static void margins(){
        margin_button.scrollIntoView(true);
        margin_field.shouldBe(Condition.visible).sendKeys("20");
        Selenide.sleep(1500);
        margin_button.shouldBe(Condition.exist).click();
        Selenide.sleep(3000);
    }

    public static void pushSendProposalButton(){
        send_proposal_btn.shouldBe(Condition.visible).click();
    }

    public static void proposalEmail(){
        email_first_name.shouldBe(Condition.exist).sendKeys("Anastasios");
        Selenide.sleep(1000);
        email_first_name.sendKeys(Keys.RETURN);
        Selenide.sleep(2500);
        switchTo().frame("proposalComments_ifr");
        comments_field.isEnabled();
        comments_field.shouldBe(Condition.visible).click();
        comments_field.setValue("This is the Comments!");
        switchTo().window(0);
        send_email_proposal_btn.shouldBe(Condition.visible).click();
        Selenide.sleep(2500);
    }
}
