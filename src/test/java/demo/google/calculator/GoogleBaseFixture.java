package demo.google.calculator;

import org.concordion.api.AfterSuite;
import org.concordion.api.BeforeSpecification;
import org.concordion.api.BeforeSuite;
import org.concordion.api.extension.Extension;
import org.concordion.ext.ScreenshotExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.selenium.Browser;
import org.concordion.selenium.SeleniumScreenshotTaker;
import org.junit.runner.RunWith;

import demo.driver.google.web.GoogleResultsPage;
import demo.driver.google.web.GoogleSearchPage;

/**
 * A base class for Google search tests that opens up the Google site at the
 * Google search page, and closes the browser once the test is complete.
 */
@RunWith(ConcordionRunner.class)
public abstract class GoogleBaseFixture {

    protected static Browser browser;
    protected static GoogleSearchPage searchPage;
    private static SeleniumScreenshotTaker screenshotTaker;

    @Extension
    private static ScreenshotExtension extension = new ScreenshotExtension();

    protected GoogleResultsPage resultsPage;

    @BeforeSuite
    public void open() {
        browser = new Browser();
        screenshotTaker = new SeleniumScreenshotTaker(browser);
        extension.setScreenshotTaker(screenshotTaker);
    }

    @BeforeSpecification
    public void openSearchPage() {
        searchPage = new GoogleSearchPage(browser);
    }

    @AfterSuite
    public void close() {
        browser.close();
        browser = null;
    }

    /**
     * Searches for the specified topic, and waits for the results page to load.
     */
    public void searchFor(String topic) {
        resultsPage = searchPage.searchFor(topic);
    }
}
