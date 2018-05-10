package Tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class DummyTest {
	public WebDriver driver;
	public static final String URL = "http://the-internet.herokuapp.com/";

	//@Before
	public void setUp(){
		  System.setProperty("webdriver.gecko.driver","./Driver/geckodriver");
          driver = new FirefoxDriver();
          driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
          driver.get(URL);  
	}
	
	//@After
	public void tearDown(){  
		
	     driver.quit();
	}
	
//@Test
public void checkTest() {
	
	
}




}
