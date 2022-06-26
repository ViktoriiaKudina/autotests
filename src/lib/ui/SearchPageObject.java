package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/search_container']",
            SEARCH_RESULT_ELEMENT_BY_ID = "org.wikipedia:id/fragment_main_coordinator",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
            SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/view_page_title_text']/*[@text='{SEARCH_TITLE}']",
            SEARCH_RESULT_BY_DESCRIPTION_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/view_page_subtitle_text']/*[@text='{SEARCH_DESCRIPTION}']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

     /**
     * TEMPLATES METHODS
     */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultByTitle(String title){
        return SEARCH_RESULT_BY_TITLE_SUBSTRING_TPL.replace("{SEARCH_TITLE}", title);
    }

    private static String getSearchResultByDescription(String description){
        return SEARCH_RESULT_BY_DESCRIPTION_SUBSTRING_TPL.replace("{SEARCH_DESCRIPTION}", description);
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        String searchResultTitleById = getSearchResultByTitle(title);
        String searchResultDescriptionById = getSearchResultByDescription(description);
        this.waitForElementPresent(By.id(searchResultTitleById),"Can't find article's title"+ " " + title,15);
        this.waitForElementPresent(By.id(searchResultDescriptionById),"Can't find article's description"+ " " + description,15);
    }

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Can't find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXpath), "Can't find search result with substring" + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXpath), "Can't find and click search result with substring" + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Can't find search cancel button", 5);
    }

    public void clickCanselSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Can't find and click search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.id(SEARCH_RESULT_ELEMENT_BY_ID),
                "Can't find anything by request",
                30);
        return this.getAmountOfElements(By.id(SEARCH_RESULT_ELEMENT_BY_ID));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.id(SEARCH_RESULT_ELEMENT_BY_ID),
                "Can't find empty result element",
                15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We can't find any results",
                15);
    }
}
