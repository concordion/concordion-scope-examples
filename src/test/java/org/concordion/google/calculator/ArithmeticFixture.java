package org.concordion.google.calculator;

import org.concordion.google.web.GoogleFixture;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;

@RunWith(ConcordionRunner.class)
public class ArithmeticFixture extends GoogleFixture {

    /**
     * Returns the result from Google calculation.
     */
    public String getCalculatorResult() {
        try {
            return resultsPage.getCalculatorResult();
        } catch (NoSuchElementException e) {
            return resultsPage.getTopResultTitle();
        }
    }
}
