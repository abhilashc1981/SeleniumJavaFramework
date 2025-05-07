package rahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory Design Pattern
	@FindBy(id="userEmail")
	WebElement userEmailField;
	
	@FindBy(id="userPassword")
	WebElement passwordField;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public LandingPage goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		return this;
	}
	
	public ProductCatalog login(String email, String password) {
		userEmailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
