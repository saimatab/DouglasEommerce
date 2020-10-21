package login;

import base.BaseTests;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.testng.annotations.Test;

import pages.CommonFunctions;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.SecureAreaPage;
import pages.HomePage;
import static org.testng.Assert.*;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginTests extends BaseTests {

	SecureAreaPage secureAreaPage;

	//LoginPage loginPage;
	CommonFunctions commonfunctions;
	String actual, expected;
	//HomePage homePage;

	@Test(priority=1,enabled=true)
	public void testSuccessfulLogin() throws InterruptedException{


		/**---- Accept Cookie Consent ---*/

		loginPage.acceptCookieConsent();

		/**---- Login Douglas Webshop ---*/
		secureAreaPage =  loginPage.login("saima.tabassum@arpatech.com","Goodluck1@");

		/**---- Validate  login successfull ---*/
		Thread.sleep(3000);
		actual =  loginPage.getPageTitle();
		expected = "Parfümerie Douglas - Parfüm, Kosmetik, Pflege, Make-up, Düfte und Beauty-Trends bei douglas.de";
		Assert.assertEquals(actual,expected,"Login not successful ");
		Thread.sleep(2000);
		Assert.assertEquals(secureAreaPage.getText(),"Hallo saima Tabassum","Hello message not displayed after login");

	}
	@Test(priority=2,enabled=true)
	public void testLogout() throws InterruptedException, IOException{


		/**---- Validate logout---*/

		homePage= secureAreaPage.logout();


		/**---- Validate Sucessful logout ---*/
		Thread.sleep(3000);
		actual =  homePage.getPageTitle();
		expected = "Online-Parfümerie » Parfum & Kosmetik kaufen | DOUGLAS";
		Assert.assertEquals(actual,expected,"home page not displayed");
		Thread.sleep(3000);

	}
	@Test(priority=3,enabled=true)
	public void testNavigateToSignIn() throws InterruptedException, IOException{


		/**---- Navigate to Login page---*/

		loginPage= homePage.navigateToSignInPage();


		/**---- Validate Successful navigation to Login page ---*/
		Thread.sleep(3000);

		actual =  loginPage.getPageTitle();
		expected = "Login - Mein Douglas";
		Assert.assertEquals(actual,expected,"Login page not loaded");
		Thread.sleep(3000);
	}
	@Test(priority=4,enabled=true)
	public void testInvalidLoginCredentials() throws InterruptedException, IOException{


		/**---- Give incorrect credentials---*/

		loginPage.login("saima.tabassum@arpatech.com","Goodluck1@3");


		/**---- Validate error Message on invalid Credentials ---*/
		Thread.sleep(3000);
		actual =  loginPage.getAlertText();
		expected = "Ihre Eingabedaten sind leider fehlerhaft, stimmen Benutzername und Passwort?";
		Assert.assertEquals(actual,expected,"Invalid Credentials Error not displayed");
		Thread.sleep(3000);
	}
	@Test(priority=5,enabled=true)
	public void testForgotPassword() throws InterruptedException, IOException, TesseractException{


		/**----Successful navigation to Forgot Password Modal  ---*/

		loginPage.clickForgotPasswordButton();
		forgotPasswordPage.forgotPassword("saima.tabassum@arpatech.com");



	}

}
