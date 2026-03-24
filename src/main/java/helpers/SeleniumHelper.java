package helpers;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class SeleniumHelper {

    private final AndroidDriver driver;
    private Actions action;
    private final OtherHelper helper = new OtherHelper();

    public SeleniumHelper(AndroidDriver driver) {
        this.driver = driver;
    }

    private By getBy(String searchedKey) {

        try {
            JsonNode foundedNode = helper.getValue(searchedKey);

            if (foundedNode == null) {
                throw new RuntimeException("Locator bulunamadı: " + searchedKey);
            }

            JsonNode typeNode = foundedNode.get("type");
            JsonNode valueNode = foundedNode.get("value");

            if (typeNode == null || valueNode == null) {
                throw new RuntimeException("Locator JSON hatalı: " + searchedKey);
            }

            String type = typeNode.asText();
            String value = valueNode.asText();

            switch (type) {
                case "id": return By.id(value);
                case "cssSelector": return By.cssSelector(value);
                case "name": return By.name(value);
                case "xpath": return By.xpath(value);
                case "className": return By.className(value);
                case "accessibilityId": return AppiumBy.accessibilityId(value);
                default: throw new IllegalArgumentException("Geçersiz locator type: " + type);
            }

        } catch (IOException e) {
            throw new RuntimeException("Locator okunamadı: " + searchedKey, e);
        }
    }

    public WebElement ffindElement(String searchedKey) {
        try {
            return driver.findElement(getBy(searchedKey));
        } catch (Exception e) {
            throw new RuntimeException("Element bulunamadı: " + searchedKey, e);
        }
    }

    public List<WebElement> findElements(String searchedKey) {
        try {
            return driver.findElements(getBy(searchedKey));
        } catch (Exception e) {
            throw new RuntimeException("Elements bulunamadı: " + searchedKey, e);
        }
    }

    public FluentWait<AndroidDriver> waitByFluent() {
        return new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(200))
                .withTimeout(Duration.ofSeconds(30))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public WebElement waitElementWithPresence(String searchedKey) {
        return waitByFluent().until(
                ExpectedConditions.presenceOfElementLocated(getBy(searchedKey))
        );
    }

    public WebElement waitElementWithVisible(String searchedKey) {
        return waitByFluent().until(
                ExpectedConditions.visibilityOfElementLocated(getBy(searchedKey))
        );
    }

    public WebElement waitElementWithClickable(String searchedKey) {
        return waitByFluent().until(
                ExpectedConditions.elementToBeClickable(getBy(searchedKey))
        );
    }

    public void clickElement(String searchedKey) {
        waitElementWithClickable(searchedKey).click();
    }

    public void longPress(String searchedKey, int durationSeconds) {

        WebElement element = waitElementWithClickable(searchedKey);

        Point location = element.getLocation();
        Dimension size = element.getSize();

        int centerX = location.getX() + size.getWidth() / 2;
        int centerY = location.getY() + size.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);

        sequence.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), centerX, centerY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofSeconds(durationSeconds)));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public void scrollUntilVisibleElement(String resourceId) {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().resourceId(\"" + resourceId + "\"));"
                )
        );
    }

    public void sendKeyToElement(String searchedKey, String text) {
        WebElement element = waitElementWithVisible(searchedKey);
        element.clear();
        element.sendKeys(text);
    }

    public void doubleClickToElement(String searchedKey) {
        action = new Actions(driver);
        WebElement element = waitElementWithClickable(searchedKey);
        action.moveToElement(element).doubleClick().perform();
    }

    public boolean isElementPresent(String searchedKey) {
        return !findElements(searchedKey).isEmpty();
    }

    public boolean isDisplayed(String searchedKey) {
        return ffindElement(searchedKey).isDisplayed();
    }

    public boolean containsText(String searchedKey, String text) {
        return ffindElement(searchedKey).getText().contains(text);
    }

    public boolean containsAttributeValue(String searchedKey, String attribute, String text) {
        String actualVal = ffindElement(searchedKey).getAttribute(attribute);
        return actualVal != null && actualVal.contains(text);
    }

    public void ifElementExistClick(String searchedKey) {
        try {
            waitElementWithVisible(searchedKey).click();
        } catch (TimeoutException e) {
            System.out.println("Element bulunamadı: " + searchedKey);
        }
    }
}