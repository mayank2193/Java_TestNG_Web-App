package com.lambdaTestApp.iOS;

import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class iOS1 extends AppUpload {
        public static String username = System.getenv("LT_USERNAME");
        public static String accessKey = System.getenv("LT_ACCESS_KEY");
        private String Status = "failed";  

        @Test
        public void basicTest() throws IOException, InterruptedException {
                upload1();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("build", "iOSAppAutomation_LT");
                capabilities.setCapability("name", "iOS_Test");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("deviceName", "iPhone 13 Mini");
                capabilities.setCapability("isRealMobile", true);
                capabilities.setCapability("platformVersion", "15.0");
                capabilities.setCapability("console", true);
                capabilities.setCapability("visual", true);
                capabilities.setCapability("app", "iOS_appurl");

                IOSDriver<IOSElement> driver = new IOSDriver<IOSElement>(
                                new URL("https://" + username + ":" + accessKey + "@beta-hub.lambdatest.com/wd/hub"),
                                capabilities);

                IOSElement Checkbox = (IOSElement) new WebDriverWait(driver, 30).until(
                                ExpectedConditions.elementToBeClickable(MobileBy.xpath(
                                                "//XCUIElementTypeOther[@name=\"Sample page - lambdatest.com\"]/XCUIElementTypeOther[3]/XCUIElementTypeOther[1]/XCUIElementTypeSwitch")));
                Checkbox.click();

                IOSElement Checkbox1 = (IOSElement) new WebDriverWait(driver, 30).until(
                                ExpectedConditions.elementToBeClickable(MobileBy.xpath(
                                                "//XCUIElementTypeOther[@name=\"Sample page - lambdatest.com\"]/XCUIElementTypeOther[3]/XCUIElementTypeOther[2]/XCUIElementTypeSwitch")));
                Checkbox1.click();

                IOSElement Checkbox2 = (IOSElement) new WebDriverWait(driver, 30).until(
                                ExpectedConditions.elementToBeClickable(MobileBy.xpath(
                                                "//XCUIElementTypeOther[@name=\"Sample page - lambdatest.com\"]/XCUIElementTypeOther[3]/XCUIElementTypeOther[3]/XCUIElementTypeSwitch")));
                Checkbox2.click();

                IOSElement Checkbox3 = (IOSElement) new WebDriverWait(driver, 30).until(
                                ExpectedConditions.elementToBeClickable(MobileBy.xpath(
                                                "//XCUIElementTypeOther[@name=\"Sample page - lambdatest.com\"]/XCUIElementTypeOther[3]/XCUIElementTypeOther[4]/XCUIElementTypeSwitch")));
                Checkbox3.click();

                IOSElement Checkbox4 = (IOSElement) new WebDriverWait(driver, 30).until(
                                ExpectedConditions.elementToBeClickable(MobileBy.xpath(
                                                "//XCUIElementTypeOther[@name=\"Sample page - lambdatest.com\"]/XCUIElementTypeOther[3]/XCUIElementTypeOther[5]/XCUIElementTypeSwitch")));
                Checkbox4.click();

                IOSElement Checkbox5 = (IOSElement) new WebDriverWait(driver, 30).until(
                                ExpectedConditions.elementToBeClickable(MobileBy.xpath(
                                                "//XCUIElementTypeOther[@name=\"Sample page - lambdatest.com\"]/XCUIElementTypeOther[4]/XCUIElementTypeTextField")));
                Checkbox5.sendKeys("Sixth Item");

                Thread.sleep(5000);

                IOSElement Checkbox6 = (IOSElement) new WebDriverWait(driver, 30).until(
                                ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("add")));
                Checkbox6.click();
                Status = "passed";
                driver.executeScript("lambda-status=" + Status);
                // The driver.quit statement is required, otherwise the test continues to
                // execute, leading to a timeout.
                driver.quit();
        }
}
