Automation Architecture 
Tools used : Selenium Webdriver, cucumber-jvm, Maven

Prerequisite : 
Java should be installed on host machine 
Firefox browser should be installed (version 46.0)

Tests Covered

Login success 
Login failure 1 
Login failure 2 
Hover scenario  
Sorted data table

Project File Structure 
com.pages
  HoverPage.java
  LoginPage.java
  SortedPage.java
Tests
  RunCukesTest.java
  Stepdefs.java
Tests
  Test.feature
target
  Reports 
pom.xml
ReadMe.md

 
RunCukesTest.java is having test runner configuration of cucumber
Stepdefs.java-All Step definitions of test.feature are defined here 
HoverPage, LoginPage, SortedPage are Pages using Page object model
Test.feature - All Scenarios are mentioned in this file 

How to run : 
Maven install - It will download all project related dependencies jars and will perform all tests 
Test Reports are available in xml format


"# Example" 
"# Example" 
