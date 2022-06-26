package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            UPDATE_SAVED_READING_LIST_ITEM = "org.wikipedia:id/item_title";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return waitForElementPresent(By.id(TITLE), "Can't find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Can't find the end of article",
                20
        );
    }

    public void addArticleToMyList(String nameOfFolder) {
        waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can't find button to open list of options",
                15);

        waitForElementPresent(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find option 'Add to reading list'",
                20);

        waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find option 'Add to reading list'",
                15);

        waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Can't find button 'GOT IT'",
                15);

        waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Can't find text input",
                20);

        waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                nameOfFolder,
                "Can't put text into input",
                1);

        waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Can't find button 'OK' to confirm text input",
                5);

    }

    public void closeArticle() {
        waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can't find X button to close article",
                5);
    }

    public void savedArticleToExistingReadingList() {
        waitForElementPresent(
                By.xpath(OPTIONS_BUTTON),
                "Can't find option 'Add to reading list'",
                20);

        waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can't find button to open list of options",
                15);

        waitForElementPresent(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find option 'Add to reading list'",
                20);

        waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can't find option 'Add to reading list'",
                15);

        waitForElementAndClick(
                By.id(UPDATE_SAVED_READING_LIST_ITEM),
                "Can't find option 'Add to reading list'",
                15);
    }


}
