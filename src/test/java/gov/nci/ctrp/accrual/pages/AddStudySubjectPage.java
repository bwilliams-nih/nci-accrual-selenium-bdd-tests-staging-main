package gov.nci.ctrp.accrual.pages;

import com.github.javafaker.Faker;
import gov.nci.ctrp.accrual.utils.ConfigFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddStudySubjectPage extends BasePage {

    //@FindBy(xpath = "(//*[@col-id='assignedIdentifier']//a)[2]")
    @FindBy(xpath = "(//*[@col-id='assignedIdentifier']//a)[contains(text(),'NCI-2023-00002')]")
    private WebElement lnkTrial;

    private WebElement lnkTrial(String trailId) {
        return driver.findElement(By.xpath("(//*[@col-id='assignedIdentifier']//a)[contains(text(),'"+trailId+"')]"));
    }

    @FindBy(xpath = "//*[text()='Add Study Subject']")
    private WebElement btnAddStudySubject;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject ID']//following-sibling::div//input")
    private WebElement txtStudySubjectId;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Birthdate']//following-sibling::div//input")
    private WebElement txtStudySubjectBirthdate;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Birthdate']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblStudySubjectBirthdate;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Sex']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblStudySubjectSex;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Race']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblStudySubjectRace;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Ethnicity']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblStudySubjectEthnicity;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Country']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblStudySubjectCountry;

    @FindBy(xpath = "//*[normalize-space(text())='Disease Terminology']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblDiseaseTerminology;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Zip Code']//following-sibling::span//div[contains(@class,'v-input__control')]")
    private WebElement lblStudySubjectZipcode;

    @FindBy(xpath = "//*[normalize-space(text())='Study Subject Zip Code']//following-sibling::span//input")
    private WebElement txtStudySubjectZipcode;

    @FindBy(xpath = "//*[normalize-space(text())='Registration Date (MM/DD/YYYY)']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblRegistrationDate;

    @FindBy(xpath = "//*[normalize-space(text())='Registration Date (MM/DD/YYYY)']//following-sibling::div//input")
    private WebElement txtRegistrationDate;

    @FindBy(xpath = "//*[normalize-space(text())='Disease']//following-sibling::div//div[@class='v-field__append-inner']")
    private WebElement iconSearchDisease;

    @FindBy(xpath = "//*[normalize-space(text())='Disease Site']//following-sibling::div//div[@class='v-field__append-inner']")
    private WebElement iconSearchDiseaseSite;

    @FindBy(xpath = "//*[normalize-space()='Disease Name']//input")
    private WebElement txtDiseaseName;

    @FindBy(xpath = "//*[normalize-space()='Disease Code']//input")
    private WebElement txtDiseaseCode;

    @FindBy(xpath = "//div[@role='dialog']//*[normalize-space()='Site Name']//following-sibling::*//input")
    private WebElement txtSiteName;

    @FindBy(xpath = "//*[normalize-space()='Disease Name' and @class='v-field__field']")
    private WebElement lblDiseaseName;

    @FindBy(xpath = "//*[normalize-space()='Disease Code' and @class='v-field__field']")
    private WebElement lblDiseaseCode;

    @FindBy(xpath = "//*[normalize-space()='Site Name' and @class='v-field__field']")
    private WebElement lblSiteName;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement btnDiseaseSearch;

    @FindBy(xpath = "(//a[text()='Select'])[1]")
    private WebElement lnkSelectFirstDisease;

    String selectedDiseaseCode = "//span[text()='%s' and contains(@class,'selection-text')]";

    @FindBy(xpath = "//*[normalize-space(text())='Participating Site']//following-sibling::div//div[contains(@class,'v-input__control')]")
    private WebElement lblParticipatingSite;

    @FindBy(xpath = "//*[text()='Add Subject']")
    private WebElement btnAddSubject;


    @FindBy(xpath = "//*[text()='Save']")
    private WebElement btnSave;

    @FindBy(xpath = "//*[text()='Update Study Subject']")
    private WebElement btnUpdateStudySubject;

    @FindBy(xpath = "//*[text()='Save']")
    private WebElement btnPartialSubjectSave;


    @FindBy(xpath = "//*[text()='Cancel']")
    private WebElement btnCancel;

    @FindBy(xpath = "(//span[normalize-space()='Other Administrative Error'])[1]")
    private WebElement btnOtherAdministrativeError;

    @FindBy(xpath = "(//span[normalize-space()='Yes, continue to delete'])[1]")
    private WebElement btnYesContinueDelete;

    @FindBy(xpath = "//*[text()='Delete All']")
    private WebElement btnDeleteAll;

    @FindBy(xpath = "//*[text()='Close Dialog']")
    private WebElement btnCloseSubject;

    private String column = "//*[@class='v-input__control']//*[text()='%s']";

    @FindBy(xpath = "//*[text()='Show/Hide']")
    private WebElement showHideBtn;

    @FindBy(xpath = "//*[normalize-space()='Site Code' and @class='v-field__field']")
    private WebElement lblSiteCode;

    @FindBy(xpath = "//*[normalize-space()='Site Code']//input")
    private WebElement txtSiteCode;

    @FindBy(xpath = "(//*[contains(@class,'mdi-pencil')])[1]")
    private WebElement updateStudySubjectIcon;

    @FindBy(xpath = "//span[(text())='Delete All']")
    private WebElement deleteStudySubjectIcon;

    private By studyPageHeader = By.xpath("//h1[normalize-space(text())='Study Subject']");

    private String commonText = "(//*[normalize-space(text())='%s'])[last()]";

    private static String diseaseTerminology = "SDC";
    private String studySubjectId;
    private String studySubjectBirthDate = "10/1994";
    private String studySubjectSex = "Male";
    private String studySubjectRace = "Asian";
    private String studySubjectEthnicity = "Not Reported";
    private String studySubjectCountry = "United States";
    private String studyPartialSubjectCountry = "Afghanistan";
    private String zipcode = "73001";
    private String registrationDate = "12/12/2022";
    private String diseaseName = "Cancer";
    private String siteName = "Lung";
    private String participatingSiteUAT = "Dana-Farber Harvard Cancer Center";
    private String participatingSiteINT = "Novartis Pharmaceuticals";

    public AddStudySubjectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickTrialIDLink(String trailId) {
        System.out.println(lnkTrial(trailId).getText());
        click(lnkTrial(trailId));
    }

    public void clickOnAddStudySubject() {
        click(btnAddStudySubject);
    }

    public String fillRequiredData(String accType) {
        Faker faker = new Faker();
        studySubjectId = faker.name().firstName().toUpperCase();
        input_text(txtStudySubjectId, studySubjectId);
        click(lblStudySubjectBirthdate);
        input_text(txtStudySubjectBirthdate, studySubjectBirthDate);
        click(lblStudySubjectSex);
        click(By.xpath(String.format(commonText, studySubjectSex)));
        click(lblStudySubjectRace);
        click(By.xpath(String.format(commonText, studySubjectRace)));
        click(lblStudySubjectRace);
        click(lblStudySubjectEthnicity);
        click(By.xpath(String.format(commonText, studySubjectEthnicity)));
        pressKey(Keys.ESCAPE);
        waitClickaable(lblStudySubjectCountry);
        if (Objects.equals(accType, "Subject")) {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studySubjectCountry)));
            click(By.xpath(String.format(commonText, studySubjectCountry)));
            pressKey(Keys.ESCAPE);
        } else {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            click(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            pressKey(Keys.ESCAPE);
        }
        waitForTimeInMilliSecs(2000);
        click(lblStudySubjectZipcode);
        input_text(txtStudySubjectZipcode, zipcode);
        click(lblRegistrationDate);
        input_text(txtRegistrationDate, registrationDate);
        click(lblDiseaseTerminology);
        click(By.xpath(String.format(commonText, diseaseTerminology)));
        pressKey(Keys.ESCAPE);
        waitVisibility(iconSearchDisease);
        click(iconSearchDisease);
        click(lblDiseaseName);
        input_text(txtDiseaseName, diseaseName);
        click(btnDiseaseSearch);
        waitVisibility(lnkSelectFirstDisease);
        click(lnkSelectFirstDisease);
        waitForTimeInMilliSecs(3000);
        click(lblParticipatingSite);
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("uat"))
            click(By.xpath(String.format(commonText, participatingSiteUAT)));
        else click(By.xpath(String.format(commonText, participatingSiteINT)));
        pressKey(Keys.ESCAPE);
        return studySubjectId;
    }

    public String fillRequiredDataWithDisease(String accType, String diseaseCode) {
        Faker faker = new Faker();
        studySubjectId = faker.name().firstName().toUpperCase();
        input_text(txtStudySubjectId, studySubjectId);
        click(lblStudySubjectBirthdate);
        input_text(txtStudySubjectBirthdate, studySubjectBirthDate);
        click(lblStudySubjectSex);
        click(By.xpath(String.format(commonText, studySubjectSex)));
        click(lblStudySubjectRace);
        click(By.xpath(String.format(commonText, studySubjectRace)));
        click(lblStudySubjectRace);
        click(lblStudySubjectEthnicity);
        click(By.xpath(String.format(commonText, studySubjectEthnicity)));
        pressKey(Keys.ESCAPE);
        waitClickaable(lblStudySubjectCountry);
        if (Objects.equals(accType, "Subject")) {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studySubjectCountry)));
            click(By.xpath(String.format(commonText, studySubjectCountry)));
            pressKey(Keys.ESCAPE);
        } else {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            click(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            pressKey(Keys.ESCAPE);
        }
        waitForTimeInMilliSecs(2000);
        click(lblStudySubjectZipcode);
        input_text(txtStudySubjectZipcode, zipcode);
        click(lblRegistrationDate);
        input_text(txtRegistrationDate, registrationDate);
        click(lblDiseaseTerminology);
        click(By.xpath(String.format(commonText, "SDC")));
        pressKey(Keys.ESCAPE);
        waitVisibility(iconSearchDisease);
        click(iconSearchDisease);
        click(lblDiseaseCode);
        input_text(txtDiseaseCode, diseaseCode);
        click(btnDiseaseSearch);
        click(lnkSelectFirstDisease);
        waitForTimeInMilliSecs(3000);
        click(lblParticipatingSite);
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("uat"))
            click(By.xpath(String.format(commonText, participatingSiteUAT)));
        else click(By.xpath(String.format(commonText, participatingSiteINT)));
        pressKey(Keys.ESCAPE);
        return studySubjectId;
    }

    public String fillRequiredDataWithDiseaseAndSiteCode(String accType, String diseaseCode, String siteCode) {
        Faker faker = new Faker();
        studySubjectId = faker.name().firstName().toUpperCase();
        input_text(txtStudySubjectId, studySubjectId);
        click(lblStudySubjectBirthdate);
        input_text(txtStudySubjectBirthdate, studySubjectBirthDate);
        waitVisibility(lblStudySubjectSex);
        click(lblStudySubjectSex);
        click(By.xpath(String.format(commonText, studySubjectSex)));
        click(lblStudySubjectRace);
        click(By.xpath(String.format(commonText, studySubjectRace)));
        click(lblStudySubjectRace);
        click(lblStudySubjectEthnicity);
        click(By.xpath(String.format(commonText, studySubjectEthnicity)));
        pressKey(Keys.ESCAPE);
        waitClickaable(lblStudySubjectCountry);
        if (Objects.equals(accType, "Subject")) {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studySubjectCountry)));
            click(By.xpath(String.format(commonText, studySubjectCountry)));
            pressKey(Keys.ESCAPE);
        } else {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            click(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            pressKey(Keys.ESCAPE);
        }
        waitForTimeInMilliSecs(2000);
        click(lblStudySubjectZipcode);
        input_text(txtStudySubjectZipcode, zipcode);
        click(lblRegistrationDate);
        input_text(txtRegistrationDate, registrationDate);
        click(lblDiseaseTerminology);
        click(By.xpath(String.format(commonText, "ICD-O-3")));
        pressKey(Keys.ESCAPE);
        waitVisibility(iconSearchDisease);
        click(iconSearchDisease);
        click(lblDiseaseCode);
        input_text(txtDiseaseCode, diseaseCode);
        click(btnDiseaseSearch);
        click(lnkSelectFirstDisease);
        waitForTimeInMilliSecs(1000);
        click(iconSearchDiseaseSite);
        click(lblSiteCode);
        input_text(txtSiteCode, siteCode);
        click(btnDiseaseSearch);
        click(lnkSelectFirstDisease);
        waitForTimeInMilliSecs(3000);
        click(lblParticipatingSite);
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("uat"))
            click(By.xpath(String.format(commonText, participatingSiteUAT)));
        else click(By.xpath(String.format(commonText, participatingSiteINT)));
        pressKey(Keys.ESCAPE);
        return studySubjectId;
    }


    public void clickOnSave() {
        waitForTimeInMilliSecs(3000);
        scrollIntoView(btnAddSubject);
        click(btnAddSubject);
    }

    public void clickOnAddSUbject() {
        waitForTimeInMilliSecs(3000);
        scrollIntoView(btnAddSubject);
        click(btnAddSubject);
    }

    public void clickOnUpdateStudySubject() {
        scrollIntoView(btnUpdateStudySubject);
        waitForTimeInMilliSecs(3000);
        click(btnUpdateStudySubject);
    }

    public void clickOnPartialSubjectSave() {
        scrollIntoView(btnPartialSubjectSave);
        click(btnPartialSubjectSave);
    }

    public void clickOnCancel() {
        scrollIntoView(btnCancel);
        click(btnCancel);
    }

    public void clickDeleteAll(String studyId) {
       // System.err.println("--------------->" + get_text(By.xpath("//*[@class='v-card-subtitle' and contains(text(),'NCI')]")) + " : " + studyId);
       click(deleteStudySubjectIcon);
       waitVisibility(btnOtherAdministrativeError);
       click(btnOtherAdministrativeError);
       waitVisibility(btnYesContinueDelete);
       click(btnYesContinueDelete);
    }

    public void clickClose() {
        waitForTimeInMilliSecs(3000);
        waitVisibility(btnCloseSubject);
        click(btnCloseSubject);
    }

    public void verifySubjectAdded() {
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId)));
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, registrationDate)));
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, studySubjectBirthDate)));
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, studySubjectBirthDate)));
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, studySubjectSex)));
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, studySubjectRace)));
        click(showHideBtn);
        waitForTimeInMilliSecs(1000);
        click(By.xpath(String.format(column, "Subject Registration Date")));
        click(By.xpath(String.format(column, "Subject DOB")));
        click(By.xpath(String.format(column, "Subject Sex")));
        input_keys(Keys.ESCAPE);
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, studySubjectCountry)));
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, studySubjectEthnicity)));
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId, zipcode)));
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("uat")) {
            validateElementIsDisplayed(By.xpath(String.format(commonText, studySubjectId, participatingSiteUAT)));
        } else {
            validateElementIsDisplayed(By.xpath(String.format(commonText, studySubjectId, participatingSiteINT)));
        }
    }

    public void verifySubjectPage() {
        validateElementIsDisplayed(studyPageHeader);
    }

    public void addStudyNotVisible() {
        validateElementIsNotDisplayed(btnAddStudySubject);
        waitForTimeInMilliSecs(1000);
    }

    public String addSubjectDiseaseCode(String accType, String DiseaseTerminology, String DiseaseCode, String SiteCode) {
        Faker faker = new Faker();
        studySubjectId = faker.name().firstName().toUpperCase();
        input_text(txtStudySubjectId, studySubjectId);
        click(lblStudySubjectBirthdate);
        input_text(txtStudySubjectBirthdate, studySubjectBirthDate);
        click(lblStudySubjectSex);
        click(By.xpath(String.format(commonText, studySubjectSex)));
        click(lblStudySubjectRace);
        click(By.xpath(String.format(commonText, studySubjectRace)));
        click(lblStudySubjectRace);
        click(lblStudySubjectEthnicity);
        click(By.xpath(String.format(commonText, studySubjectEthnicity)));
        pressKey(Keys.ESCAPE);
        click(lblStudySubjectCountry);
        if (Objects.equals(accType, "Subject")) {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studySubjectCountry)));
            click(By.xpath(String.format(commonText, studySubjectCountry)));
            pressKey(Keys.ESCAPE);
        } else {
            click(lblStudySubjectCountry);
            waitForElementDisplayed(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            click(By.xpath(String.format(commonText, studyPartialSubjectCountry)));
            pressKey(Keys.ESCAPE);
        }
        click(lblStudySubjectZipcode);
        input_text(txtStudySubjectZipcode, zipcode);
        click(lblRegistrationDate);
        input_text(txtRegistrationDate, registrationDate);
        click(lblDiseaseTerminology);
        click(By.xpath(String.format(commonText, DiseaseTerminology)));
        pressKey(Keys.ESCAPE);
        click(iconSearchDisease);
        click(lblDiseaseCode);
        input_text(txtDiseaseCode, DiseaseCode);
        click(btnDiseaseSearch);
        click(lnkSelectFirstDisease);
        if (DiseaseTerminology.equalsIgnoreCase("ICD-O-3")) {
            click(iconSearchDiseaseSite);
            click(lblSiteCode);
            input_text(txtSiteCode, SiteCode);
            click(btnDiseaseSearch);
            click(lnkSelectFirstDisease);
        }
        click(lblParticipatingSite);
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("uat"))
            click(By.xpath(String.format(commonText, participatingSiteUAT)));
        else click(By.xpath(String.format(commonText, participatingSiteINT)));
        pressKey(Keys.ESCAPE);
        return studySubjectId;
    }

    public void validateFilterAccrualType() {
        List<WebElement> elements = getElements(By.xpath("//*[@col-id='accrualType' and @role='gridcell']//span[@class='ag-cell-value']"));
        Set<String> set = new LinkedHashSet<>();
        for (WebElement element : elements) {
            set.add(get_text(element));
        }
        Assert.assertEquals(set.size(), 1, "filter search not working");
    }

    public void clickOnUpdateStudySubjectIcon() {
        waitForTimeInMilliSecs(3000);
        click(updateStudySubjectIcon);
    }

    public void updateStudySubjectModifiedWithCorrectData(String diseaseCodeSystem, String diseaseCode) {
        click(lblDiseaseTerminology);
        click(By.xpath(String.format(commonText, diseaseCodeSystem)));
        pressKey(Keys.ESCAPE);
        waitVisibility(iconSearchDisease);
        click(iconSearchDisease);
        click(lblDiseaseCode);
        input_text(txtDiseaseCode, diseaseCode);
        click(btnDiseaseSearch);
        waitVisibility(lnkSelectFirstDisease);
        click(lnkSelectFirstDisease);
    }

    public void validateStudySubjectModifiedWithCorrectData(String diseaseCodeSystem) {
        validateElementIsPresent(By.xpath(String.format(selectedDiseaseCode, diseaseCodeSystem)));
    }

    public void validateStudySubjectViaBatch(String studySubjectId) {
        validateElementIsPresent(By.xpath(String.format(commonText, studySubjectId.toUpperCase())));
    }
}
