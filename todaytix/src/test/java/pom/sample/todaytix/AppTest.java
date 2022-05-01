package pom.sample.todaytix;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
	private WebDriver driver;

	@BeforeSuite
	public void initDriver() throws Exception {
		System.out.println("You are testing in firefox");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	@BeforeTest
	public void launchingUrl() {
		String baseUrl = "https://www.todaytix.com/";
		// launch chrome and direct it to the Base URL
		driver.get(baseUrl);

		// Mazimize current window
		driver.manage().window().maximize();
	}

	@Test
	public void verifyTitleOfLandingPage() {

		String expectedTitle = "TodayTix | Theater Tickets to Musicals, Plays, Broadway, More";
		String actualTitle = "";

		// get the actual value of the title
		actualTitle = driver.getTitle();

		/*
		 * compare the actual title of the page with the expected one and print the
		 * result as "Passed" or "Failed"
		 */
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test Passed!");
		} else {
			System.out.println("Test Failed");
		}

	}

	@Test
	public void verifyLogin() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("navBarLoginButton")).click();
		// driver.switchTo().frame("ssIFrame_google");
		driver.findElement(By.xpath("//span[text()='Email']")).click();
		driver.findElement(By.id("sign-in-email-field")).sendKeys("todaytixttg@gmail.com");// Please Enter your Email here
																								
		driver.findElement(By.id("sign-in-password-field")).sendKeys("Password1$");// Please Enter your Password here
		driver.findElement(By.id("sign-in-submit")).click();

		// driver.findElement(By.id("login-go-back")).click();

		String expectedLandingPage = "Welcome back,";
		String actualLandingPage = driver.findElement(By.xpath("//a[contains(text(),'Welcome back')]")).getText();

		// compare the landing page after login of the page with the expected one and
		// print the result as "Passed" or "Failed"

		if (actualLandingPage.contentEquals(expectedLandingPage)) {
			System.out.println("Test Passed!");
		} else {
			System.out.println("Test Failed");
		}

		driver.findElement(By.xpath("//a[contains(text(),'Welcome back')]")).click();
		driver.findElement(By.xpath("//li[contains(text(),\"Log out\")]")).click();

	}

	@Test
	public void verifyNegativeLogin() {
		driver.findElement(By.id("navBarLoginButton")).click();
		// driver.switchTo().frame("ssIFrame_google");
		driver.findElement(By.xpath("//span[text()='Email']")).click();
		driver.findElement(By.id("sign-in-email-field")).sendKeys("test@gmail.com");// Please Enter your User name here

		driver.findElement(By.id("sign-in-submit")).click();

		String expectedLoginError = "Please enter a password at least 8 characters long";
		String actualLoginError = driver.findElement(By.xpath("//p[@id='sign-in-password-field-helper-text']"))
				.getText();

		/*
		 * compare the error message of the page with the expected one and print the
		 * result as "Passed" or "Failed"
		 */
		if (actualLoginError.contentEquals(expectedLoginError)) {
			System.out.println("Test Passed!");
		} else {
			System.out.println("Test Failed");
		}

	}

	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}
}