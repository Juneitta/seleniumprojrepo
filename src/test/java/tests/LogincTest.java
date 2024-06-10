package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LogincTest extends Base {

	Logger log;
	
	public WebDriver driver;

	@Test(dataProvider="getLoginData")
	public void login(String emailid, String password, String expectedResult) throws IOException, InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingPage.loginOption().click();
		log.debug("Clicked on login option");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailField().sendKeys(emailid);
		log.debug("Email addressed got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");

		AccountPage accountPage = new AccountPage(driver);
		String actualResult = null;
		try {
			
	          if(accountPage.editYourAccountInformation().isDisplayed()) {
	        	  log.debug("User got logged in");
			      actualResult = "Success";
	          }
		}catch(Exception e) {
			log.debug("User didn't log in");
			actualResult = "Failure";
		}
       
		Assert.assertEquals(actualResult,expectedResult);
		log.info("Login Test got passed");
	
	}
	@BeforeMethod
	public void openApp() throws IOException {
		
		log = LogManager.getLogger(LogincTest.class.getName());
		
		driver = initializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");

	}

	@AfterMethod
	public void closure() {

		driver.close();
		log.debug("Browser got closed");
	}

	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = {{"tabi@gmail.com","dhiv","Success"},{"dummy@test.com","1234","Failure"}};
        return data;
	}

}
