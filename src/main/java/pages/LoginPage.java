package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	private By emailAdressField = By.name("email");
	private By passwordField = By.name("password");
	private By loginButton = By.name("LoginForm|SubmitChanges");
	private By resetPasswordButton= By.xpath("//a[contains(text(),'Passwort vergessen?')]");
	private By invalidCredentialsAlert= By.xpath("//span[contains(normalize-space(),'Ihre Eingabedaten sind leider fehlerhaft, stimmen')]");
	private By cookieConsent = By.id("uc-btn-accept-banner");
	CommonFunctions commonfunctions;
	String alertText;
	public WebDriverWait wait;
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}

	public void setUsername(String username){
		driver.findElement(emailAdressField).sendKeys(username);
	}

	public void setPassword(String password){
		driver.findElement(passwordField).sendKeys(password);
	}



	public LoginPage acceptCookieConsent() throws InterruptedException{

		wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.elementToBeClickable(cookieConsent));
		Thread.sleep(5000);
		driver.findElement(cookieConsent).click();
		Thread.sleep(5000);
		return new LoginPage(driver);
	}
	public SecureAreaPage clickLoginButton(){
		driver.findElement(loginButton).click();
		return new SecureAreaPage(driver);
	}
	public String getAlertText(){

		commonfunctions=new CommonFunctions(driver);
		alertText=commonfunctions.getText(invalidCredentialsAlert);
		return alertText;

	}
	public ForgotPasswordPage clickForgotPasswordButton(){
		driver.findElement(resetPasswordButton).click();
		return new ForgotPasswordPage(driver);
	}
	public SecureAreaPage login(String username , String password) throws InterruptedException{
		driver.findElement(emailAdressField).sendKeys(username);
		Thread.sleep(3000);
		driver.findElement(passwordField).sendKeys(password);
		Thread.sleep(3000);
		driver.findElement(loginButton).click();
		Thread.sleep(5000);
		return new SecureAreaPage(driver);
	}


	public String getPageTitle(){

		commonfunctions=new CommonFunctions(driver);
		return commonfunctions.getPageTitle();
	}

}