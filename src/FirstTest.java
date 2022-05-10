import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:/Users/v.kudina/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Can't find search input",
                5);

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Can't find text",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by 'Java'",
                15);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can't find 'Search Wikipedia' input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Can't find search input",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find search field",
                15);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find X to cancel search",
                5);

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is visible on screen",
                5);
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Can't find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by 'Java'",
                15);

        WebElement titleElement = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title",
                15);

        String articleTitle = titleElement.getAttribute("text");
        Assert.assertEquals("Unexpected title", "Java (programming language)", articleTitle);

    }

    @Test
    public void testCancelingASearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Can't find search input",
                5);

        WebElement listElement = waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Can't find any text with 'Java'",
                15);

        List<WebElement> contentList = listElement.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"));
        Assert.assertTrue(contentList.size() > 1);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Can't find search field",
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_list_card_list"),
                "The list is not empty",
                15);

    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean assertElementHasText(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.textToBe(by, value));
    }

}

