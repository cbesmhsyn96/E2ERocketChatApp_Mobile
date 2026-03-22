package org.example;

import com.thoughtworks.gauge.*;
import com.thoughtworks.gauge.datastore.*;

import helpers.SeleniumHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class StepImplementation extends BaseTest {
    private static final Logger logger = LogManager.getLogger(StepImplementation.class);

    @Step("Wait until the element with key <searchedKey> is visible")
    public void waitUntilVisibleElementBySearchedKey(String searchedKey) throws MalformedURLException, URISyntaxException {
        seleniumHelper.waitElementWithVisible(searchedKey);
        logger.info("Waited until the element with key '{}' is visible", searchedKey);
    }

    @Step("Wait until the element with key <searchedKey> is presence")
    public void waitUntilPresenceElementBySearchedKey(String searchedKey){
        seleniumHelper.waitElementWithPresence(searchedKey);
        logger.info("Waited until the element with key '{}' is presence", searchedKey);
    }

    @Step("Wait until the element with key <searchedKey> is clickable")
    public void waitUntilClickableElementBySearchedKey(String searchedKey){
        seleniumHelper.waitElementWithClickable(searchedKey);
        logger.info("Waited until the element with key '{}' is clickable", searchedKey);
    }

    @Step("Verify that the element with key <searchedKey> is displayed")
    public void verifyElementBySearchedKey(String searchedKey) throws URISyntaxException, MalformedURLException {
        seleniumHelper.waitElementWithVisible(searchedKey);
        Assertions.assertTrue(seleniumHelper.isDisplayElement(searchedKey));
        logger.info("Verified that the element with key '{}' is displayed", searchedKey);
    }

    @Step("Verify that the element with key <searchedKey> contains <text> in attribute <attribute>")
    public void verifyAttributeValBySearchedKey(String searchedKey,String attribute, String text) throws URISyntaxException, MalformedURLException {
        Assertions.assertTrue(seleniumHelper.isContaionsValueOfElement(searchedKey,attribute,text));
        logger.info("Verified that the element with key '{}' contains '{}' in attribute '{}'", searchedKey, text, attribute);
    }

    @Step("Verify that the element with key <searchedKey> contains <text>")
    public void verifyTextBySearchedKey(String searchedKey,String text) throws URISyntaxException, MalformedURLException {
        Assertions.assertTrue(seleniumHelper.isContaionsTextOfElement(searchedKey,text));
        logger.info("Verified that the element with key '{}' contains '{}'", searchedKey, text);
    }

    @Step("Type into the element with key <searchedKey> the text <text>")
    public void typeTextToElementByKey(String searchedKey, String text){
        seleniumHelper.sendKeyToElement(searchedKey,text);
        logger.info("Typed '{}' into the element with key '{}'", text, searchedKey);
    }

    @Step("Fill the sign up screen with generated user data")
    public void fillSignUpScreen() throws Exception {
        String fName = user.getFirstName();
        String lName = user.getLastName();
        //String userName = user.getUserName();
        String email = "testuser" + user.getEmail();
        String userName = "user" + fName+"_"+lName;
        String password = user.getPassword();
        String passwordVerifyInput = password;
        scrollToElementByKey("register-view-name");
        typeTextToElementByKey("SignUpScreenFullNameInput",fName+" "+lName);
        typeTextToElementByKey("SignUpScreenUserNameInput",userName);
        typeTextToElementByKey("SignUpScreenEmailInput",email);
        typeTextToElementByKey("SignUpScreenPasswordInput",password);
        scrollToElementByKey("register-view-confirm-password");
        typeTextToElementByKey("SignUpScreenPasswordVerifyInput",passwordVerifyInput);
        ScenarioDataStore.put("savedName",fName);
        ScenarioDataStore.put("savedFullName",fName+" "+lName);
        ScenarioDataStore.put("savedUsername",userName);
        ScenarioDataStore.put("savedEmail",email);
        ScenarioDataStore.put("savedPassword",password);
        logger.info("Sign up form filled with generated data. FullName: {}, Username: {}, Email: {}", fName + " " + lName, userName, email);
    }

    @Step("Save created user to Excel")
    public void saveCreatedUser() throws Exception {

        String email = ScenarioDataStore.get("savedEmail").toString();
        String password = ScenarioDataStore.get("savedPassword").toString();
        String userName = ScenarioDataStore.get("savedUsername").toString();

        ExcelUtils.addUser("users.xlsx", email, userName, password);

        logger.info("User saved to Excel: {}", email);
    }

    @Step("Sign in with saved user data")
    public void fillSignInScreen(){
        String savedUserN = ScenarioDataStore.get("savedEmail").toString();
        String savedPassword = ScenarioDataStore.get("savedPassword").toString();
        typeTextToElementByKey("LoginScreenUserNameOrEmail",savedUserN);
        typeTextToElementByKey("LoginScreenPassword",savedPassword);
        logger.info("Sign-in form filled with saved user data. Email: {}, Password: [HIDDEN]", savedUserN);
    }

    @Step("Fill the email field with the signed-in user's email")
    public void fillConfirmEmailLoginScreen(){
        String email = ScenarioDataStore.get("savedEmail").toString();
        typeTextToElementByKey("LoginScreenEmailConfirmationArea",email);
        logger.info("Filled the email field with the signed-in user's email: {}", email);
    }

    @Step("Click the element with key <searchedKey>")
    public void clickElementByKey(String searchedKey){
        seleniumHelper.clickElement(searchedKey);
        logger.info("Clicked the element with key '{}'", searchedKey);
    }

    @Step("Scroll to element with resource-id <resourceId>")
    public void scrollToElementByKey(String resourceId){
        seleniumHelper.scrollUntilVisibleElement(resourceId);
        logger.info("Scrolled to element with resource id: {}", resourceId);
    }

    @Step("Login with random Excel user")
    public void loginWithRandomUser() throws Exception {

        String[] user = ExcelUtils.getRandomUser("users.xlsx");

        String email = user[0];
        String password = user[1];
        String userName = user[2];

        typeTextToElementByKey("LoginScreenUserNameOrEmail", email);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(org.openqa.selenium.support.ui.ExpectedConditions
                        .textToBePresentInElement(
                                seleniumHelper.ffindElement("LoginScreenUserNameOrEmail"),
                                email));

        typeTextToElementByKey("LoginScreenPassword", password);

        ScenarioDataStore.put("savedUsername", userName);

        logger.info("Login with random user: email='{}', username='{}'", email, userName);
    }

    @Step("Verify that element <searchedKey> text equals stored value with key <key>")
    public void validateTextWithStoredValByKey(String searchedKey, String key){
        String storedText = ScenarioDataStore.get(key).toString();
        Assertions.assertEquals(seleniumHelper.ffindElement(searchedKey).getText(),storedText);
        logger.info("Verified that element with key '{}' has text " +
                            "'{}' matching stored value for key '{}'",
                            searchedKey, storedText, key);
    }

    @Step("Verify that element <searchedKey> text contains stored value with key <key>")
    public void validateContainsTextWithStoredValByKey(String searchedKey, String key){
        String storedText = ScenarioDataStore.get(key).toString();
        Assertions.assertTrue(seleniumHelper.ffindElement(searchedKey).getText().contains(storedText));
        logger.info("Verified that element with key '{}' has text " +
                        "'{}' contained stored value for key '{}'",
                searchedKey, storedText, key);
    }




    @Step("Relaunch app with no reset")
    public void relaunchAppWithNoResetTrue() {
        logger.info("Relaunching app. NoReset=true");

        try {
            //If the driver already exists and was started with noReset=true, just activate the app
            driver.activateApp("chat.rocket.android");
        } catch (Exception e) {
            logger.error("Failed to relaunch app with no reset, attempting new session", e);
            try {
                UiAutomator2Options options = new UiAutomator2Options()
                        .setAppActivity("chat.rocket.reactnative.MainActivity")
                        .setAppPackage("chat.rocket.android")
                        .setNoReset(true)
                        .setAutoGrantPermissions(true)
                        .setAutomationName("UiAutomator2");
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
                seleniumHelper = new SeleniumHelper(driver);
                seleniumHelper.waitElementWithVisible("StartScreenConnectButton");
            } catch (MalformedURLException ex) {
                logger.error("Failed to start new driver session", ex);
            }
        }
    }


    @Step("Iteration <iteration> started")
    public void logIteration(String iteration){
        logger.info("Iteration: " + iteration);
    }

}