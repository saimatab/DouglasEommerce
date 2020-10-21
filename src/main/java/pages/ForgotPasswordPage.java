package pages;

import java.io.IOException;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import pages.HomePage;
import pages.CommonFunctions;

public class ForgotPasswordPage {
	CommonFunctions commonfunctions;
	private WebDriver driver;
	WebDriverWait wait;
	String alertText;
	private By email= By.xpath("//div[@class='rd__modal-content__body__container']//div[@class='rd__my-douglas-modal']//div[@class='rd__lost-password-form']//input[@name='email']");

	private By code = By.xpath("//div[@class='rd__modal-content__body__container']//div[@class='rd__my-douglas-modal']//div[@class='rd__lost-password-form__capture']//div[@class='rd__captcha__input']//input[@name='captcha']");

	private By sendEmail = By.xpath("//div[@class='rd__modal-content__footer']//button[@name='LoginForm|SubmitChanges']");
	private By shutdown = By.xpath("//div[@class='rd__modal-content__footer']//button[@data-ui-name='closeModal']");
	private By img = By.xpath("//div[@class='rd__modal-content__body__container']//img[@class='rd__img']");
	private By emailSentThanksAlert = By.xpath("//div[@class='rd__modal']//font[contains(text(),'Email sent')]");
	WebElement image;
	ITesseract tesseract;
	String path;
	String captchaCode;


	public ForgotPasswordPage(WebDriver driver){
		this.driver = driver;
	}


	public WebElement getEmail()


	{
		return driver.findElement(email);
	}


	public WebElement getimg()


	{
		return driver.findElement(img);
	}


	public WebElement getThanksEmailSentAlert()


	{
		return driver.findElement(emailSentThanksAlert);
	}
	public WebElement getSendEmail()


	{
		return driver.findElement(sendEmail);
	}
	public WebElement getCode()


	{
		return driver.findElement(code);
	}
	public WebElement getShutDown()


	{
		return driver.findElement(shutdown);
	}
	public String getAlertText(){
		commonfunctions=new CommonFunctions(driver);
		alertText=commonfunctions.getText(emailSentThanksAlert);
		return alertText;

	}
	public void forgotPassword(String email ) throws IOException, InterruptedException, TesseractException
	{
		try {
			wait = new WebDriverWait(driver, 120);
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(getEmail()));
			
			getEmail().sendKeys(Keys.CONTROL);

			getEmail().sendKeys(Keys.BACK_SPACE);
			Thread.sleep(5000);
			getEmail().clear();
			getEmail().sendKeys(email);

			////
			tesseract = new Tesseract(); 

			Thread.sleep(3000);
			image=getimg();

			Thread.sleep(3000);
			TakesScreenshot scrShot =((TakesScreenshot)image);
			File src=scrShot.getScreenshotAs(OutputType.FILE);

			//Thread.sleep(5000);
			path=System.getProperty("user.dir")+"/screenshots/captcha.png";

			Thread.sleep(3000);

			tesseract.setDatapath("src/tessdata");
			

			Thread.sleep(3000);
			FileUtils.copyFile(src,new File(path));

			Thread.sleep(3000);

			captchaCode=tesseract.doOCR( new File(path));

			//captchaCode=tesseract.doOCR( new File("D:\\captcha.png"));
			Thread.sleep(3000);
			System.out.println("final captcha = "+captchaCode);

			if (!captchaCode.isEmpty())
			{
				Thread.sleep(3000);


				getCode().sendKeys(captchaCode);
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(getSendEmail()));
				getSendEmail().click();
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(getShutDown()));
				getShutDown().click();
				Thread.sleep(3000);

			}


			else
			{
				Thread.sleep(3000);
				
				captchaCode=tesseract.doOCR( new File("captcha.png"));
				
				Thread.sleep(3000);


				getCode().sendKeys(captchaCode);
				Thread.sleep(3000);

				System.out.println("OCR Not able to recognize the code");

			}
		}


		catch(TesseractException e)
		{

			System.out.print(e);
		}
	}











}
