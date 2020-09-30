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
import manipal.firstAppiumProject.lib.ExcelOperations;

public class NewUserRegistration {
	
	AndroidDriver driver;
	ExcelOperations excelObject = new ExcelOperations(".\\Test Data\\User Details.xlsx");
	
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
		String fname = excelObject.getData(0, 0, 0);
		String lname = excelObject.getData(0, 0, 1);
		String email = excelObject.getData(0, 0, 2);
		String password = excelObject.getData(0, 0, 3);
		driver.findElement(MobileBy.xpath("//*[@value='F']")).click();
		driver.findElement(MobileBy.xpath("//input[@id='FirstName']")).sendKeys(fname);
		driver.findElement(MobileBy.xpath("//input[@id='LastName']")).sendKeys(lname);
		driver.findElement(MobileBy.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(MobileBy.xpath("//input[@id='Password']")).sendKeys(password);
		driver.findElement(MobileBy.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
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
