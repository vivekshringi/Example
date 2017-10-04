package Tests;

import com.pages.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class Stepdefs {
	public WebDriver driver;
	private LoginPage loginPage;
	private HoverPage hoverPage;
	private SortedPage sortedPage;
	public static final String URL = "http://the-internet.herokuapp.com/";


	@Before
	public void setUp(){
		  System.setProperty("webdriver.gecko.driver","/home/jimmy/selenium/Driver/geckodriver");
          driver = new FirefoxDriver();
          driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
          driver.get(URL);
     	 loginPage = PageFactory.initElements(driver, LoginPage.class);
     	 hoverPage = PageFactory.initElements(driver, HoverPage.class);
     	 sortedPage = PageFactory.initElements(driver, SortedPage.class);
         
	}
	
	@After
	public void tearDown(){
	     
	     driver.close();   
	     driver.quit();
	}


	@Given("^I navigated to Login page$")
	public void i_navigated_to_Login_page() throws Throwable {
	  driver.get(driver.getCurrentUrl()+"/login");
	}

	@When("^I logged in with credentials as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_logged_in_with_credentials_as_and(String arg1, String arg2) throws Throwable {
	 loginPage.Login(arg1, arg2);
	}

	@Then("^Login should be successful with message as \"([^\"]*)\"$")
	public void login_should_be_successful_with_message_as(String arg1) throws Throwable {
		assertTrue(loginPage.message.getText().contains(arg1));
		loginPage.logout.click();
	}

	@Then("^Login should be failed with error message as \"([^\"]*)\"$")
	public void login_should_be_failed_with_error_message_as(String arg1) throws Throwable {
		assertTrue(loginPage.message.getText().contains(arg1));
	}

	@Given("^I navigated to Hover page$")
	public void i_navigated_to_Hover_page() throws Throwable {
		  driver.get(driver.getCurrentUrl()+"/hovers");
	}

	@Then("^I should get correct text info when hovered is triggered on images$")
	public void i_hover_on_avatar_picutures() throws Throwable {
		int a = 1;
		String text []={"user1","user2","user3"};
		
		List<WebElement> elementsFound = hoverPage.hoverImages;
		for(WebElement e:elementsFound){
		Actions builder = new Actions(driver);	
		builder.moveToElement(e).perform();
		assertTrue(driver.findElement(By.xpath("//div[@id='content']/div/div["+a+"]/div/h5")).getText().contains(text[a-1]));
		a++;
		}
	}

	@Given("^I navigated to sorted data table page$")
	public void i_navigated_to_sorted_data_table_page() throws Throwable {
		driver.get(driver.getCurrentUrl()+"/tables");
	}

	@When("^I clicked on header of Last name$")
	public void i_clicked_on_header_of_Last_name() throws Throwable {
	sortedPage.lastName.click();
	}

	@Then("^Last name should be sorted in alphabetical descending order$")
	public void last_name_should_be_sorted_in_alphabetical_descending_order() throws Throwable {
		ArrayList<String>  expected=new ArrayList<String>(Arrays.asList("Last Name","Smith","Doe","Conway","Bach"));
		List<WebElement> elementsFound = sortedPage.AllLastNames;
		ArrayList<String> actual=new ArrayList<String>();  
		for(WebElement e:elementsFound){
			actual.add(e.getText());
			}
		Iterator<String> itrAct=actual.iterator();  
		Iterator<String> itrExp=expected.iterator();
		while(itrAct.hasNext()){  
		assertEquals(itrExp.next(), itrAct.next());
		 }  
	}

	@Then("^Last name should be sorted in alphabetical ascending  order$")
	public void last_name_should_be_sorted_in_alphabetical_ascending_order() throws Throwable {
		ArrayList<String>  expected=new ArrayList<String>(Arrays.asList("Last Name","Bach","Conway","Doe","Smith"));
		List<WebElement> elementsFound = driver.findElements(By.className("last-name"));
		ArrayList<String> actual=new ArrayList<String>();  
		for(WebElement e:elementsFound){
			actual.add(e.getText());
			}
		Iterator<String> itrAct=actual.iterator();  
		Iterator<String> itrExp=expected.iterator();
		while(itrAct.hasNext()){  
		assertEquals(itrExp.next(), itrAct.next());
		 }  
	}


	
}
