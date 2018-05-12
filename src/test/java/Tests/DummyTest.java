package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;




public class DummyTest {
	public WebDriver driver;
	public static final String URL = "http://the-internet.herokuapp.com/";

	@Before
	public void setUp(){
		  System.setProperty("webdriver.gecko.driver","./Driver/geckodriver");
          driver = new FirefoxDriver();
          driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
          driver.get(URL);  
	}
	
	@After
	public void tearDown(){  
		
	     driver.quit();
	}
	
@Test
public void checkTest() throws InterruptedException {
driver.navigate().to(URL+"/forgot_password");
driver.findElement(By.cssSelector("button#form_submit")).click();
Thread.sleep(1000);
assertEquals("Internal Server Error",driver.findElement(By.tagName("h1")).getText());
driver.navigate().back();
driver.findElement(By.cssSelector("input#email")).sendKeys("Hello@example.com");
driver.findElement(By.cssSelector("button#form_submit")).click();
assertEquals("Your e-mail's been sent!",driver.findElement(By.cssSelector("div#content")).getText());
}




}
