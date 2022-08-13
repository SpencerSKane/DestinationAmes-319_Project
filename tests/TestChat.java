package tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.Keys;

public class TestChat {
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
    public void test_chatbuttonVisibility() {
        Boolean isPresent;
        Boolean isnotPresent;

        //upon login chat button should be visible

        // click login button
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // enter email address
        driver.findElement(By.id("email")).sendKeys("testuser@email.com");
        // enter password
        driver.findElement(By.id("password")).sendKeys("password");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        // check chat-btn 
        // resultList = findElements(By.class.name("chat-btn"));
        // isPresent = (resultList.size() > 0);
        isPresent = driver.findElements(By.cssSelector(".chat-btn")).size() > 0;
        assertTrue("Success: chat button is visible when logged in", isPresent);
        // logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
    }

    @Test
    public void test_chatbuttonClick() {
        Boolean isPresent;

        // click login button
        driver.findElement(By.xpath("//a[text()='Login']")).click();
        // enter email address
        driver.findElement(By.id("email")).sendKeys("testuser@email.com");
        // enter password
        driver.findElement(By.id("password")).sendKeys("password");
        // click log in
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        // click chat-btn 
         driver.findElement(By.className("chat-btn")).click();
        
        isPresent = driver.findElements(By.className("chat-area")).size() > 0;
        assertTrue("Success: chat area is visible", isPresent);
        isPresent = driver.findElements(By.id("emoji-btn")).size() > 0;
        assertTrue("Success: emoji-btn is visible", isPresent);
        isPresent = driver.findElements(By.className("submit")).size() > 0;
        assertTrue("Success: submit button is visible", isPresent);  
        // logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();      

    }

    @Test
    public void test_sendMessage() {
        Boolean isPresent;

         // click login button
         driver.findElement(By.xpath("//a[text()='Login']")).click();
         // enter email address
         driver.findElement(By.id("email")).sendKeys("testuser@email.com");
         // enter password
         driver.findElement(By.id("password")).sendKeys("password");
         // click log in
         driver.findElement(By.xpath("//button[text()='Login']")).click();
         // click chat-btn 
          driver.findElement(By.className("chat-btn")).click();

          //type in chat-box
          driver.findElement(By.name("chat_input")).sendKeys("Hello world!");

          //send chat
          driver.findElement(By.className("submit")).click();

          isPresent = driver.findElements(By.className("my-msg")).size() > 0;
        assertTrue("Success: my message is visible", isPresent); 

        //logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();  

    }
    @Test
    public void test_sendMessageEnterKey() {
        //send message with the enter key
        Boolean isPresent;

         // click login button
         driver.findElement(By.xpath("//a[text()='Login']")).click();
         // enter email address
         driver.findElement(By.id("email")).sendKeys("testuser@email.com");
         // enter password
         driver.findElement(By.id("password")).sendKeys("password");
         // click log in
         driver.findElement(By.xpath("//button[text()='Login']")).click();
         // click chat-btn 
          driver.findElement(By.className("chat-btn")).click();

          //type in chat-box
          driver.findElement(By.name("chat_input")).sendKeys("Hello world!");

          //send chat
          driver.findElement(By.name("chat_input")).sendKeys(Keys.RETURN);
          //driver.findElement(By.className("submit")).click();

          isPresent = driver.findElements(By.className("my-msg")).size() > 0;
        assertTrue("Success: my message is visible", isPresent); 

        //logout
        driver.findElement(By.xpath("//a[text()='Logout']")).click();  
    }


    @After
    public void close() {

        driver.close();
    }

}