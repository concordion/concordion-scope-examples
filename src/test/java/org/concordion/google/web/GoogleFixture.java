package org.concordion.google.web;

import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.ext.ScreenshotExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.selenium.SeleniumScreenshotTaker;
import org.junit.runner.RunWith;

/** 
 * A base class for Google search tests that opens up the Google site
 * at the Google search page, and closes the browser once the test is complete.
 */
@RunWith(ConcordionRunner.class)
public abstract class GoogleFixture {

	protected static Browser browser = new Browser();
	protected GoogleSearchPage searchPage;
    private static SeleniumScreenshotTaker screenshotTaker = new SeleniumScreenshotTaker(browser.getDriver());
    
    @Extension
    public ConcordionExtension extension = new ScreenshotExtension().setScreenshotTaker(screenshotTaker);
    protected GoogleResultsPage resultsPage;

    protected GoogleFixture() {
		searchPage = new GoogleSearchPage(browser.getDriver());	
	}

//	@AfterClass
//	public static void close() {
//		browser.close();
//		browser = null;
//	}

    /**
     * Searches for the specified topic, and waits for the results page to load.
     */
    public void searchFor(String topic) {
    	resultsPage = searchPage.searchFor(topic);
    }
}
