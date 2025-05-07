package rahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement confirmationMessageHeader;
	

	By confirmationMessageBy = By.cssSelector(".hero-primary");
	
	public boolean matchConfirmationMessage(String expectedConfirmationMessage) {
		waitForElementToAppear(confirmationMessageBy);
		return confirmationMessageHeader.getText().equalsIgnoreCase(expectedConfirmationMessage);
	}
}
