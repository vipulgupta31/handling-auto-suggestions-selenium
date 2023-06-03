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
}
