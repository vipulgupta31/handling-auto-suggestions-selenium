package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

	public RemoteWebDriver driver = null;
	public WebDriverWait wait;

	String username = System.getenv("LT_USERNAME") == null ? "<lambdatest_username>" : System.getenv("LT_USERNAME");
	String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "<lambdatest_accesskey>" : System.getenv("LT_ACCESS");

	@BeforeTest
	public void setup() {
		try {
			SafariOptions safariOptions = new SafariOptions();
			safariOptions.setPlatformName("MacOS Ventura");
			safariOptions.setBrowserVersion("16.0");

			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("build", "Auto Suggestions in Selenium");
			ltOptions.put("name", "Handling Auto Suggestions");
			ltOptions.put("w3c", true);
			safariOptions.setCapability("LT:Options", ltOptions);

			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), safariOptions);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	/*
	 * 1. Navigate to google 2. Enter search term 3. Wait for auto-suggestions list
	 * to be visible
	 */
	public void navigateToGoogleAndSearch() {
		// to navigate to the website and enter search term
		System.out.println("Navigating to the website");
		driver.get("https://www.google.com");

		System.out.println("Entering search term as lambdatest");
		driver.findElement(By.name("q")).sendKeys("lambdatest");

		// explicit wait to wait for the auto suggestions list to be present
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@role='listbox']")));
		System.out.println("Auto-suggestions list found");
	}
}
