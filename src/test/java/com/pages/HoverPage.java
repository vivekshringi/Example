package com.pages;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class HoverPage {

	@FindBys(@FindBy(className = "figure"))
	public List<WebElement> hoverImages;
	

}