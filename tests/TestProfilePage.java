package tests;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.Keys;

public class TestProfilePage {
    WebDriver driver;

    @Before
    public void launchBrowser() {
        //profile below is specific to skanes machine
        
        // property and caps are set to my specific machine atm; need to change if you
        // want to run
        System.setProperty("webdriver.chrome.driver", "/Users/spencerkane/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080");

        DesiredCapabilities caps;

        caps = new DesiredCapabilities();
        caps.setCapability("platform", "MONTEREY");
        caps.setCapability("version", 12.1);
        caps.setCapability("browserName", "chrome");
    }
//If you would like to run the tests yourself
//  fill out your own profile with the template below
//  simply uncomment below then comment out the above profile
//--start of template------------------------------------------------
    // @Before
    // public void launchBrowser() {
    //     System.setProperty("driver", "path");
    //     driver = new <type>Driver();
    //     driver.get("http://localhost:5000");

    //     DesiredCapabilities caps;

    //     caps = new DesiredCapabilities();
    //     caps.setCapability("-", "-");
    //     
    // }
//--end of template---------------------------------------------------

    @Test
    public void test_userInfo() {
        // Login to account go to accounts profile page find user info go home log out

        Boolean isPresent;
        driver.findElement(By.xpath("//a[text()='Login']")).click();

        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // find profile name in headerlinks, click
        driver.findElement(By.xpath("//a[text()='spencer']")).click();

        // ensure on user profile page
        isPresent = driver.findElements(By.id("user")).size() > 0;
        assertTrue("Success: user name visible", isPresent);

        isPresent = driver.findElements(By.id("email")).size() > 0;
        assertTrue("Success: email visible", isPresent);

        isPresent = driver.findElements(By.id("lcn")).size() > 0;
        assertTrue("Success: location visible", isPresent);

        // logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        // ensure at home
        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: log-in, log-out", isPresent);
    }

    @Test
    public void test_userChangeLocation() {
        // Login to account go to accounts profile page change location

        Boolean isPresent;
        driver.findElement(By.xpath("//a[text()='Login']")).click();

        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // find profile name in headerlinks, click
        driver.findElement(By.xpath("//a[text()='spencer']")).click();

        //click settings
        driver.findElement(By.xpath("//button[text()='Settings']")).click();

        //change location
        driver.findElement(By.id("lct-text")).sendKeys("Des Moines");
          //click submit
          driver.findElement(By.xpath("//button[text()='Submit']")).click();

          isPresent = driver.findElements(By.id("lcn")).size() > 0;
        assertTrue("Success: location changed", isPresent);

          // logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    @After
    public void close() {

        driver.close();
    }

    
}
