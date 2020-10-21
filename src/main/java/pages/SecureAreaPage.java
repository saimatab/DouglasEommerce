package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.HomePage;
import pages.CommonFunctions;

public class SecureAreaPage {

	private WebDriver driver;
	WebDriverWait wait;
	private By statusAlert = By.xpath("//div[@class='rd__my-douglas-welcome__headline']//span[@class='rd__headline-bold rd__headline-bold--large']");
	CommonFunctions commonfunctions;
	String alertText;
	private By meinDouglasMenu = By.xpath("//div[@class='rd__col rd__col--sm-12']//div[@class='rd__nav-mydouglas rd__nav-mydouglas--loggedin']");
	WebElement logoff;
	private By logout = By.xpath("//div[@class='rd__flyout rd__flyout--mydouglas']//ul[2]/li[1]/a[1]");
	private By loginFromHome = By.xpath("//div[@class='rd__nav-mydouglas']//div[@class='rd__flyout rd__flyout--mydouglas']//div[@class='rd__col rd__col--sm-6'][1]//font[contains(text(),'log in')]");
	//div[@class='rd__nav-mydouglas']//div[@class='rd__flyout rd__flyout--mydouglas']//div[@class='rd__col rd__col--sm-6'][1]

	public SecureAreaPage(){

	}

	public SecureAreaPage(WebDriver driver){
		this.driver = driver;
	}

	public String getText(){
		commonfunctions=new CommonFunctions(driver);
		alertText=commonfunctions.getText(statusAlert);
		return alertText;

	}
	public WebElement getMainDouglasMenu()


	{
		return driver.findElement(meinDouglasMenu);
	}
	public WebElement getLogout()


	{
		return driver.findElement(logout);
	}

	public HomePage logout() throws IOException, InterruptedException
	{
		HomePage hp=new HomePage(driver);
		wait = new WebDriverWait(driver, 120);
		Thread.sleep(10000);
		wait.until(ExpectedConditions.elementToBeClickable(getMainDouglasMenu()));
		Actions actions = new Actions(driver);
		actions.moveToElement(getMainDouglasMenu());
		actions.perform();	 
		Thread.sleep(5000);

		wait.until(ExpectedConditions.elementToBeClickable(getLogout()));
		Thread.sleep(10000);
		WebElement logoff= getLogout();
		logoff.click();
		Thread.sleep(5000);  
		return new HomePage(driver);
	}
}
