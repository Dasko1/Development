package com.browserstack;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePageTest {

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
    
    @AfterEach
    void tearDown() {

        if(title().equals("DemandBridge")){
            executeJavaScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
        }
        else {
            executeJavaScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
        }

        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("The titles should match")
    void shouldSeeBuildList() {
        Selenide.open("http://demandbridge.com");
        title().contentEquals("DemandBridge");
        $(".six-up-images").shouldBe(visible);
    }
}
