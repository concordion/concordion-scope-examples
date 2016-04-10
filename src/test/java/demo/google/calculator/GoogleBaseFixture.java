package demo.google.calculator;

import org.concordion.api.AfterExample;
import org.concordion.api.extension.Extension;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.ParallelRunExtension;
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
@Extensions(ParallelRunExtension.class)
public abstract class GoogleBaseFixture {

    protected Browser browser;
    protected GoogleSearchPage searchPage;
    private SeleniumScreenshotTaker screenshotTaker;

    @Extension
    private ScreenshotExtension extension = new ScreenshotExtension();

    protected GoogleResultsPage resultsPage;

    private void open() {
        browser = new Browser();
        screenshotTaker = new SeleniumScreenshotTaker(browser);
        extension.setScreenshotTaker(screenshotTaker);
    }

    @AfterExample
    public void close() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
    }

    /**
     * Searches for the specified topic, and waits for the results page to load.
     */
    public void searchFor(String topic) {
        if (browser == null) {
            open();
        }
        searchPage = new GoogleSearchPage(browser);
        resultsPage = searchPage.searchFor(topic);
    }
}
