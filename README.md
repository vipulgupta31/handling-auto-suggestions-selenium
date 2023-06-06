# Handling Auto-suggestions in Selenium Java


## What is an Auto-suggestion?
Auto-suggestion or auto-complete is a useful feature available on web pages. It is used to provide possible suggestions to a user based on their input on a search box or any other input field on the webpage.

## Advantages of having Auto-suggestions on your website
- Enhanced User Experience
- Improved Search Efficiency
- Higher Accuracy due to auto corrections
- Mobile-Friendly Experience
- Wider website exploration

## Challenges in handling Auto-suggestions
- Delay in auto-suggestion list
- Highly dynamic content
- Cross-browser compatibility
- Pop-up Auto-suggestion windows

## Best Practices to handle Auto-suggestions in Selenium Java
- Using Explicit Waits
- Unique WebElement locators
- Verifying the suggestion value
- Using keyboard events
- Cross-browser testing

## Handling Auto-suggestions using different approaches
This repository contains reference code to handle auto-suggestions using different approaches on Google and Amazon websites.
- Handling auto-suggestion using the entire auto-suggestion list for Google search
- Handling auto-suggestion using keyboard actions for Google search
- Handling auto-suggestion using web scraping for Google search
- Handling auto-suggestion using web scraping for Amazon search

## About Project
It is created using Selenium with Java, TestNG and Maven.

This is the list of tools, being used in this framework:
1. Apache Maven
2. Java 8
3. Selenium Cloud Grid - [LambdaTest](http://www.lambdatest.com?fp_ref=vipul51) Platform
4. TestNG Framework

## Steps for Local Execution
1. Import this project in Eclipse/IntelliJ as “Existing Maven Project”
2. Go to the test file and Run test case for your desired case.
3. You can see the logs coming up on Console as your execution progresses.
4. Since we are using RemoteWebDriver and executing on Cloud Grid platform, [LambdaTest](http://www.lambdatest.com?fp_ref=vipul51), you can login to same and view detailed logs on dashboard.
