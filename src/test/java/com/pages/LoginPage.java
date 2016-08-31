package com.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;




public class LoginPage {
	
	public static final String SEARCH_PAGE_URL = "http://bet.unibet.co.uk/search?";

	@FindBy(id = "username")
	public WebElement userName;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(css = "button.radius")
	public WebElement loginButton;
	
	@FindBy(id = "flash")
	public WebElement message;
	
	@FindBy(css = "i.icon-2x.icon-signout")
	public WebElement logout;
	
	public void Login(String username, String password){
		this.userName.sendKeys(username);
		this.password.sendKeys(password);
		loginButton.click();
	}
	
	
	
	

}