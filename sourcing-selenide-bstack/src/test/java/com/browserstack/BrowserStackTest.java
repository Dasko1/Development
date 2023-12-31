package com.browserstack;
import com.browserstack.local.Local;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class BrowserStackTest {
	public RemoteWebDriver driver;
	private Local l;

	public static String username;
	public static String accessKey;
	public static String sessionId;

	@BeforeMethod(alwaysRun=true)
	@Parameters(value={"config", "environment"})
	public void setUp(String config_file, String environment) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + config_file));
		JSONObject envs = (JSONObject) config.get("environments");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
		Iterator it = envCapabilities.entrySet().iterator();
    	while (it.hasNext()) {
      		Map.Entry pair = (Map.Entry)it.next();
      		capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
    	}

		Map<String, String> commonCapabilities= (Map<String, String>) config.get("capabilities");
		it = commonCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			if (capabilities.getCapability(pair.getKey().toString()) == null) {
				capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
			}
		}

		username = System.getenv("BROWSERSTACK_USERNAME");
		if (username == null) {
			username = (String) config.get("user");
		}

		accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		if (accessKey == null) {
			accessKey = (String) config.get("key");
		}

		if (capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true") {
			l = new Local();
			Map<String, String> options = new HashMap<String, String>();
			options.put("key", accessKey);
			String currentTime = String.valueOf(System.currentTimeMillis());
			options.put("localIdentifier", currentTime);
			capabilities.setCapability("browserstack.localIdentifier", currentTime);
			l.start(options);
		}

		driver = new RemoteWebDriver(new URL("http://adaskalopoulos1:RwofYrpjLEBwrwzpTdSc@" + config.get("server") + "/wd/hub"), capabilities);
		sessionId = driver.getSessionId().toString();

		Configuration.timeout = 20000;
		Configuration.baseUrl = "https://dbdemo.dba.development.dbenterprise.com";
		WebDriverRunner.setWebDriver(driver);
	}


}
