package rahulShettyAcademy.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.pageobjects.CartPage;
import rahulShettyAcademy.pageobjects.CheckOutPage;
import rahulShettyAcademy.pageobjects.LandingPage;
import rahulShettyAcademy.pageobjects.OrderConfirmationPage;
import rahulShettyAcademy.pageobjects.OrdersPage;
import rahulShettyAcademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	
	@Test(dataProvider="getData")
	public void addProductToCartTest(HashMap<String, String> input) throws IOException {
		
		ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("password"));
		
		productCatalog.addProductToCart(input.get("product"));
		CartPage cart = productCatalog.goToCartPage();
		
		Assert.assertTrue(cart.matchProductByName(input.get("product")));
		
	}

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) 
			throws IOException {

		ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("password"));
		
		productCatalog.addProductToCart(input.get("product"));
		CartPage cart = productCatalog.goToCartPage();
		
		CheckOutPage checkOutPage = cart.checkOut();
		
		checkOutPage.enterCountry(input.get("country")).selectCountry();
		OrderConfirmationPage orderConfirmationPage = checkOutPage.placeOrder();
		
		Assert.assertTrue(orderConfirmationPage.matchConfirmationMessage(input.get("confirmationMessage")));
		
		//String orderId = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText().replace("|", "").trim();*/
	}
	
	@Test(dependsOnMethods= {"submitOrder"}, dataProvider="getData")
	public void orderHistoryTest(HashMap<String, String> input) {
		ProductCatalog productCatalog = landingPage.login(input.get("email"), input.get("password"));
		OrdersPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.matchOrder(input.get("product")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") 
				+ "/src/test/java/rahulShettyAcademy/data/PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
		
	}
}
