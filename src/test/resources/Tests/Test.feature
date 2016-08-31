Feature:Assignment 

Scenario:Login success 
Given I navigated to Login page
When I logged in with credentials as "tomsmith" and "SuperSecretPassword!"
Then Login should be successful with message as "You logged into a secure area!"

Scenario: Login failure 1 
Given I navigated to Login page
When I logged in with credentials as "anything" and "SuperSecretPassword!"
Then Login should be failed with error message as "Your username is invalid!"

Scenario: Login failure 2
Given I navigated to Login page
When I logged in with credentials as "tomsmith" and "wrongPassword"
Then Login should be failed with error message as "Your password is invalid!"

Scenario: Hover scenario 
When I navigated to Hover page
Then I should get correct text info when hovered is triggered on images 

Scenario: Sorted data table 
Given I navigated to sorted data table page
When I clicked on header of Last name 
Then Last name should be sorted in alphabetical ascending  order 
When I clicked on header of Last name 
Then Last name should be sorted in alphabetical descending order 