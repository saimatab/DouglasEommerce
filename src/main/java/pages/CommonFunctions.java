package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions {


	public WebDriver driver;
	public WebDriverWait wait;

	public CommonFunctions(WebDriver driver) {

		this.driver = driver;
	}

	public void  waitForElementToBeClickable(By locator, long timeoutInSeconds )
	{
		wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void  waitForPageTitle(String pageTitle , long timeoutInSeconds)
	{
		wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.titleContains(pageTitle));
	}
	public void  waitForElementVisible(By locator , long timeoutInSeconds)
	{
		wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public String getText(By locator){

		return driver.findElement(locator).getText();
	}

	public String getPageTitle(){

		return driver.getTitle();
	}

	public ExpectedCondition<Boolean> waitForLoad() {

		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};

		wait=new WebDriverWait(driver,60);
		wait.until(pageLoadCondition);
		return pageLoadCondition;
	}

}
