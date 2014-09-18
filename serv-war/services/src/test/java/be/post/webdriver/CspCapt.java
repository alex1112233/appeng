package be.post.webdriver;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CspCapt {
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
  public void testCspCapt() throws Exception {
    driver.get(baseUrl + "/csp/pub/validateAddress.do?lang=fr");
    driver.findElement(By.id("streetInput")).clear();
    driver.findElement(By.id("streetInput")).sendKeys("wetstraat");
    driver.findElement(By.id("houseNumberInput")).clear();
    driver.findElement(By.id("houseNumberInput")).sendKeys("1");
    driver.findElement(By.id("postalCodeInput")).clear();
    driver.findElement(By.id("postalCodeInput")).sendKeys("1000");
    driver.findElement(By.id("submit")).click();
    driver.findElement(By.name("feedback")).clear();
    driver.findElement(By.name("feedback")).sendKeys("test comment");
    driver.findElement(By.id("recaptcha_response_field")).clear();
    
    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)  //.pollingEvery(5, TimeUnit.SECONDS);
    	       .withTimeout(300, TimeUnit.SECONDS)
    	       .pollingEvery(5, TimeUnit.SECONDS);
//    	       .ignoring(NoSuchElementException.class);
//    
//    Boolean clickSelected = (new WebDriverWait(driver, 100, 1000))
//  		  .until(ExpectedConditions.invisibilityOfElementLocated(By.id("investigate")));
    
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("investigate")));
    
  //  driver.findElement(By.id("investigate")).click();
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
