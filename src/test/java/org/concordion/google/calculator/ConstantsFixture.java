package org.concordion.google.calculator;

import org.concordion.google.web.GoogleFixture;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;

@RunWith(ConcordionRunner.class)
public class ConstantsFixture extends GoogleFixture {

    public String getConstantResult() {
        try {
            return resultsPage.getConstantResult();
        } catch (NoSuchElementException e) {
            return resultsPage.getCalculatorResult();
        }
    }
}
