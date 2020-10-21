package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class HomePage {

	private By meinDouglasMenu = By.xpath("//div[@class='rd__col rd__col--sm-12']//div[@class='rd__nav-mydouglas']");
	
	

	WebElement logoff;
	
	private By loginFromHome = By.xpath("//a[contains(normalize-space(),'Einloggen')]");
	
	//private By loginFromHome = By.xpath("//div[@class='rd__col rd__col--sm-12']//div[@class='rd__nav-mydouglas']//div[@class='rd__flyout rd__flyout--mydouglas']//div[@class='rd__col rd__col--sm-6'][1]//font[contains(text(),'log in')]");
	public WebDriverWait wait;
	private WebDriver driver;
	CommonFunctions commonfunctions;

	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}

	

	public WebElement getMainDouglasMenu()


	{
		return driver.findElement(meinDouglasMenu);
	}

	public WebElement getLoginFromHome()


	{
		return driver.findElement(loginFromHome);
	}
	public LoginPage navigateToSignInPage() throws InterruptedException{

		wait = new WebDriverWait(driver, 180);
	
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(getMainDouglasMenu()));
		Actions actions = new Actions(driver);
		actions.moveToElement(getMainDouglasMenu());
		actions.perform();	 
		Thread.sleep(8000);

		wait.until(ExpectedConditions.elementToBeClickable(getLoginFromHome()));
		Thread.sleep(8000);
		WebElement login= getLoginFromHome();
		login.click();
		Thread.sleep(5000);  
		return new LoginPage(driver);
	}
	
public String getPageTitle(){
		
		commonfunctions=new CommonFunctions(driver);
		return commonfunctions.getPageTitle();
	}
}