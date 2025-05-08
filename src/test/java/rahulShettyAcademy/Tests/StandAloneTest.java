package rahulShettyAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new comments are added
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("abhilashc24@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("aZYb)(12xC");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		String requiredProduct = "ZARA COAT 3";
		products.stream().filter(product -> product.findElement(By.tagName("b")).getText().equals(requiredProduct)).findFirst().orElse(null)
			.findElement(By.cssSelector(".w-10")).click();
		
		
		/*for (WebElement product : products) {
			if (product.getText().contains("ZARA COAT 3")) {
				product.findElement(By.cssSelector(".w-10")).click();
				break;
			}
		}*/
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Assert.assertTrue(cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(requiredProduct)));
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions actions = new Actions(driver);
		actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
		
		/*driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("IND");
		
		List<WebElement> countries = driver.findElements(By.cssSelector("button .ng-star-inserted"));
		
		countries.stream().filter(country -> country.getText().equals(" India")).findFirst().orElse(null).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String orderId = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText().replace("|", "").trim();*/
		
		// India
	}

}
