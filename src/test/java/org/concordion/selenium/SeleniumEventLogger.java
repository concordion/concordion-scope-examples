package org.concordion.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumEventLogger extends AbstractWebDriverEventListener {

    final Logger logger = LoggerFactory.getLogger("selenium.events");
    private String oldValue;

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        oldValue = element.getAttribute("value");
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String elementName = getElementName(element);
        try {
            String newValue = element.getAttribute("value");
            if (!newValue.equals(oldValue)) {
                if (newValue.length() == 0) {
                    logger.debug("[{}] - cleared value", elementName);
                } else {
                    logger.debug("[{}] - changed value to '{}'", elementName, newValue);
                }
            }
        } catch (Exception e) {
            logger.debug("[{}] - changed value", elementName);
        }
    }

    @Override
    public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
        logger.debug("[{}] - found", arg0);
    }

    @Override
    public void afterNavigateBack(WebDriver arg0) {
        logger.debug("Navigated back");
    }

    @Override
    public void afterNavigateForward(WebDriver arg0) {
        logger.debug("Navigated forward");
    }

    @Override
    public void afterNavigateTo(String arg0, WebDriver arg1) {
        logger.debug("Navigated to '{}'", arg0);
    }

    @Override
    public void afterScript(String arg0, WebDriver arg1) {
        logger.debug("Ran script '{}'", arg0);
    }

    @Override
    public void beforeClickOn(WebElement arg0, WebDriver arg1) {
        logger.debug("[{}] - clicked", getElementName(arg0));
    }

    private String getElementName(WebElement arg0) {
        String foundBy = arg0.toString();
        if (foundBy != null) {
            int arrowIndex = foundBy.indexOf("->");
            if (arrowIndex >= 0) {
                return "By." + foundBy.substring(arrowIndex + 3, foundBy.length() - 1);
            }
        }
        return "unknown";
    }
}
