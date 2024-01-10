package gov.nci.ctrp.accrual.pages;

import com.deque.html.axecore.results.ResultType;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import gov.nci.ctrp.accrual.managers.FileReaderManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static com.deque.html.axecore.selenium.AxeReporter.getReadableAxeResults;
import static org.testng.Assert.*;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Duration timeout_duration;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.timeout_duration = Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
        this.wait = new WebDriverWait(driver, timeout_duration);
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected void scrollIntoView(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
        scrollUpALittle();
    }

    public void scrollDownALittle() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
    }

    public void scrollUpALittle() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-250)");
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click;", element);
    }

    protected void click(WebElement element) {
        waitVisibility(element);
        waitClickaable(element);
        waitVisibility(element).click();
    }

    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    public void get(String url) {
        driver.get(url);
    }

    protected void click(By by) {
        waitForTimeInMilliSecs(2000);
        WebElement element = driver.findElement(by);
        waitVisibility(element).click();
    }

    protected void hoverTo(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    protected void input_keys(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();
    }

    protected void switchToPopupFrame(WebElement popupFrame) {
        waitForTimeInMilliSecs(500);
        driver.switchTo().frame(popupFrame);
        waitForTimeInMilliSecs(500);
    }

    protected void switchToDefaultFrame() {
        waitForTimeInMilliSecs(500);
        driver.switchTo().defaultContent();
        waitForTimeInMilliSecs(500);
    }

    protected void input_text(WebElement element, String text) {
        waitVisibility(element).click();
        element.clear();
        element.sendKeys(text);
    }

    protected void input_text(By by, String text) {
        WebElement element = driver.findElement(by);
        waitVisibility(element).click();
        element.clear();
        element.sendKeys(text);
    }

    protected void pressKey(Keys key) {
        new Actions(driver).sendKeys(key).build().perform();
    }

    protected void select_item(WebElement comboBox, String item) {
        waitVisibility(comboBox);
        Select select = new Select(comboBox);
        select.selectByVisibleText(item);
    }

    protected String get_text(WebElement element) {
        return waitVisibility(element).getText();
    }

    protected String get_text(By by) {
        WebElement element = driver.findElement(by);
        return waitVisibility(element).getText();
    }

    protected WebElement waitVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitVisibility(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /*this method continuously check for trial search/prior submission search list
    with specific interval till 5 min. Once list is visible testcase will execute normally.
    But if list is not visible within 5 min, testcase will fail. */
    public void waitForListDisplayed() {
        waitForTimeInMilliSecs(3000);
       /* if (!isElementDisplayed(By.xpath("(//*[@ref=\"centerContainer\"]//div[@row-id])[1]"))) {
            Instant currentTime = getCurrentTime();
            while (!isElementDisplayed(By.xpath("(//*[@ref=\"centerContainer\"]//div[@row-id])[1]"))) {
                Instant loopingTime = getCurrentTime();
                Duration timeElapsed = Duration.between(currentTime, loopingTime);
                long sec = timeElapsed.toMillis() / 1000;
                int durDiff = (int) sec;
                if (durDiff >= 60) {
                    fail("List not displayed");
                }
            }
        }*/
    }

    public void waitForElementDisplayed(By by) {
        waitForTimeInMilliSecs(500);
        if (!isElementDisplayed(by)) {
            Instant currentTime = getCurrentTime();
            while (!isElementDisplayed(by)) {
                Instant loopingTime = getCurrentTime();
                Duration timeElapsed = Duration.between(currentTime, loopingTime);
                long sec = timeElapsed.toMillis() / 1000;
                int durDiff = (int) sec;
                if (durDiff >= 300) {
                    fail("List not displayed");
                }
            }
        }
    }

    private Instant getCurrentTime() {
        return Instant.now();
    }

    private boolean isElementDisplayed(By locator) {
        boolean state;
        try {
            state = driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    protected WebElement waitClickaable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void validateElementText(WebElement element, String expectedAlertText) {
        waitVisibility(element);
        assertEquals(element.getText(), expectedAlertText);
    }

    protected void validateElementContainsText(WebElement element, String expectedAlertText) {
        waitVisibility(element);
        assertTrue(element.getText().contains(expectedAlertText));
    }

    public void displays(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }

    protected void validateElementIsDisplayed(WebElement element) {
        waitVisibility(element);
        assertTrue(element.isDisplayed());
    }

    protected void validateElementIsDisplayed(By by) {
        assertTrue(waitVisibility(by).isDisplayed(), "Element is NOT displayed!");
    }

    protected void validateElementIsPresent(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        if (element == null)
            Assert.fail("Element is NOT present!");
    }

    protected void validateElementIsNotDisplayed(WebElement element) {
        Assert.assertFalse(isDisplayed(element));
    }

    protected void validateElementIsNotDisplayed(By by) {
        boolean status = false;
        try {
            WebElement element = driver.findElement(by);
            if (element != null)
                status = true;
        } catch (Exception e) {
            status = false;
        }
        Assert.assertFalse(status);
    }

    protected void validateElementAttribute(WebElement element, String attribute, String expectedValue) {
        waitVisibility(element);
        assertEquals(element.getAttribute(attribute), expectedValue);
    }

    public boolean isDisplayed(WebElement element) {
        boolean displayed = false;
        try {
            if (element.isDisplayed()) {
                displayed = true;
            }
        } catch (Exception ignored) {

        }
        return displayed;
    }

    public boolean isDisplayed(By by) {
        boolean displayed = false;
        try {
            WebElement element = driver.findElement(by);
            if (element.isDisplayed()) {
                displayed = true;
            }
        } catch (Exception ignored) {
        }
        return displayed;
    }

    public void waitForPageToLoad(String title) {
        waitForTimeInMilliSecs(2000);
        waitForPageTitle(title);
        waitForPage(driver);
    }

    private void waitForPage(WebDriver driver) {
        try {
            wait.until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        } catch (TimeoutException e) {
            System.out.println(" Timed out while waiting for page to load. Page load took longer than: " + timeout_duration + "s!");
        }
    }

    public void waitForTimeInMilliSecs(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }

    public void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleIs(title));
    }


    public void validateNoViolations(Boolean hasViolations) {
        Assert.assertEquals(hasViolations, false);
    }

    public void scan508(String pageName) {
        Results results = new AxeBuilder().analyze(driver);
        boolean hasViolations;

        List<Rule> violations = results.getViolations();

        hasViolations = getReadableAxeResults(ResultType.Violations.getKey(), driver, violations);
        System.out.print(AxeReporter.getAxeResultString());
        AxeReporter.writeResultsToJsonFile("test-output/AccessibilityReport/" + pageName, results);
        AxeReporter.writeResultsToTextFile("test-output/AccessibilityReport/" + pageName, AxeReporter.getAxeResultString());
        validateNoViolations(hasViolations);
    }

    protected String randomAmount() {
        NumberFormat numberFormat = NumberFormat.getInstance();
        Double randomNumber = Math.floor(Math.random() * 10000000);
        //format the number with grouping ie comma separated
        numberFormat.setGroupingUsed(false);

        return numberFormat.format(randomNumber);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void stopLoading() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return window.stop");
    }
}

