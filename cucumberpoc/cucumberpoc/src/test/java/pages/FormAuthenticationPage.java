package pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.CommonUtilities;

public class FormAuthenticationPage extends CommonUtilities {

    // Locators
    // Home Page
    By ele_HomePageHeader = By.xpath("//h1[text()='Welcome to the-internet']");
    By ele_FormAuthenticationLink = By.xpath("//a[text()='Form Authentication']");

    // Form Authentication Page
    By ele_LoginHeader = By.xpath("//h2[text()='Login Page']");
    By ele_LoginInformation = By.xpath("//h4[@class='subheader']");
    By ele_UserNameLabel = By.xpath("//label[text()='Username']");
    By ele_UserNameInput = By.id("username");
    By ele_PasswordLabel = By.xpath("//label[text()='Password']");
    By ele_PasswordInput = By.id("password");
    By ele_LoginButton = By.xpath("//button[@type='submit']");

    // Login Suucess screen
    By ele_LoginFlashMsg = By.xpath("//div[@id='flash-messages']/div[@id='flash']");
    By ele_SecureMsg = By.xpath("//div[@class='example']/h2");
    By ele_WelcomeMsg = By.xpath("//h4[@class='subheader']");
    By ele_Logout = By.xpath("//a/i[contains(text(),'Logout')]");

    // Getter and setter methods
    public boolean isDisplayHomePageHeader() {
        return isDisplayElement(ele_HomePageHeader, "Home Page header");
    }

    public void clickOnFormAuthenticationLink() {
        logger.info("click on Form Authentication Link");
        clickElement(ele_FormAuthenticationLink, "Form Authentication Link");
    }

    public void validateLoginPage() {
        logger.info("Verify Login Page Header message");
        Assert.assertTrue(isDisplayElement(ele_LoginHeader, "Login Page Header message"));
        logger.info("Verify Login hint information message");
        String actualLoginInfoText = getText(ele_LoginInformation, "Login hint information message");
        String expectedLoginInfoText = properties.getProperty("logininfotext");
        Assert.assertEquals(actualLoginInfoText, expectedLoginInfoText, "Login hint information message is not displaying as expected");
        logger.info("Verify UserName label");
        Assert.assertTrue(isDisplayElement(ele_UserNameLabel, "User Name label"), "UserName label is not displaying");
        logger.info("Verify Password label");
        Assert.assertTrue(isDisplayElement(ele_PasswordLabel, "Password label"), "Password label is not displaying");
    }

    public void clickOnLoginButton() {
        logger.info("Click on login Button");
        clickElement(ele_LoginButton, "login button");
    }

    public void verifyFlashMessages(String message) {
        logger.info("Verify login error/success messages");
        Assert.assertTrue(isDisplayElement(ele_LoginFlashMsg, message + " message"), message + " message is not displaying");
        String actualMsg = getText(ele_LoginFlashMsg, message + " message");
        Assert.assertTrue(actualMsg.contains(message), message + " message is not displaying as expected");
    }

    public void enterUsername(String username) {
        logger.info("Enter user name");
        setInput(ele_UserNameInput, username, "Username field");
    }

    public void enterPassword(String password) {
        logger.info("Enter password");
        setInput(ele_PasswordInput, password, "password field");
    }

    public void verifySecureAreaMsg() {
        logger.info("Verify secure area header and welcome message");
        String actualMsg;
        actualMsg = getText(ele_SecureMsg, "Secure Area");
        Assert.assertTrue(actualMsg.contains("Secure Area"), "Secure Area header is not displaying");

        actualMsg = getText(ele_WelcomeMsg, "Welcome to secure area Message");
        Assert.assertTrue(actualMsg.contains("Welcome to the Secure Area. When you are done click logout below."), "Welcome to secure area message is not displaying");
    }

    public void logout() {
        logger.info("Click on logout button");
        clickElement(ele_Logout, "logout button");
    }

}
