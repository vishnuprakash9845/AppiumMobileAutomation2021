package qa.mobile;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class test1 {
	
	AppiumDriver driver;
	
  @Test
  public void invalidUserName() throws Exception {
	  MobileElement usernameTextField = (MobileElement)driver.findElementsByAccessibilityId("test-Username");
	  MobileElement passwordTextField = (MobileElement)driver.findElementsByAccessibilityId("test-Password");
	  MobileElement loginButton = (MobileElement)driver.findElementsByAccessibilityId("test-LOGIN");
	  
	  usernameTextField.sendKeys("invalideusername");
	  passwordTextField.sendKeys("secret_sauce");
	  loginButton.click();
	  
	  Thread.sleep(2000);
	  
	  MobileElement errorText = (MobileElement)driver.findElementsByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
	  System.out.println("Actual Error Text - "+ errorText);
	  String actualErrorText = errorText.getAttribute("text");
	  String expectedErrorText = "Username and password do not match any user in this service.";
	  
	  Assert.assertEquals(actualErrorText, expectedErrorText);
  }
  
  @Test
  public void invalidPassword() throws Exception {
	  MobileElement usernameTextField = (MobileElement)driver.findElementsByAccessibilityId("test-Username");
	  MobileElement passwordTextField = (MobileElement)driver.findElementsByAccessibilityId("test-Password");
	  MobileElement loginButton = (MobileElement)driver.findElementsByAccessibilityId("test-LOGIN");
	  
	  usernameTextField.sendKeys("standard_user");
	  passwordTextField.sendKeys("invalidPassword");
	  loginButton.click();
	  
	  Thread.sleep(2000);
	  
	  MobileElement errorText = (MobileElement)driver.findElementsByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
	  System.out.println("Actual Error Text - "+ errorText);
	  String actualErrorText = errorText.getAttribute("text");
	  String expectedErrorText = "Username and password do not match any user in this service.";
	  
	  Assert.assertEquals(actualErrorText, expectedErrorText);
  }
  
  @Test
  public void successfulLogin() throws Exception {
	  MobileElement usernameTextField = (MobileElement)driver.findElementsByAccessibilityId("test-Username");
	  MobileElement passwordTextField = (MobileElement)driver.findElementsByAccessibilityId("test-Password");
	  MobileElement loginButton = (MobileElement)driver.findElementsByAccessibilityId("test-LOGIN");
	  
	  usernameTextField.sendKeys("standard_user");
	  passwordTextField.sendKeys("secret_sauce");
	  loginButton.click();
	  
	  Thread.sleep(2000);
	  
	  MobileElement productTitle = (MobileElement)driver.findElementsByXPath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.widget.ViewGroup/android.widget.TextView");
	  System.out.println("Home Page Product Title - "+ productTitle);
	  String actualproductTitle = productTitle.getAttribute("text");
	  String expectedproductTitle = "PRODUCT";
	  
	  Assert.assertEquals(actualproductTitle, expectedproductTitle);
  }
  
  @BeforeClass
  public void beforeClass() throws Exception {
	  DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	  desiredCapabilities.setCapability("platformName", "Android");
	  desiredCapabilities.setCapability("platformVersion", "10.0");
	  desiredCapabilities.setCapability("deviceName", "any device");
	  desiredCapabilities.setCapability("automationName", "UiAutomator2");
	  desiredCapabilities.setCapability("appPackage", "com.swaglabsmobileapp");
	  desiredCapabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
	  desiredCapabilities.setCapability("app", "/Users/Om/Downloads/ApiDemos-debug.apk");

	  URL url = new URL("http://127.0.0.1:4723/wd/hub");
	  
	  driver = new AppiumDriver(url,desiredCapabilities);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  String sessionId = driver.getSessionId().toString();
	  
  }

  @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
