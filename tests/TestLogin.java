package tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Before;
import org.junit.After;

public class TestLogin {
    WebDriver driver;

    //profile below is specific to skanes machine
    @Before
    public void launchBrowser() {
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
    public void test_logio() {
        // Login to account go to accounts profile page go home log out

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
        assertTrue("Success: profile page", isPresent);

        // logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        // ensure at home
        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: log-in, log-out", isPresent);
    }

    @Test
    public void test_unregisteredLogin() {
        // login with unregistered account (email and password)

        Boolean isPresent;

        driver.findElement(By.xpath("//a[text()='Login']")).click();

        // email address
        driver.findElement(By.id("email")).sendKeys("invalid@invalid.com");
        // password
        driver.findElement(By.id("password")).sendKeys("invalid");

        driver.findElement(By.xpath("//button[text()='Login']")).click();

        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: error message unregistered login", isPresent);
    }

    @Test
    public void test_invalidPassword() {
        // login with registered account with invalid password

        Boolean isPresent;

        driver.findElement(By.xpath("//a[text()='Login']")).click();

        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // incorrect password
        driver.findElement(By.id("password")).sendKeys("invalid");

        driver.findElement(By.xpath("//button[text()='Login']")).click();

        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: error message invalid password", isPresent);
    }

    @Test
    public void test_loginoutinout() {
        // log in log out log in log out

        Boolean isPresent;

        // 'Home'->'Login'
        driver.findElement(By.xpath("//a[text()='Login']")).click();

        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");

        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        // 'Home'->'Login'
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");

        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: log-in out then back in and back out", isPresent);
    }

    @After
    public void close() {

        driver.close();
    }

}
