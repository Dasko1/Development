package com.browserstack.single.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Link;
import com.mailosaur.models.Message;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RespondToProposalMailosaur {

    @Test
    public static void RespondToProposalMailosaurTest() throws MailosaurException, IOException {

        MailosaurEmailClient emailClient = new MailosaurEmailClient();
        Message latestProposal = emailClient.getEmailBySubject("New Proposal - ");
        Link proposal = latestProposal.html().links().stream()
                .filter(link -> link.text().equalsIgnoreCase("CLICK HERE TO RESPOND TO THIS PROPOSAL")).findAny().orElseThrow(() -> new RuntimeException("Proposal Not Found"));
        open(proposal.href());

        // Accept the proposal & push the Yes button
        $(".x-panel-bwrap .x-grid3-row-first").shouldBe(Condition.visible).click();
        Selenide.sleep(1500);
        $(By.linkText("Accept Selected Quantity/Price")).shouldBe(Condition.visible).click();
        Selenide.sleep(1500);
        $(By.id("ext-comp-1019")).click();
        // $(By.id("ext-gen94")).shouldBe(Condition.visible).click();

        Selenide.sleep(5000);
    }
}
