package be.post.webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CspFileUpload {
	 private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://csp-dv2.netpost/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testCspFileUpload() throws Exception {
	    driver.get(baseUrl + "/csp/indexPage.do");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("u353002");
	    driver.findElement(By.id("j_password")).sendKeys("Sep2014");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();	   
	    driver.findElement(By.cssSelector("dt > a")).click();
//	    driver.findElement(By.name("file")).clear();
	    driver.findElement(By.name("file")).sendKeys("C:\\Projects\\CSP\\doc\\DEV_V0001.11.00\\testFiles\\testAlternatives1.XML");
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    
	    
        List<WebElement> allSuggestions = driver.findElements(By.xpath("//a"));
        
        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());
        }
        
        if(driver.getPageSource().contains("the file was successfully uploaded"))
	    {
	        System.out.println("the file was successfully uploaded");
	    }

	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      System.out.println(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}
