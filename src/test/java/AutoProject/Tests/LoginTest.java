package AutoProject.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutoProject.TestComponents.BaseTest;
import AutoProject.pageobjects.CartPage;
import AutoProject.pageobjects.CheckoutPage;
import AutoProject.pageobjects.ConfirmationPage;
import AutoProject.pageobjects.OrderPage;
import AutoProject.pageobjects.ProductCatalogue;

public class LoginTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test (dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException, InterruptedException { //-using hashmap
		//public void submitOrder(String email,String password, String productName ) throws IOException {  //-using arrays
		// LandingPage landingPage=launchApplication();- this is not required as we have declared launchApplication method with @beforemethod annotation which is declared in BaseTest file
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));//-usinghasmap
		//ProductCatalogue productCatalogue = landingPage.loginApplication(email,password);//-using arrays
		List<WebElement> products = productCatalogue.getProductList();
		
		productCatalogue.addProductToCart(input.get("product"));//-suing hashmap
		//productCatalogue.addProductToCart(productName); //using arrays
		CartPage cartPage = productCatalogue.gotoCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product")); //Using hashmap
		//Boolean match = cartPage.VerifyProductDisplay(productName);//using arrays
		Assert.assertTrue(match);
		// cartPage.goToCheckout();
		// driver.findElement(By.cssSelector(".totalRow button")).click();)

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("preetish45@gmail.com", "Password123");
		OrderPage ordersPage=productCatalogue.gotoOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	

	//Using json file
	@DataProvider 
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AutoProject\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)} };
	}

	//Using hashmap
	/*@DataProvider 
	public Object[][] getData()
	{
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("email", "preetish45@gmail.com");
		map.put("password", "Password123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1= new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "ADIDAS ORIGINAL");
		
		
		return new Object[][] {{map}, {map1} };
	}*/
	
	//Using arrays
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"preetish45@gmail.com","Password123","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}*/
	
		
}

/* Note to run the tests run the Purchase.xml file under testSuites
 * The data can be passed in 2 ways from data providers the first method is using arrays for which all code named using arrays will have to be uncommented
 * Second was is using hashmap which is the default how this is implemented
 * */


 