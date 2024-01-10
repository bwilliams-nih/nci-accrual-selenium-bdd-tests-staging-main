package gov.nci.ctrp.accrual.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class TrialSearchPage extends BasePage {

    @FindBy(xpath = "//div[@class='v-sheet v-theme--CustomLightTheme entity-logo trialSearchHeader']//following-sibling::div/h1")
    private WebElement pageHeader;

    @FindBy(xpath = "//span[@class='v-btn__content'][text()='Accept']")
    private WebElement disclaimerAcceptBtn;

    @FindBy(css = ".ag-header-cell-label > span.ag-header-cell-text")
    private List<WebElement> tableHeader;

    @FindBy(xpath = "//div[@class='v-field__field']/label[text()='Show']/parent::div//following-sibling::div[@class='v-field__append-inner']/i")
    private WebElement pageSizeDropDown;

    @FindBy(xpath = "//div[@class='v-overlay__content v-select__content']//div[@class='v-list-item-title']")
    private List<WebElement> webElementPageSizesList;

    @FindBy(xpath = "//div[@ref='eContainer']//div[@col-id='assignedIdentifier']")
    private List<WebElement> trailsCount;

    @FindBy(xpath = "//span[@ref='lbFirstRowOnPage']")
    private WebElement firstRowCount;

    @FindBy(xpath = "//span[@ref='lbLastRowOnPage']")
    private WebElement lastRowCount;

    @FindBy(xpath = "//div[@class='v-sheet v-theme--CustomLightTheme total-count']/span")
    private WebElement recordCount;

    @FindBy(xpath = "//span[@ref='lbCurrent']")
    private WebElement currentPageNumber;

    @FindBy(xpath = "//span[@ref='lbTotal']")
    private WebElement totalPageNumber;

    @FindBy(xpath = "//div[@ref='btFirst']")
    private WebElement firstPageBtn;

    @FindBy(xpath = "//div[@ref='btPrevious']")
    private WebElement PreviousPageBtn;

    @FindBy(xpath = "//div[@ref='btNext']")
    private WebElement nextPageBtn;

    @FindBy(xpath = "//div[@ref='btLast']")
    private WebElement lastPageBtn;

    private WebElement pageSizeElement(String inputSize) {
        return driver.findElement(By.xpath("//div[@class='v-overlay__content v-select__content']//div[@class='v-list-item-title'][text()='" + inputSize + "']"));
    }

    List<String> columnLst = Arrays.asList("NCI Trial Identifier", "ClinicalTrials.gov ID", "Lead Org Trial Identifier", "Local Trial ID", "Lead Organization", "Official Title", "Current Trial Status", "Trial Type", "Trial Category", "Accrual Type", "Accrual Disease Terminology", "Total Trial Accrual", "Last Accrual Update Submitted", "Action");

    List<String> pageSizeList = Arrays.asList("10", "25", "50", "100");

    @FindBy(xpath = "//h1[normalize-space(text())='Trial Search']")
    private WebElement trialSearchPageHeader;

    @FindBy(xpath = "//input[@aria-label='NCI Trial Identifier Filter Input']")
    private WebElement NCITrailIdentifierSearchBox;
    @FindBy(xpath = "//input[@aria-label='ClinicalTrials.gov ID Filter Input']")
    private WebElement ClinicalTrialsSearchBoxGovID;

    @FindBy(xpath = "//*[text()='Filter Options']")
    private WebElement filterOption;

    @FindBy(xpath = "//button[normalize-space()='Apply']")
    private WebElement btnApply;

    @FindBy(xpath = "//*[text()='Total Trial Accrual ']//i[contains(@class,'arrow-up')]")
    private WebElement sortTotalTrialAccrual;

    @FindBy(css = "#root > div > div > nav > div > div:nth-child(1) > a")
    private WebElement menuTrial;

    @FindBy(xpath = "//span[@class='org-list-container']/following-sibling::div//div[@class='v-autocomplete__selection']//following-sibling::input")
    private WebElement txtOrgFamily;

    @FindBy(xpath = "//*[text()='Org/Family:']//following-sibling::div//div[@class='v-field__field']")
    private WebElement lblOrgFamily;

    @FindBy(xpath = "//*[text()='Family']//parent::div//parent::div//parent::div[@class='v-field__field']/following-sibling::div")
    private WebElement selectOrgFamilyDropDown;

    @FindBy(xpath = "//span[@class='org-list-container']/following-sibling::div//div[@class='v-field__append-inner']")
    private WebElement selectOrgFamilyValue;

    private WebElement searchFilterBox(String filterColumn) {
        return driver.findElement(By.xpath("//input[@aria-label='" + filterColumn + "']"));
    }

    String txtSearch = "//*[text()='%s']//following-sibling::input";

    String lblSearch = "//*[text()='%s']//ancestor::div[@class='v-field__field']";

    String commonText = "(//*[text()='%s'])[last()]";

    String commonContainsText = "(//*[contains(text(),'%s')])[last()]";

    String commonSearchText = "//*[normalize-space()='%s']";

    public TrialSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void getHeader() {
        List<String> headerNames = tableHeader.stream()
                .map(element -> element.getAttribute("innerText"))
                .toList();
        columnLst.forEach(column -> {
            System.out.println(column);
            Assert.assertTrue(headerNames.contains(column));
        });
        Assert.assertEquals(headerNames.size(), 14);
    }

    public void clickDisclaimerAccept() {
        click(disclaimerAcceptBtn);
    }

    public void clickPageSizeDropDown() {
        waitVisibility(pageSizeDropDown);
        click(pageSizeDropDown);
    }

    public void getPageSizeList() {
        List<String> pageSizes = webElementPageSizesList.stream()
                .map(element -> element.getAttribute("innerText"))
                .toList();
        pageSizeList.forEach(sizeValue -> {
            System.out.println(sizeValue);
            Assert.assertTrue(pageSizes.contains(sizeValue));
        });
        Assert.assertEquals(pageSizes.size(), 4);
    }

    public int getTrailsListCount() {
        List<String> countTrails = trailsCount.stream()
                .map(element -> element.getAttribute("innerText"))
                .toList();
        return countTrails.size();
    }

    public void getTrailsCount(String selectedSize) {
        List<String> countTrails = trailsCount.stream()
                .map(element -> element.getAttribute("innerText"))
                .toList();

        Assert.assertEquals(countTrails.size(), Integer.parseInt(selectedSize));
    }

    public void clickPageSize(String inputSize) {
        click(pageSizeElement(inputSize));
    }

    public void selectPageSize(String pageSize) {
        clickPageSize(pageSize);
    }

    public void validatePageSize() {
        getPageSizeList();
    }

    public void verifyRowsCount(String inputPageSize) {
        waitForTimeInMilliSecs(5000);
        Assert.assertEquals(getTotalRecordCount(), Integer.parseInt(inputPageSize));
    }

    public int getTotalRecordCount() {
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(text(),'NCI')]"));
        return elements.size();
    }

    public String getTotalPageNumber() {
        return totalPageNumber.getText();
    }

    public int totalPagesBasedOnPageSize(String pageSize) {
        int recordCount = getTotalRecordCount();
        double number = (double) recordCount / Integer.parseInt(pageSize);
        return (int) Math.ceil(number);
    }

    public void verifyTotalPages(String pageSize) throws InterruptedException {
        int totalPages = totalPagesBasedOnPageSize(pageSize);
        int totalPageNumbers = Integer.parseInt(getTotalPageNumber());
        Assert.assertEquals(totalPages, totalPageNumbers);
    }

    public void displays() {
        waitVisibility(trialSearchPageHeader);
        waitClickaable(lblOrgFamily);
        click(lblOrgFamily);
        input_text(txtOrgFamily, "Novartis Pharmaceuticals");
        waitForTimeInMilliSecs(5000);
        click(By.xpath(String.format(commonContainsText, "Novartis Pharmaceuticals")));
        waitForListDisplayed();
        validateElementIsDisplayed(trialSearchPageHeader);
    }

    public void clickNCITrailIdentifierSearchBox() {
        click(NCITrailIdentifierSearchBox);
    }

    public void clickOnFilterSearchBox(String filterBy) {
        waitVisibility(searchFilterBox(filterBy));
        click(searchFilterBox(filterBy));
    }

    public void enterFilterInput(String searchFilterBox, String searchInput) {
        input_text(searchFilterBox(searchFilterBox), searchInput);
    }

    public void filterSearchForTextBox(String fieldName, String text) {
        waitVisibility(By.xpath(String.format(lblSearch, fieldName)));
        click(By.xpath(String.format(lblSearch, fieldName)));
        input_text(By.xpath(String.format(txtSearch, fieldName)), text);
    }

    public void filterSearchForDropdown(String fieldName, String value) {
        click(By.xpath(String.format(lblSearch, fieldName)));
        click(By.xpath(String.format(commonText, value)));
    }

    public void clickFilterOption() {
        waitForTimeInMilliSecs(2000); //we are checking already filtered fields, in this case we don't have any specific locator for wait. so hard coded wait added for 1 second.
        List<WebElement> elements = getElements(By.xpath("//*[@aria-label='Close']"));
        for (WebElement element : elements) {
            waitForTimeInMilliSecs(500); //we are checking already filtered fields, in this case we don't have any specific locator for wait. so hard coded wait added for 1 second.
            click(element);
        }
        click(filterOption);
    }

    public void clickFilterOptionWithoutClose() {
        waitForTimeInMilliSecs(1000);
        click(filterOption);
    }

    public void clickApply() {
        click(btnApply);
        waitForTimeInMilliSecs(3000);
    }

    public void sortTotalTrialAccrualAsc() {
        jsClick(sortTotalTrialAccrual);
        waitForListDisplayed();
    }

    public void validateSearchResults(String inputExpected) {
        waitForTimeInMilliSecs(5000);
        validateElementIsDisplayed(By.xpath(String.format(commonSearchText, inputExpected)));
    }

    public void navigateToTrial() {
        hoverTo(menuTrial);
        click(menuTrial);
        waitVisibility(filterOption);
        hoverTo(filterOption);
    }

    public void selectOrgFamily(String orgFamilyInput) {
        click(selectOrgFamilyDropDown);
        waitForElementDisplayed(By.xpath(String.format(commonText, orgFamilyInput)));
        click(By.xpath(String.format(commonText, orgFamilyInput)));
    }

    public void inputOrgFamily(String txtOrgFamilyValue) {
        click(selectOrgFamilyValue);
        input_text(txtOrgFamily, txtOrgFamilyValue);
        waitForTimeInMilliSecs(5000);
        click(By.xpath(String.format(commonContainsText, txtOrgFamilyValue)));
        waitForListDisplayed();
    }


}
