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



		/**---- Login Douglas Webshop ---*/
		secureAreaPage =  loginPage.login("saima.tabassum@arpatech.com","Goodluck1@");

		/**---- Validate  login successfull ---*/
		Thread.sleep(3000);
		actual =  loginPage.getPageTitle();
	
		Thread.sleep(2000);
		Assert.assertEquals(secureAreaPage.getText(),"Hallo saima Tabassum","Hello message not displayed after login");

	}
	@Test(priority=2,enabled=true)
	public void testLogout() throws InterruptedException, IOException{


		/**---- Validate logout---*/

		homePage= secureAreaPage.logout();



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


		
	/**---- Empty Entries ---*/
	

		loginPage.acceptCookieConsent();

		loginPage.loginEmptyEntries();
		Thread.sleep(3000);
		actual =  loginPage.getemptyunalert();
		expected = "E-Mail Adresse: Bitte füllen Sie das Feld aus.";
		Assert.assertEquals(actual,expected,"empty username Error not displayed");
		Thread.sleep(3000);
		
		
		String actual1 =  loginPage.getemptypwdalert();
	String 	expected1 = "Passwort: Bitte füllen Sie das Feld aus.";
		Assert.assertEquals(actual1,expected1,"empty password Error not displayed");
		Thread.sleep(3000);
		
		/**---- Give incorrect credentials---*/

		loginPage.login("saima.tabassum@arpatech.com","Goodluck1@3");


		/**---- Validate error Message on invalid Credentials ---*/
		Thread.sleep(3000);
		actual =  loginPage.getAlertText();
		expected = "Ihre Eingabedaten sind leider fehlerhaft, stimmen Benutzername und Passwort?";
		Assert.assertEquals(actual,expected,"Invalid Credentials Error not displayed");

 

	
		
		
	}
	@Test(priority=5,enabled=true)
	public void testForgotPassword() throws InterruptedException, IOException, TesseractException{


		/**----Successful navigation to Forgot Password Modal  ---*/

		loginPage.clickForgotPasswordButton();
		forgotPasswordPage.forgotPassword("saima.tabassum@arpatech.com");



	}

}
