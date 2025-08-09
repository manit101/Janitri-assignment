package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        Assert.assertFalse(loginPage.isLoginButtonEnabled());
        // Here I think this is a bug that the login button is enabled even when fields are empty
        // Using assertFalse is failing the test case
    	
    	loginPage.clickLoginButton();

        String error = loginPage.getErrorMessage();
        System.out.println(error);

        Assert.assertEquals(error, "Invalid Credentials");
    }

    @Test
    public void testPasswordMaskedButton() {
        loginPage.enterPassword("demoPassword");
        Assert.assertTrue(loginPage.isPasswordMasked());

        loginPage.togglePasswordVisibility();
        Assert.assertFalse(loginPage.isPasswordMasked());
    }
    
    
    @Test
    public void testInvalidLoginShowErrorMsg() {
        loginPage.enterUserId("demoUserId");
        loginPage.enterPassword("demoUserPassword");
        loginPage.clickLoginButton();

        String error = loginPage.getErrorMessage();
        System.out.println(error);

        Assert.assertEquals(error, "Invalid Credentials");
    }
    // Tried a lot but that 'enable notification div' was creating a mess
    // Really want to learn how to handle this case, searched on web but can't fix this particular case
}