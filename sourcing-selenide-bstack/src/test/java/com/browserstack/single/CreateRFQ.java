package com.browserstack.single;

import com.browserstack.BrowserStackTest;
import com.browserstack.single.pages.*;
import com.codeborne.selenide.WebDriverRunner;
import com.mailosaur.MailosaurException;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.browserstack.single.pages.RespondToProposalMailosaur.RespondToProposalMailosaurTest;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.title;

public class CreateRFQ extends BrowserStackTest {

    private static final String USERNAME = "adaskalopoulos1";
    private static final String KEY = "RwofYrpjLEBwrwzpTdSc";

    @BeforeEach
    void setup() throws MalformedURLException {
        var capabilities = new DesiredCapabilities(Map.of(
                "browser", "Chrome",
                "os", "Windows",
                "os_version", "10",
                "acceptInsecureCerts", true,
                "acceptSslCert", true,
                "browserstack.networkLogs", true,
                "build", "Selenide Single",
                "name", "Selenide Single Homepage Test"));

        var driver = new RemoteWebDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", USERNAME, KEY)), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterMethod(alwaysRun=true)
    public void tearDown() throws Exception {

        // Setting the status of test as 'passed' or 'failed' based on the condition; if title of the web page contains 'DB Commerce'.
        WebDriverWait wait = new WebDriverWait(driver, 15);
        try {
            wait.until(ExpectedConditions.urlContains("DistributorHome.dbv"));
            // wait.until(ExpectedConditions.titleContains("DemandBridge"));
            markTestStatus("passed", "The URL contains 'DistributorHome.dbv'",driver);
            markTestStatus("passed","Yaay, the test passed'!",driver);
        }
        catch(Exception e) {
            markTestStatus("failed", "The URL does not contain 'DistributorHome.dbv'",driver);
            markTestStatus("failed","The test did not pass'",driver);
        }


        if(title().contains("DemandBridge")){
            executeJavaScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
        }
        else {
            executeJavaScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
        }

        WebDriverRunner.closeWebDriver();
    }

    private void markTestStatus(String passed, String s, RemoteWebDriver driver) {
    }


    @Test
    public void createRFQTest() throws MailosaurException, IOException {
        new LoginPage().to().login("daskoadmin", "dasko");
        new GeneralProjectInformation().generalProjectInformationTest();            // Push Create RFQ button and enter General Project Information.
        new ProjectSpecs().projectSpecsTest();                                      // Enter Product Specifications.
        new SupplierSelection().supplierSelectionTest();                            // Select Supplier & push Proceed & Send buttons.
        new SupplierAction().supplierActionTest();                                  // Supplier Action, or receive a quote.
        SendProposal.clickSelectBid();                                              // Complete Send Proposal process!
        SendProposal.pushPrepButton();
        SendProposal.enterDescriptionAndAmount();
        SendProposal.margins();
        SendProposal.pushSendProposalButton();
        SendProposal.proposalEmail();
        RespondToProposalMailosaurTest();                                           // Mailosaur response.
        Award.findRFQToAward();                                                     // Click top RFQ.
        Award.pushAwardButton();                                                    // Award proposal.
        new SubmitPO().findRFQToSubmitPO();
        new SubmitPO().dateAndPOCode();
        new SubmitPO().productInfoTab();
        new SubmitPO().setShippingTab();
    }
}