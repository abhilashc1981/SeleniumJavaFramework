package rahulShettyAcademy.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initialiseDriver() throws IOException {
		
		Properties properties = new Properties();
		
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/rahulShettyAcademy/Resources/GlobalData.properties");
		
		properties.load(fileInputStream);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				chromeOptions.addArguments("headless");
			}
			
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().setSize(new Dimension(1440, 900));//full screen
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
			});
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File temporaryScreenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File screenshot = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(temporaryScreenshot, screenshot);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws IOException {
		
		WebDriver driver = initialiseDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		driver.quit();
		
	}
}
