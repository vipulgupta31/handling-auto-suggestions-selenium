package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TestHandleAutoSuggestions_UsingSuggestionsList extends BaseTest {

	@Test
	public void testUsingAutoSuggestionsList() {

		String expectedSearchTerm = "lambdatest careers";

		navigateToGoogleAndSearch();

		// to fetch the webElement for all the suggestions from list
		List<WebElement> autoSuggestionList = driver.findElements(By.xpath("//*[@role='option']"));

		// to traverse the list and navigate to required suggestion search results
		for (WebElement autoSuggestion : autoSuggestionList) {
			System.out.println("Auto Suggestion Value : " + autoSuggestion.getText());
			if (autoSuggestion.getText().equalsIgnoreCase(expectedSearchTerm)) {
				System.out.println("\nFound required value in auto suggestion list. Clicking on it now\n");
				autoSuggestion.click();
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
