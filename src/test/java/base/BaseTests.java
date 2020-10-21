package base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import pages.CommonFunctions;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;

public class BaseTests {

	private WebDriver driver;
	WebDriverWait wait;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected  ForgotPasswordPage forgotPasswordPage;
	protected CommonFunctions commonfunctions;

	String actual;
	String expected;
	@BeforeSuite
	public void setUp(){

		/*----- Open browser  */

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();

		/*----- Open Douglas Web login page----  */

		driver.get("https://www.douglas.de/mydouglas/login");
		//driver.get("https://www.douglas.de");
		/* ----- Verify page title ---- */
		commonfunctions=new CommonFunctions(driver);
	
		commonfunctions.waitForLoad();

		actual =  commonfunctions.getPageTitle();
		expected = "Login - Mein Douglas";
		Assert.assertEquals(actual,expected,"page not loaded");


		loginPage = new LoginPage(driver);
		homePage=new HomePage(driver);
		forgotPasswordPage=new ForgotPasswordPage(driver);
	}

	@AfterSuite
	public void tearDown(){
		//driver.quit();
	}
}