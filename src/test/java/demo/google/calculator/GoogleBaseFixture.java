package demo.google.calculator;

import org.concordion.api.*;
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
 *
 * This fixture shows how you could login/logout per example, with a browser created per spec.
 */
@RunWith(ConcordionRunner.class)
public abstract class GoogleBaseFixture {

    @ConcordionScoped(Scope.SPECIFICATION)
    private ScopedObjectHolder<Browser> browserHolder = new ScopedObjectHolder<Browser>() {
        @Override
        public Browser create() {
            System.out.println("creating browser");
            Browser browser = new Browser();
            extension.setScreenshotTaker(new SeleniumScreenshotTaker(browser));
            return browser;
        }

        @Override
        protected void destroy(Browser browser) {
            System.out.println("closing browser");
            browser.close();
        };
    };

    @Extension
    private ScreenshotExtension extension = new ScreenshotExtension();

    protected GoogleResultsPage resultsPage;

    @BeforeExample
    public void login() {
        Browser browser = browserHolder.get();
        System.out.println("logging in");
        // login would go here
    }

    @AfterExample
    public void logout() {
        System.out.println("logging out");
        // logout would go here
    }

    /**
     * Searches for the specified topic, and waits for the results page to load.
     */
    public void searchFor(String topic) {
        System.out.println("searching for " + topic);
        resultsPage = new GoogleSearchPage(browserHolder.get()).searchFor(topic);
    }
}
