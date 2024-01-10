package gov.nci.ctrp.accrual.pages;

import gov.nci.ctrp.accrual.managers.FileReaderManager;
import gov.nci.ctrp.accrual.utils.Encoder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class ProtocolAbstractorPage extends BasePage {
    @FindBy(css = "#acceptDisclaimer")
    private WebElement btnAccept;

    @FindBy(css = "select#documentWorkflowStatusCode")
    private WebElement drpProcessingStatus;

    @FindBy(css = ".search")
    private WebElement btnSearch;

    @FindBy(xpath = "//*[text()='Search Results!!!!']")
    private WebElement veridyRecords;

    @FindBy(xpath = "(//td//a[contains(text(),'NCI')])[1]")
    private WebElement firstNCILink;

    @FindBy(xpath = "//td[normalize-space()='NCI Trial Identifier']//following-sibling::td")
    private WebElement trialID;

    @FindBy(css = "[id='assignedTo']")
    private WebElement drpAssignee;

    @FindBy(xpath = "//*[text()='Save']")
    private WebElement btnSave;

    @FindBy(css = ".confirm_msg")
    private WebElement successMsg;

    @FindBy(xpath = "(//*[text()='Logout'])[1]")
    private WebElement btnLogout;

    @FindBy(css = "[aria-label='NCI Trial Identifier Filter Input']")
    private WebElement txtTrialId;

    String text = "(//a[text()='%s'])[1]";

    private String column = "//*[@class='v-input__control']//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Show/Hide']")
    private WebElement showHideBtn;

    private String columnHeader = "//*[normalize-space(text())='%s' and @class='customHeaderLabel']";

    public ProtocolAbstractorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void acceptProtocolAbstraction() {
        click(btnAccept);
        waitForTimeInMilliSecs(2000);
        stopLoading();
        refreshPage();
    }


    public void selectStatus(String status) {
        String[] st = status.split(",");
        for (int i = 0; i < st.length; i++)
            select_item(drpProcessingStatus, st[i]);
    }

    public void searchRecords() {
        click(btnSearch);
    }

    public void verifySearch() {
        validateElementIsDisplayed(veridyRecords);
    }

    public void selectFirstNCIIdentifier() {
        scrollIntoView(firstNCILink);
        click(firstNCILink);
    }

    public void selectAssignee(String assignee) {
        select_item(drpAssignee, assignee);
    }

    public String getTrialId() {
        return get_text(trialID);
    }

    public void clickOnSave() {
        click(btnSave);
    }

    public void verifySuccessMsg() {
        validateElementIsDisplayed(successMsg);
    }

    public void logout() {
        click(btnLogout);
    }

    public void validateTrialId(String trialId) {
        validateElementIsDisplayed(By.xpath(String.format(text, trialId)));
    }

    public void validateAllColumns() {
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "NCI Trial Identifier")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "ClinicalTrials.gov ID")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Lead Org Trial Identifier")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Lead Organization")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Local Trial ID")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Official Title")));
        click(showHideBtn);
        click(By.xpath(String.format(column, "ClinicalTrials.gov ID")));
        click(By.xpath(String.format(column, "Lead Org Trial Identifier")));
        click(By.xpath(String.format(column, "Lead Organization")));
        click(By.xpath(String.format(column, "Local Trial ID")));
        click(By.xpath(String.format(column, "Official Title")));
        input_keys(Keys.ESCAPE);
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Current Trial Status")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Trial Type")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Trial Category")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Accrual Type")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Accrual Disease Terminology")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Total Trial Accrual")));
        click(showHideBtn);
        click(By.xpath(String.format(column, "Current Trial Status")));
        click(By.xpath(String.format(column, "Trial Type")));
        input_keys(Keys.ESCAPE);
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Last Accrual Update Submitted")));
        validateElementIsDisplayed(By.xpath(String.format(columnHeader, "Action")));
    }
}
