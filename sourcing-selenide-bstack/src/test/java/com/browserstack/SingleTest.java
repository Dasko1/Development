package com.browserstack;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class SingleTest {

	@Test
	public void test() throws Exception {

		open("http://www.google.com");
		$(By.name("q")).setValue("BrowserStack").pressEnter();
		sleep(2000);
		Assert.assertTrue(Pattern.matches("BrowserStack -.*", title()));
		
		if(title().equals("BrowserStack - Google Search")){
			executeJavaScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Title matched!\"}}");
		}
		else {
			executeJavaScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Title not matched\"}}");
		}
		
	}
}
