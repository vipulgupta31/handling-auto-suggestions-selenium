package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class TestUsingKeyActions extends BaseTest {

	@Test
	public void testUsingArrowKeys() {

		navigateToGoogleAndSearch();

		// if we want to specifically click on the second term from auto-suggestions
		// list
		for (int i = 1; i < 3; i++) {
			driver.findElement(By.name("q")).sendKeys(Keys.ARROW_DOWN);
			System.out.println("Pressing down arrow key to reach " + i + " search term");
		}
		System.out.println("Hitting enter key on the required search term");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		// wait for the search results page to load and verify if it has same term as we
		// expected.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='search']")));
		System.out.println(
				"Search term on results page : " + driver.findElement(By.xpath("//*[@type='search']")).getText());

		// verify that search term on results page consists the inital input term given
		Assert.assertEquals(driver.findElement(By.xpath("//*[@type='search']")).getText().contains("lambdatest"), true,
				"Valid Search result page.");
		System.out.println("Reached to search result page for given term");
	}
}
