package com.lambdatest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

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

    public class Android1 {

        String userName = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        private String Status = "failed";
        String buildName = System.getenv("LT_BUILD_NAME");
        @Test
        public void basicTest() throws IOException, InterruptedException {

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", "Galaxy S21");
            caps.setCapability("isRealMobile", true);
            caps.setCapability("platformVersion","11");
            caps.setCapability("platformName", "Android");
            caps.setCapability("build", buildName);
            caps.setCapability("name", "Single Test");
            caps.setCapability("app", "android_appurl");
            caps.setCapability("visual", true);


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


