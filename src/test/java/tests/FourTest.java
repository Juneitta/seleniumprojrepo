package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class FourTest extends Base {
	
	public WebDriver driver;
    @Test
	public void testFour() throws IOException, InterruptedException {
    	
    	System.out.println("Febi has updated this statement");
    	System.out.println("Febi's second updated code");		
		System.out.println("TestFour");
		
        driver = initializeDriver();
		
		driver.get("https://tutorialsninja.com/demo/");
		
		Thread.sleep(2000);
		
		Assert.assertTrue(false);
    }
    @AfterMethod
    public void tearDown() {
		
		driver.close();
		
	}
}
