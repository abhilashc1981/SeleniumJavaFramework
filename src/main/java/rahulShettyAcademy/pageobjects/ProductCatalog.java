package rahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory Design Pattern
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="#toast-container")
	WebElement toastMessage;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector(".w-10");
	By toastMessageBy = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement requiredProduct = products.stream().filter(product -> product.findElement(By.tagName("b")).getText()
			.equals(productName)).findFirst().orElse(null);
		return requiredProduct;
	}
	
	public ProductCatalog addProductToCart(String productName) {
		WebElement requiredProduct = getProductByName(productName);
		requiredProduct.findElement(addToCartBy).click();
		waitForElementToAppear(toastMessageBy);
		waitForElementToDisappear(spinner);
		waitForElementToDisappear(toastMessage);
		return this;
	}
}
