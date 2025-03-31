package AutoProject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutoProject.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	//WebElement userEmails=driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage()
	{	
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
