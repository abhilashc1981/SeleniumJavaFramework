package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	By cartProductsBy = By.cssSelector(".cartSection h3");
	
	public List<WebElement> getCartProductList() {
		waitForElementToAppear(cartProductsBy);
		return cartProducts;
	}
	
	public boolean matchProductByName(String productName) {
		return cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}

	public CheckOutPage checkOut() {
		checkOutButton.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
