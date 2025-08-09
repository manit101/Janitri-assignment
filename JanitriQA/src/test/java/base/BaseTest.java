package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v136.browser.Browser;
import org.openqa.selenium.devtools.v136.browser.model.PermissionType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver; 
	public String baseUrl = "https://dev-dash.janitri.in";
	
	@BeforeMethod
	public void setup() {
	    WebDriverManager.chromedriver().setup();

	    ChromeOptions options = new ChromeOptions();

	    driver = new ChromeDriver(options);

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	    driver.get(baseUrl);

	    driver.navigate().refresh();

	    DevTools devTools = ((ChromeDriver) driver).getDevTools();
	    devTools.createSession();

	    devTools.send(Browser.grantPermissions(
	        java.util.List.of(PermissionType.NOTIFICATIONS),
	        java.util.Optional.of(baseUrl),
	        java.util.Optional.empty()
	    ));
	    
	    driver.navigate().refresh();
	}
	
	@AfterMethod
	public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}