package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddStudySubjectSteps extends BaseStep {
    public String studySubjectId;

    public AddStudySubjectSteps(TestContext context) {
        super(context);
    }

    @When("I search trial with trial status {string} and trial category {string}")
    public void searchTrialWithStatusAndCategory(String status, String category) {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForDropdown("Current Trial Status", status);
        trialSearchPage.filterSearchForDropdown("Trial Category", category);
        trialSearchPage.clickApply();
    }

    @When("I click Add Study Subject")
    public void clickOnAddStudySubjectButton() {
        addStudySubjectPage.clickOnAddStudySubject();
    }

    @And("I click Add Subject")
    public void clickAddSubjectButton() {
        addStudySubjectPage.clickOnAddSUbject();
    }

    @And("I click Save")
    public void clickSaveButton() {
        addStudySubjectPage.clickOnSave();
    }

    @And("I click Update Study Subject")
    public void clickUpdateSubjectButton() {
        addStudySubjectPage.clickOnUpdateStudySubject();
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("Study Subject ID", studySubjectId);
        trialSearchPage.clickApply();
    }

    @Then("The new study subject displays with the data that was entered")
    public void verifySubjectAdded() {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("Study Subject ID", studySubjectId);
        trialSearchPage.clickApply();
        addStudySubjectPage.verifySubjectAdded();
    }

    @When("I click update study subject icon")
    public void updateStudySubject() {
        addStudySubjectPage.clickOnUpdateStudySubjectIcon();
    }

    @Then("The Study Subject page displays")
    public void verifySubjectPage() {
        addStudySubjectPage.verifySubjectPage();
    }

    @And("I close the success dialog")
    public void clickOnClose() {
        addStudySubjectPage.clickClose();
    }

    @And("I click Save Button")
    public void ClickSaveButton() {
        addStudySubjectPage.clickOnPartialSubjectSave();
    }

    @And("I click Cancel Button")
    public void ClickCancelButton() {
        addStudySubjectPage.clickOnCancel();
    }

    @And("I delete all subjects")
    public void ClickDeleteAll() {
//        trialSearchPage.clickFilterOption();
//        trialSearchPage.filterSearchForTextBox("Study Subject ID", studySubjectId);
//        trialSearchPage.clickApply();
        addStudySubjectPage.clickDeleteAll(studySubjectId);
    }

    @And("I enter valid data into the required fields for {string}")
    public void EnterValidDataIntoTheRequiredFieldsFor(String accrualType) {
        studySubjectId = addStudySubjectPage.fillRequiredData(accrualType);
    }

    @And("I enter valid data into the required fields for {string} and disease code {string} and site code {string}")
    public void EnterValidDataIntoTheRequiredFieldsForICDO3(String accrualType, String diseaseCode, String siteCode) {
        studySubjectId = addStudySubjectPage.fillRequiredDataWithDiseaseAndSiteCode(accrualType, diseaseCode, siteCode);
    }

    @And("I enter valid data into the required fields for {string} and disease code {string}")
    public void EnterValidDataIntoTheRequiredFieldsForSDC(String accrualType, String diseaseCode) {
        studySubjectId = addStudySubjectPage.fillRequiredDataWithDisease(accrualType, diseaseCode);
    }

    @Then("I update the Study Subject is modified with the correct Disease Code System {string} and Disease Code {string}")
    public void updateStudySubjectModifiedWithCorrectData(String diseaseCodeSystem, String diseaseCode) {
        addStudySubjectPage.updateStudySubjectModifiedWithCorrectData(diseaseCodeSystem, diseaseCode);
    }

    @Then("Validate the Study Subject is modified with the correct Disease Code System {string}")
    public void validateStudySubjectModifiedWithCorrectData(String diseaseCodeSystem) {
        addStudySubjectPage.validateStudySubjectModifiedWithCorrectData(diseaseCodeSystem);
    }

    @And("I enter valid data into the required fields for {string},{string},{string},{string}")
    public void iEnterValidDataIntoTheRequiredFieldsFor(String AccrualType, String DiseaseTerminology, String DiseaseCode, String SiteCode) {
        studySubjectId = addStudySubjectPage.addSubjectDiseaseCode(AccrualType, DiseaseTerminology, DiseaseCode, SiteCode);
    }

    @And("I click the TrialID link with trial {string}")
    public void iClickTheTrialIDLinkWithTrialId(String trailId) {
        addStudySubjectPage.clickTrialIDLink(trailId);
    }
}
