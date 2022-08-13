package tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Before;
import org.junit.After;

public class TestHome {
    WebDriver driver;

    @Before
    public void launchBrowser() {
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
    // }
//--end of template---------------------------------------------------

    @Test
    public void test_headerLinks() {
        Boolean isPresent;
        // checks if opens on home page
        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: open on Home page", isPresent);

        // click 'Home' while at 'Home'
        // check element to ensure still on 'Home' page
        driver.findElement(By.xpath("//a[text()='Home']")).click();
        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: Home->Home", isPresent);

        // 'Home'->'Login'
        // check element to ensure on 'Login' page
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        isPresent = driver.findElements(By.xpath("//button[text()='Login']")).size() > 0;
        assertTrue("Home->Login click successful \n", isPresent);

        // 'Login'->'Home'
        // check element to ensure on 'Home' page
        driver.findElement(By.xpath("//a[text()='Home']")).click();
        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: Login->Home\n", isPresent);

        // 'Home'->'Register'
        // check element to ensure on 'Register' page
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        isPresent = driver.findElements(By.xpath("//button[text()='Register User']")).size() > 0;
        assertTrue("Success: Home->Register\n", isPresent);

        // 'Register'->'Home'
        // check element to ensure on 'Home' page
        driver.findElement(By.xpath("//a[text()='Home']")).click();
        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: Home->Register\n", isPresent);

        // 'Home'->'Login'->'Register'
        // check element to ensure on 'Register' page
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        isPresent = driver.findElements(By.xpath("//button[text()='Register User']")).size() > 0;
        assertTrue("Success: Home->Login->Register\n", isPresent);

        // 'Register'->'Login'
        // check element to ensure on 'Login' page
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        isPresent = driver.findElements(By.xpath("//button[text()='Login']")).size() > 0;
        assertTrue("Success: Register->Login\n", isPresent);
    }

    @Test
    public void test_iconClick() {
        // click the icon to return to the home page
        Boolean isPresent;
        // click icon-- 'Home'->'Home'
        driver.findElement(By.xpath("//img[contains(@src,'https://i.imgur.com/L9DWoEA.png')]")).click();
        // 'Home'->'Login'
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // 'Login->'Home
        driver.findElement(By.xpath("//img[contains(@src,'https://i.imgur.com/L9DWoEA.png')]")).click();
        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // 'Register'->'Home
        driver.findElement(By.xpath("//img[contains(@src,'https://i.imgur.com/L9DWoEA.png')]")).click();

        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        assertTrue("Success: clicking icon to return home while logged in", isPresent);
    }

    @Test
    public void test_iconClickLoggedIn() {
        // click the icon to return to the home page while logged in

        Boolean isPresent;
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // click icon-- 'Home'->'Home'
        driver.findElement(By.xpath("//img[contains(@src,'https://i.imgur.com/L9DWoEA.png')]")).click();
        // 'Home'->'Profile'
        driver.findElement(By.xpath("//a[text()='spencer']")).click();
        // 'Profile'->'Home
        driver.findElement(By.xpath("//img[contains(@src,'https://i.imgur.com/L9DWoEA.png')]")).click();

        isPresent = driver.findElements(By.cssSelector(".header")).size() > 0;
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        assertTrue("Success: clicking icon to return home while logged in", isPresent);
    }

    @After
    public void close() {

        driver.close();
    }

}