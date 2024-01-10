package gov.nci.ctrp.accrual.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;

public class PriorSubmissionPage extends BasePage {
    @FindBy(xpath = "//div[@class='v-navigation-drawer__content']/div/a[@href='/priorSubmissions']")
    private WebElement priorSubmissionMenu;

    @FindBy(css = "[aria-label='Trial ID Filter Input']")
    private WebElement txtTrialId;

    @FindBy(css = "[href='/priorSubmissions']")
    private WebElement menuPriorSubmission;

    @FindBy(xpath = "///label[text()='Search...']//ancestor::div[@class='v-field__field']")
    private WebElement lblTrialId;

    String validateMenu = "//*[@class='v-list-item-title' and text()='%s']";

    String gridColumns = "//*[@class='customHeaderLabel' and text()='%s']";

    String sortColumn = "//*[text()='%s']//following-sibling::*[@ref='eSortIndicator']//*[@class='ag-sort-indicator-icon ag-sort-none-icon']";

    String sortArrowAsc = "//*[text()='%s']//following-sibling::i[1]";

    String sortArrowDsc = "//*[text()='%s']//following-sibling::i[2]";

    String commonText = "//*[text()='%s']";

    private String column = "//*[@class='v-input__control']//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Show/Hide']")
    private WebElement showHideBtn;


    public PriorSubmissionPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateLeftMenu() {
        hoverTo(priorSubmissionMenu);
    }

    public void validateLeftMenu() {
        hoverTo(priorSubmissionMenu);
        validateElementIsDisplayed(By.cssSelector("#root > div > div > nav > div > div:nth-child(1) > a"));
        validateElementIsDisplayed(By.cssSelector("#root > div > div > nav > div > div:nth-child(2) > a"));
        validateElementIsDisplayed(By.cssSelector("#root > div > div > nav > div > div:nth-child(3) > a"));
    }

    public void navigatePriorSubmissionMenu() {
        hoverTo(priorSubmissionMenu);
        waitForTimeInMilliSecs(5000);
        click(priorSubmissionMenu);
        waitForTimeInMilliSecs(5000);
        waitForListDisplayed();
        hoverTo(showHideBtn);
    }

    public void validatePriorSubmissionColumns() {
        validateElementIsDisplayed(By.xpath(String.format(gridColumns, "Submission Method")));
        validateElementIsDisplayed(By.xpath(String.format(gridColumns, "Trial ID")));
        validateElementIsDisplayed(By.xpath(String.format(gridColumns, "File/Subject")));
        validateElementIsDisplayed(By.xpath(String.format(gridColumns, "Date/Time")));
        validateElementIsDisplayed(By.xpath(String.format(gridColumns, "Submitted By")));
        validateElementIsDisplayed(By.xpath(String.format(gridColumns, "Upload Status")));
        click(showHideBtn);
        click(By.xpath(String.format(column, "File/Subject")));
        input_keys(Keys.ESCAPE);
        validateElementIsDisplayed(By.xpath(String.format(gridColumns, "Rejection Reason")));
    }

    public void validateAllColumnSortByAsc() {
        validateIndividualColumnSortByAsc("Trial ID");
        validateIndividualColumnSortByAsc("File/Subject");
        validateIndividualColumnSortByAsc("Submission Method");
        validateIndividualColumnSortByAsc("Date/Time");
        validateIndividualColumnSortByAsc("Submitted By");
        validateIndividualColumnSortByAsc("Upload Status");
        click(showHideBtn);
        click(By.xpath(String.format(column, "File/Subject")));
        input_keys(Keys.ESCAPE);
        validateIndividualColumnSortByAsc("Rejection Reason");
    }

    public void validateAllColumnSortByDsc() {
        validateIndividualColumnSortByDsc("Trial ID");
        validateIndividualColumnSortByDsc("File/Subject");
        validateIndividualColumnSortByDsc("Submission Method");
        validateIndividualColumnSortByDsc("Date/Time");
        validateIndividualColumnSortByDsc("Submitted By");
        validateIndividualColumnSortByDsc("Upload Status");
        click(showHideBtn);
        click(By.xpath(String.format(column, "File/Subject")));
        input_keys(Keys.ESCAPE);
        validateIndividualColumnSortByDsc("Rejection Reason");
    }

    public void validateIndividualColumnSort(String columnName) {
        if (isDisplayed(By.xpath(String.format(sortColumn, columnName)))) {
            click(By.xpath(String.format(commonText, columnName)));
        }
        validateElementIsDisplayed(By.xpath(String.format(sortArrowAsc, columnName)));
    }

    public void validateIndividualColumnSortByAsc(String columnName) {
        if (isDisplayed(By.xpath(String.format(sortArrowAsc, columnName)))) {
            click(By.xpath(String.format(sortArrowAsc, columnName)));
        }
    }

    public void validateIndividualColumnSortByDsc(String columnName) {
        if (isDisplayed(By.xpath(String.format(sortArrowDsc, columnName)))) {
            click(By.xpath(String.format(sortArrowDsc, columnName)));
        }
    }

    public String getTrialID() {
        List<WebElement> trialIdColumnValues = driver.findElements(By.xpath("//*[@col-id='trialId' and @role='gridcell']"));
        Optional<String> trialIdText = trialIdColumnValues
                .stream().filter(e -> !e.getAttribute("innerText").isEmpty())
                .map(e -> e.getAttribute("innerText")).findFirst();
        if (trialIdText.isPresent()) {
            return trialIdText.get();
        } else {
            throw new RuntimeException("TrialID value is not displayed");
        }

    }

    public void validateTrialIDRecords(String trialIDText) {
        waitForListDisplayed();
        List<WebElement> trialIdColumnValues = getElements(By.xpath("//*[@col-id='trialId' and @role='gridcell']"));
        List<WebElement> filterElement = trialIdColumnValues.stream()
                .filter(e -> e.getAttribute("innerText").contains(trialIDText))
                .toList();
        Assert.assertEquals(trialIdColumnValues.size(), filterElement.size());
    }
}
