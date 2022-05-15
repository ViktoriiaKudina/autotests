import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
        capabilities.setCapability("app", "wikipedia");
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

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title",
                15);

    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Cookie",
                "Can't find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'Cookie')]"),
                "Can't find 'Cookie' topic searching by 'Cookie'",
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title",
                15);

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Can't find the end of the article",
                        20);

    }

    @Test
    public void testSaveArticlesToMyListAndDeleteOne(){
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

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find button to open list of options",
                15);

        waitForElementPresent(
                By.xpath("//*[@text='Add to reading list']"),
        "Can't find element 'Add to reading list'",
        15);

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find option 'Add to reading list'",
                15);

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can't find button 'GOT IT'",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can't find text input 'My reading list'",
                5);

        String nameOfFolder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                nameOfFolder,
                "Can't put text into input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can't find button 'OK' to confirm text input",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find X button to close article",
                5);

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Horse",
                "Can't find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'Horse')]"),
                "Can't find 'Horse' topic searching by 'Horse'",
                15);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can't find title",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can't find button to open list of options",
                15);

        waitForElementPresent(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find element 'Add to reading list'",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can't find option 'Add to reading list'",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text='"+ nameOfFolder +"']"),
                "Can't find saving list 'Learning programming'",
                15);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can't find X button to close article",
                5);


        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can't find 'My lists' button",
                5);

        waitForElementPresent(
                By.xpath("//*[@text= '"+ nameOfFolder +"']"),
                "Can't find element 'Add to reading list'",
                15);

        waitForElementAndClick(
                By.xpath("//*[@text= '"+ nameOfFolder +"']"),
                "Can't find created folder",
                15);

        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't find element 'Add to reading list'",
                15);

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't find saved article 'Java (programming language'");

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can't delete saved article 'Java (programming language)'",
                15);

        waitForElementPresent(
                By.xpath("//*[@text='Horse']"),
                "Can't find saved article 'Horse'",
                15);

    }

    @Test
    public void testAmountOfNotEmptySearch(){

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can't find Search Wikipedia input",
                5);

        String searchLine = "Linkin Park Discography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Can't find search input",
                5);

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/search_container']";

        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Can't find anything by request" + searchLine,
                15);

        int amountOfSearchResults = getAmountOfElements(
                By.xpath(searchResultLocator));

        Assert.assertTrue(
                "We found few results",
                amountOfSearchResults > 0);
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

    @Test
    public void testCheckValidSearch() {
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

        //List<WebElement> contentList = listElement.findElements(By.id("org.wikipedia:id/fragment_main_coordinator"));
        List<WebElement> contentList = listElement.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"));

        for (int i = 0; i < contentList.size(); i++) {
            WebElement element = contentList.get(i);

            Assert.assertTrue(assertElementContainsText(
                    element,
                    "Java",
                    "Can't find searching text",
                    15
            ));
        }

    }

    @Test
    public void testElementTitlePresent(){

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

        WebElement listElement = waitForElementPresent(
                By.id("org.wikipedia:id/page_contents_container"),
                "Something goes wrong",
                15);

        WebElement titleElement = listElement.findElement(By.id("org.wikipedia:id/view_page_title_text"));

        Assert.assertTrue("Can't find element 'title'", assertElementPresent(titleElement));
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private boolean assertElementPresent(WebElement element){
        return element != null;
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by, errorMessage, 15);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
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

    private boolean assertElementContainsText(WebElement element, String text, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width / 2);
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by,String errorMessage, int maxSwipes){
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0){
            if(alreadySwiped > maxSwipes){
                waitForElementPresent(by,"Can't find element by swiping up. \n" + errorMessage,0);
                        return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage){
        WebElement element = waitForElementPresent(by, errorMessage,10);
        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY+lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(500)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by){
    List elements = driver.findElements(by);
    return elements.size();
    }

}

