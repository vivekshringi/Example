Feature:Project "The Internet"

@Test
Scenario:Login success 
Given I navigated to Login page
When I logged in with credentials as "tomsmith" and "SuperSecretPassword!"
Then Login should be successful with message as "You logged into a secure area!"

@Test
Scenario: Login failure 1 
Given I navigated to Login page
When I logged in with credentials as "anything" and "SuperSecretPassword!"
Then Login should be failed with error message as "Your username is invalid!"

@Test
Scenario: Login failure 2
Given I navigated to Login page
When I logged in with credentials as "tomsmith" and "wrongPassword"
Then Login should be failed with error message as "Your password is invalid!"

@Test
Scenario: Hover scenario 
When I navigated to Hover page
Then I should get correct text info when hovered is triggered on images 

@Test
Scenario: Sorted data table 
Given I navigated to sorted data table page
When I clicked on header of Last name 
Then Last name should be sorted in alphabetical ascending  order 
When I clicked on header of Last name 
Then Last name should be sorted in alphabetical descending order 

@Test
Scenario: Basic Authentication 
Given I want to test Basic Authentication

@Test
Scenario: Broken Image verification 
Given I want to test Broken Image verification

@Test
Scenario: Challenging DOM
Given I want to test Challenging DOM

@Test
Scenario: Check box Test
Given I want to test Check box Test

@NotWorking
Scenario: Context menu test
Given I want to test Context menu test

@Test
Scenario: Disappearing element test
Given I want to test Disappearing element test

@NotWorking
Scenario: Drag and Drop test
Given I want to test Drag and Drop test

@Test
Scenario: Dynamic Tests 
Given I want to test Dynamic tests

@Test
Scenario: Dynamic Controls 
Given I want to test Dynamic controls

@Test
Scenario: Dynamic Loading 
Given I want to test Dynamic loading

@NotWorking
Scenario: Exit Intent
Given I want to test exit Intent