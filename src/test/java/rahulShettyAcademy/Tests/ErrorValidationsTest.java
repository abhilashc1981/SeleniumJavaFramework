package rahulShettyAcademy.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.TestComponents.Retry;
import rahulShettyAcademy.pageobjects.CartPage;
import rahulShettyAcademy.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {

		landingPage.login("abhilashc24@gmail.com", "aZYb12xC");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		
		String productToAddToCart = "ZARA COAT 3";
		String productToSearchInCart = "ZARA COAT 33";

		ProductCatalog productCatalog = landingPage.login("abhilashc24@gmail.com", "aZYb)(12xC");
		
		productCatalog.addProductToCart(productToAddToCart);
		CartPage cart = productCatalog.goToCartPage();
		
		Assert.assertFalse(cart.matchProductByName(productToSearchInCart));
	}

}
