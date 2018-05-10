#Introduction
This project is to show case browser automation capability using Selenium. Basic automation framework is build to test a web application. This framework supports page object model to separate elements from test logic. Behavior driven development style scenarios can also be written in this framework to check the product behavior.

##Tools 
Maven - Maven is used as build and project management tool 
JUnit- Testing framework to compare expected results and actual result, It also decide in which order tests should be executed 
cucumber-jvm - Test specification in plain english to understand product behaviour 
Selenium - Browser Automation framework to automatically driving the events on browser to test application under test 

##Prerequisite : 
Java should be installed on host machine (e.g. Java version should be update in pom.xml file)
Firefox browser should be installed (By default my framework support firefox using firefox but it can be executed on other browsers by adding appropriate drivers)
Gecko driver is used to selenium webdriver to support selenium

##Tests Covered
Login success,
Login failure 1,
Login failure 2,
Hover scenario,
Sorted data table,

##Project File Structure 
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

##File Description

RunCukesTest.java is having test runner configuration of cucumber
Stepdefs.java-All Step definitions of test.feature are defined here 
HoverPage, LoginPage, SortedPage are Pages using Page object model
Test.feature - All Scenarios are mentioned in this file 

###How to run : 
Maven install - It will download all project related dependencies jars and will perform all tests
 
Test Reports are generated in xml/html format, It also publish screenshot for failed tests

