package AutoProject.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutoProject.TestComponents.BaseTest;
import AutoProject.TestComponents.Retry;
import AutoProject.pageobjects.CartPage;
import AutoProject.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidations() throws IOException {
		// LandingPage landingPage=launchApplication();- this is not required as we have
		// declared launchApplication method with @beforemethod annotation which is
		// declared in BaseTest file
		landingPage.loginApplication("preetish@gmail.com", "assword123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidations() throws IOException {
		String productName = "ZARA COAT 3";
		// LandingPage landingPage=launchApplication();- this is not required as we have
		// declared launchApplication method with @beforemethod annotation which is
		// declared in BaseTest file
		ProductCatalogue productCatalogue = landingPage.loginApplication("preetish45@gmail.com", "Password123");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.gotoCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	
	}
	
}
