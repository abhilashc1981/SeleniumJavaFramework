package rahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	private WebElement countryField;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	private WebElement secondCountryButton;
	
	@FindBy(css=".action__submit")
	private WebElement placeOrderButton;
	
	By countryFieldBy = By.cssSelector("[placeholder='Select Country']");
	
	public CheckOutPage enterCountry(String country) {
		waitForElementToAppear(countryFieldBy);
		countryField.sendKeys(country);
		return this;
	}
	
	public CheckOutPage selectCountry() {
		secondCountryButton.click();
		return this;
	}

	public OrderConfirmationPage placeOrder() {
		placeOrderButton.click();
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}
}
