package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TestHandleAutoSuggestions_UsingWebScrapping extends BaseTest {

	@Test
	public void testUsingWebScraping_Google() {

		String expectedSearchTerm = "lambdatest careers";

		navigateToGoogleAndSearch();

		// Fetch the list of all suggestions
		System.out.println("Fetching the web element list for all suggestions");
		WebElement autoSuggestionListBox = driver.findElement(By.xpath("//*[@role='listbox']"));
		List<WebElement> autoSuggestionList = autoSuggestionListBox.findElements(By.xpath(".//li"));

		// scrape the autoSuggestionList to get each suggested term for given search
		// term.
		System.out.println("<-------- Started Web scraping for suggestion list -------->");
		for (WebElement listTerm : autoSuggestionList) {
			WebElement term = listTerm.findElement(By.xpath(".//span"));
			System.out.println("Auto Suggestion Value : " + term.getText());
			if (term.getText().equalsIgnoreCase(expectedSearchTerm)) {
				System.out.println("\nFound required value in auto suggestion list. Clicking on it now\n");
				term.click();
				break;
			}
		}

		// wait for the search results page to load and verify if it has same term as we
		// expected.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@type='search']")).getText(), expectedSearchTerm,
				"Valid Search result page.");
		System.out.println("Reached to google search result page for : " + expectedSearchTerm);
	}
	
		@Test
	public void testUsingWebScraping_Amazon(){
		
		String expectedSearchTerm = "samsung galaxy s22";
		
		// to navigate to the website and enter search term
		System.out.println("Navigating to the website");
		driver.get("https://www.amazon.com");

		System.out.println("Entering search term as : samsung galaxy s");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("samsung galaxy s");

		// explicit wait to wait for the auto suggestions list to be present
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='left-pane-results-container']")));
		System.out.println("Auto-suggestions list found");
		
		// Fetch the list of all suggestions
		System.out.println("Fetching the web element list for all suggestions");
		WebElement autoSuggestionListBox = driver.findElement(By.xpath("//*[@class='left-pane-results-container']"));
		List<WebElement> autoSuggestionList = autoSuggestionListBox.findElements(By.xpath(".//div[@class='s-suggestion-container']"));
		
		// scrape the autoSuggestionList to get each suggested term for given search term.
		System.out.println("<-------- Started Web scraping for suggestion list -------->");
		for (WebElement listTerm : autoSuggestionList) {
			WebElement term = listTerm.findElement(By.xpath(".//div"));
			System.out.println("Auto Suggestion Value : " + term.getText());
			if (term.getText().equalsIgnoreCase(expectedSearchTerm)) {
				System.out.println("\nFound required value in auto suggestion list. Clicking on it now\n");
				term.click();
				break;
			}
		}
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]")));
		//scraping the search results page to get the price of required product
		List<WebElement> productDetails = driver.findElements(By.xpath("//*[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS')]"));
		for(WebElement product : productDetails) {
			WebElement productName = product.findElement(By.xpath(".//h2"));
			WebElement productPrice = product.findElement(By.xpath(".//*[@class='a-price']"));
			System.out.println("Product Name: " + productName.getText());
			if(productName.getText().contains("Ultra 5G S908U")) {
				System.out.println("\nFound Required Product");
				System.out.println("Required Product Price : " + productPrice.getText());
				break;
			}
		}
	}
}
