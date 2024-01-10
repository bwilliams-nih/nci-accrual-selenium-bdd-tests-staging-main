package gov.nci.ctrp.accrual.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DiseaseTerminologyPage extends BasePage {
    @FindBy(css = "[aria-label='Total Trial Accrual Filter Input']")
    private WebElement txtTotalTrialAccural;

    @FindBy(css = "[aria-label='Accrual Disease Terminology Filter Input']")
    private WebElement txtAccuralDiseaseTerminology;

    @FindBy(xpath = "(//div[@col-id='trialCount']//preceding-sibling::div[@col-id='diseaseCodeSystem']//*[@class='v-field__input'])[1]")
    private WebElement accuralDiseaseTerminologyDropdown;

    private String accuralDiseaseTerminology = "(//div[@col-id='trialCount' and text()='%s']//preceding-sibling::div[@col-id='diseaseCodeSystem']//*[@class='v-field__input'])[1]";
//    @FindBy(xpath = "(//*[contains(@class,' DisTermDropDown')]//*[@class='v-field__input']//span[@class='v-select__selection-text'])[1]")
//    private WebElement firstAccuralDiseaseTerminology;


    @FindBy(xpath = "//*[text()='Show/Hide']")
    private WebElement showHideBtn;

    private String firstAccuralDiseaseTerminology = "(//div[@col-id='trialCount' and text()='%s']//preceding-sibling::div[@col-id='diseaseCodeSystem']//*[@class='v-field__input']//span[@class='v-select__selection-text'])[1]";
    @FindBy(css = "#root > div > div > nav > div > div:nth-child(1) > a")
    private WebElement menuTrial;

    @FindBy(xpath = "//*[text()='Show/Hide']")
    private WebElement ShowOrHideButton;

    private String commonText = "//*[normalize-space()='%s']";

    private String diseaseTermList = "//*[@class='v-list-item-title' and text()='%s']";

    private String commonContainsText = "//*[contains(text(),'%s')]";

    private static final String[] COLUMN_LIST = {"ClinicalTrials.gov ID", "Lead Org Trial Identifier", "Lead Organization", "Local Trial ID", "Official Title", "Current Trial Status", "Trial Type", "Trial Category", "Accrual Type", "Accrual Disease Terminology", "Total Trial Accrual", "Last Accrual Update Submitted"};

    private String column = "//*[@class='v-input__control']//*[text()='%s']";

    public DiseaseTerminologyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigateToTrial() {
        click(menuTrial);
        waitForListDisplayed();
    }

    public void clickAccrualDropdownAndValidateDiseaseTerminologyList() {
        click(ShowOrHideButton);
        click(By.xpath(String.format(column, "ClinicalTrials.gov ID")));
        click(By.xpath(String.format(column, "Lead Org Trial Identifier")));
        click(By.xpath(String.format(column, "Local Trial ID")));
        click(By.xpath(String.format(column, "Lead Organization")));
        click(By.xpath(String.format(column, "Official Title")));
        click(By.xpath(String.format(column, "Current Trial Status")));
        input_keys(Keys.ESCAPE);
        click(accuralDiseaseTerminologyDropdown);
        validateElementIsDisplayed(By.xpath(String.format(commonText, "SDC")));
        validateElementIsDisplayed(By.xpath(String.format(commonText, "ICD10")));
        validateElementIsDisplayed(By.xpath(String.format(commonText, "ICD9")));
        validateElementIsDisplayed(By.xpath(String.format(commonText, "ICD-O-3")));
        input_keys(Keys.ESCAPE);
    }

    public void updateTerminology(String totalTrial) {
        String diseaseTerm = get_text(By.xpath(String.format(firstAccuralDiseaseTerminology, totalTrial)));
        click(By.xpath(String.format(accuralDiseaseTerminology, totalTrial)));
        if (diseaseTerm.equalsIgnoreCase("SDC"))
            click(By.xpath(String.format(diseaseTermList, "ICD10")));
        else
            click(By.xpath(String.format(diseaseTermList, "SDC")));
        waitForTimeInMilliSecs(3000);
    }

    public void validateWarningMessage() {
        validateElementIsDisplayed(By.xpath(String.format(commonContainsText, "Warning: Accrual has previously been entered on this trial using the ")));
    }

    public void validateWarningMessageNotDisplayed() {
        validateElementIsNotDisplayed(By.xpath(String.format(commonContainsText, "Warning: Accrual has previously been entered on this trial using the ")));
    }

    public void searchTrial(String totalAccural) {
        scrollIntoView(txtTotalTrialAccural);
        showColumnInTrialPage("Accrual Disease Terminology#Total Trial Accrual");
        input_text(txtTotalTrialAccural, totalAccural);
        input_text(txtAccuralDiseaseTerminology, "SDC");
    }

    public void showColumnInTrialPage(String columnName) {
        deselctAllColumns();
        click(ShowOrHideButton);
        String[] columns = columnName.split("#");
        for (int i = 0; i < columns.length; i++) {
            waitForTimeInMilliSecs(1000);
            click(By.xpath(String.format(column, columns[i])));
        }
        input_keys(Keys.ESCAPE);
    }

    public void deselctAllColumns() {
        click(ShowOrHideButton);
        for (int i = 0; i < COLUMN_LIST.length; i++) {
            waitForTimeInMilliSecs(1000);
            click(By.xpath(String.format(column, COLUMN_LIST[i])));
        }
        input_keys(Keys.ESCAPE);
    }

}
