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

public class TestPost {
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
    public void test_loginClickPost() {
        Boolean isPresent;
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebElement Element = driver.findElement(By.xpath("//button[text()='Create Post']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(By.xpath("//button[text()='Create Post']")).sendKeys(Keys.RETURN);

        isPresent = driver.findElements(By.className("formPopup")).size() > 0;
        assertTrue("Success: post form displayed to user", isPresent); 

         // logout
         driver.findElement(By.xpath("//a[text()='Logout']")).click(); 
    }

    @Test
    public void test_clickPost() {
        Boolean isPresent;

        WebElement Element = driver.findElement(By.xpath("//button[text()='Create Post']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(By.xpath("//button[text()='Login to Create Post']")).sendKeys(Keys.RETURN);

        isPresent = driver.findElements(By.xpath("//button[text()='Login']")).size() > 0;
        assertTrue("Success: redirected to login", isPresent); 
    }

    @Test
    public void test_makePost() {
        Boolean isPresent;
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebElement Element = driver.findElement(By.xpath("//button[text()='Create Post']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(By.xpath("//button[text()='Create Post']")).sendKeys(Keys.RETURN);

   // where?
   driver.findElement(By.id("name")).sendKeys("The Cafe");
   // thoughts?
   driver.findElement(By.id("thoughts")).sendKeys("Yum!");
   driver.findElement(By.id("address")).sendKeys("2616 Northridge Pkwy Ames");

        // driver.findElement(By.className("rate"));
        // WebElement radio = driver.findElement(By.className("rate"));
        // radio.click();
        
        driver.findElement(By.xpath("//button[text()='Post']")).click();

        isPresent = driver.findElements(By.id("box")).size() > 0;
        assertTrue("Success: post successful", isPresent); 

         // logout
         driver.findElement(By.xpath("//a[text()='Logout']")).click(); 
    }
    @Test
    public void test_closePost() {
        Boolean isNotPresent;
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebElement Element = driver.findElement(By.xpath("//button[text()='Create Post']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        driver.findElement(By.xpath("//button[text()='Create Post']")).sendKeys(Keys.RETURN);

        // where?
        driver.findElement(By.id("name")).sendKeys("The Cafe");
        // thoughts?
        driver.findElement(By.id("thoughts")).sendKeys("Yum!");
        //address
        driver.findElement(By.id("address")).sendKeys("2616 Northridge Pkwy Ames");

        // driver.findElement(By.className("rate"));
        // WebElement radio = driver.findElement(By.className("rate"));
        // radio.click();
        
        driver.findElement(By.xpath("//button[text()='Close']")).click();

        isNotPresent = driver.findElements(By.cssSelector(".box")).size() <= 0;
        assertTrue("Success: post form closed before posting", isNotPresent); 

         // logout
         driver.findElement(By.xpath("//a[text()='Logout']")).click(); 
    }

    @Test 
    public void test_postVisibility(){
        //test if posts can be viewed on the home page (not logged)
        Boolean isPresent;
        driver.manage().window().maximize();
       isPresent = driver.findElements(By.className("locationContainer")).size() > 0;

       assertTrue("Success: post is visible", isPresent); 
    }

    @Test 
    public void test_postVisibilityLI(){
        //log in then see if posts are visible
        Boolean isPresent;

        driver.manage().window().maximize();

    // 'Home'->'Login'
    driver.findElement(By.xpath("//a[text()='Login']")).click();
    // email address
    driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
    // password
    driver.findElement(By.id("password")).sendKeys("spencer");

    driver.findElement(By.xpath("//button[text()='Login']")).click();

       isPresent = driver.findElements(By.className("locationContainer")).size() > 0;

       assertTrue("Success: post is visible", isPresent); 
    }

    @Test
    public void test_postInteractivity(){
        Boolean isPresent;
        driver.manage().window().maximize();
       
       driver.findElement(By.className("locationContainer")).click();

    isPresent = driver.findElements(By.className("times")).size() > 0;
       assertTrue("Success: hours are visible", isPresent); 

       isPresent = driver.findElements(By.className("mid-title")).size() > 0;
       assertTrue("Success: menu is visible", isPresent); 

       isPresent = driver.findElements(By.className("comment")).size() > 0;
       assertTrue("Success: reviews are visible", isPresent); 

    }

    @Test
    public void test_leaveReview(){
        Boolean isPresent;
        driver.manage().window().maximize();
       
       driver.findElement(By.className("locationContainer")).click();
       driver.findElement(By.xpath("//button[text()='Login to Review']")).click();

        // email address
        driver.findElement(By.id("email")).sendKeys("spencer@spencer.com");
        // password
        driver.findElement(By.id("password")).sendKeys("spencer");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        driver.findElement(By.className("locationContainer")).click();
        driver.findElement(By.xpath("//button[text()='Leave a Review']")).click();

        driver.findElement(By.id("thoughts")).sendKeys("Good stuff!");

     driver.findElement(By.className("rate"));
     WebElement radio = driver.findElement(By.className("rate"));
    radio.click();

    driver.findElement(By.xpath("//button[text()='Post']")).click();

isPresent = driver.findElements(By.className("comment")).size() > 0;
  assertTrue("Success: reviews are visible", isPresent); 
    }

    @After
    public void close() {

        driver.close();
    }

}