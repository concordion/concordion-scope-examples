package org.concordion.google.calculator;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;

@RunWith(ConcordionRunner.class)
public class CalculatorFixture extends GoogleBaseFixture {

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
