package demo.google.calculator;

import java.util.ArrayList;
import java.util.List;

import org.concordion.api.AfterSuite;
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

    private static List<Browser> browsers = new ArrayList<Browser>();
    protected static ThreadLocal<Browser> browser = new ThreadLocal<Browser>();

    @Extension
    private ScreenshotExtension extension = new ScreenshotExtension();

    protected GoogleResultsPage resultsPage;

    private void initialiseBrowser() {
        if (browser.get() == null) {
            Browser newBrowser = new Browser();
            browser.set(newBrowser);
            browsers.add(newBrowser);
        }
        extension.setScreenshotTaker(new SeleniumScreenshotTaker(browser.get()));
    }

    @AfterSuite
    public void close() {
        for (Browser browser : browsers) {
            browser.close();
        }
    }

    /**
     * Searches for the specified topic, and waits for the results page to load.
     */
    public void searchFor(String topic) {
        initialiseBrowser();
        resultsPage = new GoogleSearchPage(browser.get()).searchFor(topic);
    }
}
