package Tests;

import com.pages.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import static org.junit.Assert.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Stepdefs {
	public WebDriver driver;
	private LoginPage loginPage;
	private HoverPage hoverPage;
	private SortedPage sortedPage;
	public static final String URL = "http://the-internet.herokuapp.com/";
	static int count =0;

	@Before
	public void setUp(){
		  System.setProperty("webdriver.gecko.driver","./Driver/geckodriver");
          driver = new FirefoxDriver();
          driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
          driver.get(URL);
     	 loginPage = PageFactory.initElements(driver, LoginPage.class);
     	 hoverPage = PageFactory.initElements(driver, HoverPage.class);
     	 sortedPage = PageFactory.initElements(driver, SortedPage.class);
         
	}
	
	@After
	public void tearDown(Scenario scenario){  
		 if(scenario.isFailed()){
			 final byte[] screenshot = ((TakesScreenshot) driver)
                     .getScreenshotAs(OutputType.BYTES);
			 scenario.embed(screenshot, "image/png");
		 }
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
	
	@Given("^I want to test Basic Authentication$") 
		public void basicAuth(){
		    driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		    assertEquals(driver.findElement(By.cssSelector("p")).getText(), "Congratulations! You must have the proper credentials.");;
		}
		
	@Given("^I want to test Broken Image verification$")
		public void brokenImageVerification() throws ClientProtocolException, IOException{
			
			driver.navigate().to(URL+"/broken_images");
			List <WebElement> images = driver.findElements(By.tagName("img"));
			for(WebElement l : images){
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(l.getAttribute("src"));
				HttpResponse response = (HttpResponse) client.execute(request);
				if(((org.apache.http.HttpResponse) response).getStatusLine().getStatusCode()!=200){
				  System.out.println(l.getAttribute("src")+" is  a Broken image");
				  count++;
				}
				
			}
			System.out.println(count+" images are broken out of "+images.size());
		}
		
	@Given("^I want to test Challenging DOM$")
		public void challengingDOM(){
		driver.navigate().to(URL+"/challenging_dom");
		for(int i=0;i<10;i++){
		driver.findElement(By.xpath("//div[@id='content']/div/div/div/div/a")).click();
		List <WebElement> buttons = driver.findElements(By.xpath("//div[@id='content']/div/div/div/div/a"));
		for(WebElement a : buttons){
			assertTrue(Arrays.asList("baz", "foo", "qux","bar").contains(a.getText()));
			System.out.println((a.getText()+"Text Color is "+a.getCssValue("background-color")));
		}
		System.out.println(driver.findElement(By.id("canvas")).getText());
		System.out.println(driver.findElement(By.id("canvas")).getLocation());
		System.out.println(driver.findElement(By.id("canvas")).getSize());
		 //((JavascriptExecutor)driver).executeScript("document.");
		//To do here is how to get text from canvas and verify. to get canvas text we have perform java script method what exactly i have to use that needs to be checked"
		// How to convert rgba value to hex code
		}
		}
		
	@Given("^I want to test Check box Test$")
		public void checkBoxTest() throws InterruptedException{
			driver.navigate().to(URL+"/checkboxes");
			List <WebElement> l = driver.findElements(By.xpath("//input[@type='checkbox']"));
			for(WebElement a:l){
				a.click();
				//System.out.println(a.getAttribute("Checked"));
				assertTrue(a.getAttribute("checked"), true);
			}
		
		}
		
	@Given("^I want to test Context menu test$")
		public void contextMenu() throws InterruptedException{
			driver.navigate().to(URL+"/context_menu"); 
	        Actions action= new Actions(driver);
	        action.contextClick(driver.findElement(By.id("hot-spot"))).build().perform();
	        action.sendKeys(Keys.ARROW_DOWN).build().perform();
	        action.sendKeys(Keys.ARROW_DOWN).build().perform();
	        action.sendKeys(Keys.ARROW_DOWN).build().perform();
	        action.sendKeys(Keys.ARROW_DOWN).build().perform();
	        action.sendKeys(Keys.ARROW_DOWN).build().perform();
	        action.sendKeys(Keys.ENTER).build().perform();
	        Alert alert = driver.switchTo().alert();
	        assertEquals(alert.getText(), "You selected a context menu");
	        alert.accept();
	        assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Context Menu");
	        
		}
		
		
	@Given("^I want to test Disappearing element test$")
		public void disappearingElementsTest() throws InterruptedException{
			driver.navigate().to(URL+"/disappearing_elements");
			driver.navigate().refresh();
			List <WebElement> l = driver.findElements(By.cssSelector("li > a"));
			for(WebElement a:l){
				   String bef_1=a.getCssValue("color");
				   String bef_2=a.getCssValue("font-size");
				   Actions action= new Actions(driver);
				   Thread.sleep(5000);
				   action.moveToElement(a).build().perform();
				   Thread.sleep(5000);
				   String aft_1=a.getCssValue("color");
				   String aft_2=a.getCssValue("font-size");
				   assertFalse(bef_1.equals(aft_1));
				   assertFalse(bef_2.equals(aft_2));
				   assertTrue(Arrays.asList("Home","About","Contact Us","Portfolio","Gallery").contains(a.getText()));
			}
		
		}
		
	@Given("^I want to test Drag and Drop test$")
		public void drag_and_dropTest() throws InterruptedException{
			driver.navigate().to(URL+"/drag_and_drop");
	         WebElement source= driver.findElement(By.id("column-a"));
	        WebElement target= driver.findElement(By.id("column-b"));
	       System.out.println(source.getAttribute("style"));
	       System.out.println(source.getLocation());
	       Actions a= new Actions(driver);
	       a.moveByOffset(240, 90);
	       a.clickAndHold(source);
	       a.moveToElement(source,490, 90);
	       a.release();
	       a.build().perform();
	        //new Actions(driver).dragAndDropBy(source, 400, 400).build().perform();
	       // new Actions(driver).dragAndDrop(source, target).build().perform();
	        //new Actions(driver).clickAndHold(source).moveToElement(source, 400, 400).build().perform();
	        //new Actions(driver).moveToElement(source).clickAndHold().moveToElement(source,215,0).release().build().perform();
	        
	        Thread.sleep(10000);
			}
		
	@Given("^I want to test Drop down second try$")
		public void dropDownTest() throws InterruptedException{
			driver.navigate().to(URL+"/dropdown");
			Select S = new Select(driver.findElement(By.id("dropdown")));
			S.selectByIndex(1);
			System.out.println((S.getFirstSelectedOption()).getText());
			S.selectByValue("1");
			System.out.println((S.getFirstSelectedOption()).getText());
			S.selectByVisibleText("Option 2");
			String s=(S.getFirstSelectedOption()).getText();
			System.out.println(s);
			
		}
		
	@Given("^I want to test Dynamic tests$")
		public void dynamicContent() throws InterruptedException{
			for(int i =0;i<10;i++){
			driver.navigate().to(URL+"/dynamic_content");
			List <WebElement> images = driver.findElements(By.xpath("//div/img"));
			for(WebElement j : images){
			    System.out.println(j.getAttribute("src").subSequence(84, 85));
				assertTrue(Arrays.asList("1", "2", "3","4","5","6","7").contains(j.getAttribute("src").subSequence(84, 85)));
				
			}
			}
		}
		
	@Given("^I want to test Dynamic controls$")
		public void dynamicControls() {
			driver.navigate().to(URL+"/dynamic_controls");
	        driver.findElement(By.id("checkbox")).click();
	        assertEquals(driver.findElement(By.id("btn")).getText(), "Remove");
	        driver.findElement(By.id("btn")).click();
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading"))); 
	        String a = driver.findElement(By.id("message")).getText();
	        assertEquals(a, "It's gone!");
	        assertEquals(driver.findElement(By.id("btn")).getText(), "Add");
	        driver.findElement(By.id("btn")).click();
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading"))); 
	        a = driver.findElement(By.id("message")).getText();
	        assertEquals(a, "It's back!");
		}
		
	@Given("^I want to test Dynamic loading$")
		public void dynamicLoading() {
			driver.navigate().to(URL+"/dynamic_loading");
			driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();
			String s = driver.getCurrentUrl();
			assertEquals(s, "http://the-internet.herokuapp.com/dynamic_loading/1");
			System.out.println(driver.findElement(By.cssSelector("#finish > h4")).getText());
			driver.findElement(By.cssSelector("button")).click();
		    WebDriverWait wait = new WebDriverWait(driver, 10);
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading"))); 
			System.out.println(driver.findElement(By.cssSelector("#finish > h4")).getText());
			driver.navigate().to(URL+"/dynamic_loading");
			driver.findElement(By.linkText("Example 2: Element rendered after the fact")).click();
			String s1 = driver.getCurrentUrl();
			assertEquals(s1, "http://the-internet.herokuapp.com/dynamic_loading/2");
			driver.findElement(By.cssSelector("button")).click();
		    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading"))); 
			System.out.println(driver.findElement(By.cssSelector("#finish > h4")).getText());
		}
		
	@Given("^I want to test exit Intent$")
		public void exitIntent() {
			driver.navigate().to(URL+"/exit_intent");
			assertEquals(driver.getTitle(), "The Internet");
			Actions act = new Actions(driver);
			act.moveByOffset(0, 0);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}


	
}
