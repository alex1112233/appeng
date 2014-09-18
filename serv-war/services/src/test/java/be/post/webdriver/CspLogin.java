package be.post.webdriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CspLogin {
    public static void main(String[] args) throws Exception {
        // The Firefox driver supports javascript 
    	WebDriver driver = new FirefoxDriver();
               
        
        // Go to the Google Suggest home page        
        driver.get("http://csp-dv2.netpost/csp/indexPage.do");
        
        // Enter the query string "Cheese"
        WebElement query = driver.findElement(By.name("j_username"));       
        query.sendKeys("u353002");
        
        WebElement queryPwd = driver.findElement(By.name("j_password"));
        queryPwd.sendKeys("Sep2014");
        
 //       driver.findElement(By.xpath("//input[@value='OK']")).click();

        WebElement myDynamicElement = (new WebDriverWait(driver, 100))
        		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='tasksArea']")));
        
/**
        // Sleep until the div we want is visible or 5 seconds is over
        long end = System.currentTimeMillis() + 25000;
        while (System.currentTimeMillis() < end) {
            WebElement resultsDiv = driver.findElement(By.xpath("//div[@id='tasksArea']"));

            // If results have been returned, the results are displayed in a drop down.
            if (resultsDiv.isDisplayed()) {
              break;
            }
        }
*/
        // And now list the suggestions
        List<WebElement> allSuggestions = driver.findElements(By.xpath("//a"));
        
        for (WebElement suggestion : allSuggestions) {
            System.out.println(suggestion.getText());
        }

        driver.quit();
    }
}
