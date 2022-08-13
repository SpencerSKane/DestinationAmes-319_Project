package tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Before;
import org.junit.After;

public class TestReg {
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
    // }
//--end of template---------------------------------------------------

    @Test
    public void test_blankReg() {
        // attempting to register a user where all fields are blank

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_nameFilledRestBlank() {
        // name will be the only filled field, an error is expected
        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill name field
        driver.findElement(By.id("name")).sendKeys("jim");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_nameBlank() {
        // name will be the only empty field, an error is expected

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill email field
        driver.findElement(By.id("email")).sendKeys("jim@jim.com");
        // fill password field
        driver.findElement(By.id("password")).sendKeys("jim");
        // fill confirm password field
        driver.findElement(By.id("passwordConfirm")).sendKeys("jim");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_emailFilledRestBlank() {
        // email will be the only filled field, an error is expected

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill email field
        driver.findElement(By.id("email")).sendKeys("jim@jim.com");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_emailBlank() {
        // email will be the only empty field, an error is expected

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill name field
        driver.findElement(By.id("name")).sendKeys("jim");
        // fill password field
        driver.findElement(By.id("password")).sendKeys("jim");
        // fill confirm password field
        driver.findElement(By.id("passwordConfirm")).sendKeys("jim");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_passwordFilledRestBlank() {
        // password will be the only filled field, an error is expected
        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill email field
        driver.findElement(By.id("password")).sendKeys("jim");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_passwordBlank() {
        // password will be the only empty field, an error is expected
        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill name field
        driver.findElement(By.id("name")).sendKeys("jim");
        // fill password field
        driver.findElement(By.id("email")).sendKeys("jim@jim.com");
        // fill confirm password field
        driver.findElement(By.id("passwordConfirm")).sendKeys("jim");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_confirmPasswordFilledRestBlank() {
        // confirm password will be the only filled field, an error is expected

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill email field
        driver.findElement(By.id("passwordConfirm")).sendKeys("jim");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_passwordConfirmBlank() {
        // confirm password will be the only empty field, an error is expected

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill name field
        driver.findElement(By.id("name")).sendKeys("jim");
        // fill password field
        driver.findElement(By.id("email")).sendKeys("jim@jim.com");
        // fill confirm password field
        driver.findElement(By.id("password")).sendKeys("jim");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught empty fields", isPresent);
    }

    @Test
    public void test_noAtEmail() {
        // an email with no '@' results in an error

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill name field
        driver.findElement(By.id("name")).sendKeys("john");
        // fill email field
        driver.findElement(By.id("email")).sendKeys("john");
        // fill password field
        driver.findElement(By.id("password")).sendKeys("john");
        // fill confirm password field
        driver.findElement(By.id("passwordConfirm")).sendKeys("john");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught invalid password (no @)", isPresent);

    }

    @Test
    public void test_noComEmail() {
        // an email with no '.com' or similar will result in an error

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill name field
        driver.findElement(By.id("name")).sendKeys("john");
        // fill email field
        driver.findElement(By.id("email")).sendKeys("john@");
        // fill password field
        driver.findElement(By.id("password")).sendKeys("john");
        // fill confirm password field
        driver.findElement(By.id("passwordConfirm")).sendKeys("john");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught invalid email (no .com)", isPresent);
    }

    @Test
    public void test_passwordMatch() {
        // when passwords dont match an error message is displayed

        Boolean isPresent;

        // 'Home'->'Register'
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // click register user
        driver.findElement(By.xpath("//button[text()='Register User']")).click();
        // fill name field
        driver.findElement(By.id("name")).sendKeys("john");
        // fill email field
        driver.findElement(By.id("email")).sendKeys("john@john.com");
        // fill password field
        driver.findElement(By.id("password")).sendKeys("john");
        // fill confirm password field
        driver.findElement(By.id("passwordConfirm")).sendKeys("jose");
        // check for error message (indicates working properly)
        isPresent = driver.findElements(By.cssSelector(".alert.alert-danger.mt-4")).size() > 0;

        assertTrue("Success: caught invalid password(dont match)", isPresent);
    }

    @After
    public void close() {

        driver.close();
    }

}
