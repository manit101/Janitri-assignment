package pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By userIdInput = By.id("formEmail"); 
    private By passwordInput = By.id("formPassword"); 
    private By loginButton = By.className("login-button"); 
    private By maskToggle = By.className("passowrd-visible"); 
    private By errorMessage = By.className("normal-text"); 
    
    // Added hard wait (Thread.sleep()) to handle the notification permission div

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUserId(String userId) throws InterruptedException {
    	 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(userIdInput));
         element.sendKeys(userId);
         Thread.sleep(2000);
    }

    public void enterPassword(String password) throws InterruptedException {
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        element.sendKeys(password);
        Thread.sleep(2000);
    }

    public void clickLoginButton() throws InterruptedException {
    	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        element.click();
        Thread.sleep(2000);
    }

    public boolean isLoginButtonEnabled() {
    	 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
         return element.isEnabled();
    }

    public void togglePasswordVisibility() {
    	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(maskToggle));
        element.click();
    }

    public String getErrorMessage() {
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return element.getText();
    }

    public boolean isPasswordMasked() {
    	 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
         String type = element.getAttribute("type");
         return type.equals("password");
    }
}
