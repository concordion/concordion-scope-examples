package org.concordion.google.calculator;

import org.concordion.api.*;
import org.concordion.api.extension.Extension;
import org.concordion.ext.ScreenshotExtension;
import org.concordion.google.web.Browser;
import org.concordion.google.web.GoogleResultsPage;
import org.concordion.google.web.GoogleSearchPage;
import org.concordion.integration.junit4.ConcordionRunner;
import org.concordion.selenium.SeleniumScreenshotTaker;
import org.junit.runner.RunWith;

/**
 * A base class for Google search tests that opens up the Google site at the
 * Google search page, and closes the browser once the test is complete.
 */
@RunWith(ConcordionRunner.class)
public abstract class GoogleBaseFixture {

    @ConcordionScoped(Scope.SPECIFICATION)
    private ScopedObjectHolder<Browser> browserHolder = new ScopedObjectHolder<Browser>() {
        @Override
        public Browser create() {
            Browser browser = new Browser();
            extension.setScreenshotTaker(new SeleniumScreenshotTaker(browser.getDriver()));
            return browser;
        }

        @Override
        protected void destroy(Browser browser) {
            browser.close();
        };
    };

    @Extension
    private ScreenshotExtension extension = new ScreenshotExtension();

    protected GoogleResultsPage resultsPage;

    /**
     * Searches for the specified topic, and waits for the results page to load.
     */
    public void searchFor(String topic) {
        resultsPage = new GoogleSearchPage(browserHolder.get().getDriver()).searchFor(topic);
    }
}
