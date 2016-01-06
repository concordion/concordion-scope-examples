package org.concordion.google.calculator;

import org.concordion.api.extension.Extension;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.ParallelRunExtension;
import org.concordion.ext.ScreenshotExtension;
import org.concordion.google.web.Browser;
import org.concordion.google.web.GoogleResultsPage;
import org.concordion.google.web.GoogleSearchPage;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.selenium.SeleniumScreenshotTaker;
import org.junit.After;
import org.junit.runner.RunWith;

/** 
 * A base class for Google search tests that opens up the Google site
 * at the Google search page, and closes the browser once the test is complete.
 */
@RunWith(ConcordionRunner.class)
@Extensions(ParallelRunExtension.class)
public abstract class GoogleBaseFixture {

	protected Browser browser;
	protected GoogleSearchPage searchPage;
    private SeleniumScreenshotTaker screenshotTaker;
    
    @Extension
    public ScreenshotExtension extension = new ScreenshotExtension();
    
    protected GoogleResultsPage resultsPage;

    private void open() {
        browser = new Browser();
        screenshotTaker = new SeleniumScreenshotTaker(browser.getDriver());
        extension.setScreenshotTaker(screenshotTaker);
    }

	@After
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
    	searchPage = new GoogleSearchPage(browser.getDriver());
        resultsPage = searchPage.searchFor(topic);
    }
}
