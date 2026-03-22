package org.example;

import com.thoughtworks.gauge.*;
import helpers.SeleniumHelper;
import io.appium.java_client.android.*;
import io.appium.java_client.android.options.*;

import model.FakerDataModel;
import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import testdata.*;


import java.net.*;

public class BaseTest{
    protected static AndroidDriver driver;
    protected static FakerDataFactory faker;
    protected static FakerDataModel user;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static UiAutomator2Options options;
    protected static SeleniumHelper seleniumHelper;

    @BeforeScenario
    public void setUpTest() throws MalformedURLException, URISyntaxException {
        logger.info("Setting up scenario: preparing Faker data");
        faker = new FakerDataFactory();
        user = faker.produceFakeData();
        logger.info("Launching the Rocket.Chat application");
        options = new UiAutomator2Options()
                .setAppActivity("chat.rocket.reactnative.MainActivity")
                .setAppPackage("chat.rocket.android")
                .setPlatformName("Android")
                .setNoReset(false)
                .setAppWaitActivity("chat.rocket.reactnative.MainActivity")
                .setUdid("emulator-5554")
                .setAutoGrantPermissions(true)
                .setAutomationName("UiAutomator2");
        driver = new AndroidDriver(
                new URI("http://127.0.0.1:4723").toURL(), options
        );
        seleniumHelper = new SeleniumHelper(driver);
        logger.info("Application launched successfully");
    }

    @AfterScenario
    public void tearDown(){
        try {
            if(driver != null){
                logger.info("Closing the Android driver session");
                driver.terminateApp("chat.rocket.android");
                driver.quit();
            }
        } catch (Exception e) {
            logger.error("Error while closing driver: {}", e.getMessage());
        }
    }
}