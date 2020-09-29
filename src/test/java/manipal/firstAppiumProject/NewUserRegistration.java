package manipal.firstAppiumProject;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class NewUserRegistration {
	
	AndroidDriver driver;
	
	@BeforeTest
	public void setUp() throws MalformedURLException, InterruptedException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Anitha");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		driver.get("http://demowebshop.tricentis.com/");
		
	}
	
	@Test
	public void userRegistration() throws InterruptedException {
	
		//driver.findElement(By.linkText("Register")).click();
		driver.findElement(MobileBy.xpath("//*[@href='/register']")).click();
		WebDriverWait wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Register')]")));
		driver.findElement(MobileBy.xpath("//*[@value='F']")).click();
		driver.findElement(MobileBy.xpath("//input[@id='FirstName']")).sendKeys("Anitha");
		driver.findElement(MobileBy.xpath("//input[@id='LastName']")).sendKeys("Rao");
		driver.findElement(MobileBy.xpath("//input[@id='Email']")).sendKeys("qwe.rty5671@gmail.com");
		driver.findElement(MobileBy.xpath("//input[@id='Password']")).sendKeys("password123$");
		driver.findElement(MobileBy.xpath("//input[@id='ConfirmPassword']")).sendKeys("password123$");
		driver.findElement(MobileBy.xpath("//input[@value='Register']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(MobileBy.xpath("//*[contains(text(),'Your registration completed') ]")).isDisplayed(), "Registration successfull");
		driver.findElement(MobileBy.xpath("//a[contains(text(),'Log out') ]")).click();

}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
}
