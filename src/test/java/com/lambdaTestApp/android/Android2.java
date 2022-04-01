package com.lambdaTestApp.android;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Android2 extends AppUploadAndroid {

        String userName = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        private String Status = "failed";  
        @Test
        public void basicTest() throws IOException,
                InterruptedException {
                upload();
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("platformName", "Android");
                caps.setCapability("deviceName", "Google Pixel 4");
                caps.setCapability("platformVersion", "11");
                caps.setCapability("isRealMobile", true);
                caps.setCapability("build", "Android");
                caps.setCapability("name", "Single Test");
                caps.setCapability("app", "android_appurl");

                AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                                new URL("https://" + userName + ":" + accessKey + "@beta-hub.lambdatest.com/wd/hub"),
                                caps);
                WebDriverWait wait = new WebDriverWait(driver, 10);
                AndroidElement searchElement = (AndroidElement) wait
                                .until(ExpectedConditions
                                                .elementToBeClickable(MobileBy.AccessibilityId("Search Wikipedia")));
                searchElement.click();
                AndroidElement insertTextElement = (AndroidElement) wait
                                .until(ExpectedConditions.elementToBeClickable(
                                                MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
                insertTextElement.sendKeys("LambdaTest");
                Thread.sleep(5000);

                List<AndroidElement> allProductsName = driver.findElementsByClassName("android.widget.TextView");
                assert (allProductsName.size() > 0);

                
                Status = "passed";
                driver.executeScript("lambda-status=" + Status);
                // The driver.quit statement is required, otherwise the test continues to
                // execute, leading to a timeout.
                driver.quit();
        }
}
