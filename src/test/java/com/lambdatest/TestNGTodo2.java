package com.lambdatest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo2 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        String buildName = System.getenv("LT_BUILD_NAME");

        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        // caps.setCapability("platformName", "Android");
	    // caps.setCapability("deviceName", "Galaxy S21");
	    // caps.setCapability("platformVersion","12");
        // caps.setCapability("isRealMobile", true);
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", buildName);
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");
        
        // To view Logs metrics
        caps.setCapability("performance", true);
        caps.setCapability("network", true);
        caps.setCapability("console", true);
        caps.setCapability("visual",true);

        String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
    }

    @Test
    public void basicTest() throws InterruptedException, IOException {
        String spanText;
        System.out.println("Loading Url");
        
        driver.get("https://lambdatest.github.io/sample-todo-app/");

       System.out.println("Checking Box");
       driver.findElement(By.name("li1")).click();

       System.out.println("Checking Another Box");
       driver.findElement(By.name("li2")).click();

       System.out.println("Checking Box");
       driver.findElement(By.name("li3")).click();

       System.out.println("Checking Another Box");
       driver.findElement(By.name("li4")).click();

       driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
       driver.findElement(By.id("addbutton")).click();

       driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
       driver.findElement(By.id("addbutton")).click();

       driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
       driver.findElement(By.id("addbutton")).click();

       System.out.println("Checking Another Box");
       driver.findElement(By.name("li1")).click();

       System.out.println("Checking Another Box");
       driver.findElement(By.name("li3")).click();

       System.out.println("Checking Another Box");
       driver.findElement(By.name("li7")).click();

       System.out.println("Checking Another Box");
       driver.findElement(By.name("li8")).click();

       System.out.println("Entering Text");
       driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");

       driver.findElement(By.id("addbutton")).click();

       System.out.println("Checking Another Box");
       driver.findElement(By.name("li9")).click();

       // Let's also assert that the todo we added is present in the list.

       spanText = driver.findElementByXPath("/html/body/div/div/div/ul/li[9]/span").getText();
       Assert.assertEquals("Get Taste of Lambda and Stick to It", spanText);
       Status = "passed";
       Thread.sleep(150);

       System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}
