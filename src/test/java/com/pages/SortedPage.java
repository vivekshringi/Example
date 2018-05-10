package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class SortedPage {
	@FindBys(@FindBy(className = "last-name"))
	public List<WebElement> AllLastNames;
	
	@FindBy(className = "last-name")
	public WebElement lastName;
}
