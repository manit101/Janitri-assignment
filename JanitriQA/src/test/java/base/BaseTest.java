package base;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

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
	public void setup() throws InterruptedException {
	    WebDriverManager.chromedriver().setup();

	    ChromeOptions options = new ChromeOptions();

	    driver = new ChromeDriver(options);

	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	    DevTools devTools = ((ChromeDriver) driver).getDevTools();
	    devTools.createSession();
	    devTools.send(Browser.grantPermissions(
	        List.of(PermissionType.NOTIFICATIONS),
	        Optional.of(baseUrl),
	        Optional.empty()
	    ));
	    
	    // Added hard wait (Thread.sleep()) and page refresh to handle the notification permission div
	    driver.get(baseUrl);
	   
	    Thread.sleep(5000);
	    
	    driver.navigate().refresh();

	    Thread.sleep(5000);
	}
	
	@AfterMethod
	public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
