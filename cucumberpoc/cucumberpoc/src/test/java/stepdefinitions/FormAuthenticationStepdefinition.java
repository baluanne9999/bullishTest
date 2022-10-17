package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.FormAuthenticationPage;
import utilities.CommonUtilities;

public class FormAuthenticationStepdefinition extends CommonUtilities {

    FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage();

    @Given("I am on the internet page")
    public void i_am_on_the_internet_page() {
        try {
            logger.info("Launch the application");
            openBrowser();
            Assert.assertTrue(formAuthenticationPage.isDisplayHomePageHeader(), "Home page header is not displaying");
        } catch (InterruptedException e) {
            logger.info("Unable to Launch the application"+e.getMessage());
        }
    }

    @And("I navigate to Form Authentication page")
    public void i_navigate_to_form_authentication_page() {
        formAuthenticationPage.clickOnFormAuthenticationLink();
    }

    @Then("I verify login page")
    public void i_verify_login_page() {
        formAuthenticationPage.validateLoginPage();
    }


    @When("username and password field is empty")
    public void username_and_password_field_is_empty() {
        formAuthenticationPage.enterUsername("");
        formAuthenticationPage.enterPassword("");
    }

    @And("I click login button")
    public void i_click_login_button() {
        formAuthenticationPage.clickOnLoginButton();
    }

    @Then("I should see {string} message")
    public void i_should_see_error_message(String errorMsg) {
        formAuthenticationPage.verifyFlashMessages(errorMsg);
    }

    @When("I type {string} in username field")
    public void i_type_in_username_field(String username) {
        formAuthenticationPage.enterUsername(username);
    }

    @And("I type {string} in password field")
    public void i_type_in_password_field(String password) {
        formAuthenticationPage.enterPassword(password);
    }

    @And("I should see welcome to secure area message")
    public void i_should_see_welcome_to_secure_area_message() {
        formAuthenticationPage.verifySecureAreaMsg();
    }

    @And("I click on logout")
    public void i_click_on_logout() {
        formAuthenticationPage.logout();
    }

    @And("I close browser")
    public void i_close_browser() {
        closeBrowser();
    }

}
