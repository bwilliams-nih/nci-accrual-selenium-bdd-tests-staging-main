package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DiseaseTerminologySteps extends BaseStep {

    private String studyName;

    public DiseaseTerminologySteps(TestContext context) {
        super(context);
    }

    @And("I search for a trial with trial status {string} and trial category {string}")
    public void searchForATrialWithAccuralReported(String status, String category) {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("Current Trial Status", status);
        trialSearchPage.filterSearchForDropdown("Trial Category", category);
        trialSearchPage.clickApply();
    }

    @And("I search for a trial with accrual type {string}")
    public void searchForATrialWithAccrualType(String type) {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForDropdown("Accrual Type", type);
        trialSearchPage.clickApply();
    }

    @And("I search for a trial with trial type {string}")
    public void searchForATrialWithTrialType(String type) {
        trialSearchPage.clickFilterOptionWithoutClose();
        trialSearchPage.filterSearchForDropdown("Trial Type", type);
        trialSearchPage.clickApply();
    }

    @And("I search for a trial with trial category {string}")
    public void searchForATrialWithTrialCategory(String type) {
        trialSearchPage.clickFilterOptionWithoutClose();
        trialSearchPage.filterSearchForDropdown("Trial Category", type);
        trialSearchPage.clickApply();
    }

    @And("I search for a trial with accrual disease terminology {string}")
    public void searchForATrialWithAccrualDiseaseTerminology(String type) {
        trialSearchPage.clickFilterOptionWithoutClose();
        trialSearchPage.filterSearchForDropdown("Accrual Disease Terminology", type);
        trialSearchPage.clickApply();
    }

    @Then("only trials with selected Accrual type Display")
    public void validateFilterAccrualType() {
        addStudySubjectPage.validateFilterAccrualType();
    }

    @Then("I can not add new study")
    public void addStudyNotVisible() {
        addStudySubjectPage.addStudyNotVisible();
    }

    @And("Click the Accrual Disease Terminology dropdown")
    public void clickTheAccrualDiseaseTerminologyDropdown() {
        diseaseTerminologyPage.clickAccrualDropdownAndValidateDiseaseTerminologyList();
    }

    @When("I update disease terminology for the first trial in the trial search list with total trial {string}")
    public void updateTerminologyForReportedAccrual(String totalTrial) {
        diseaseTerminologyPage.updateTerminology(totalTrial);
    }

    @Then("The warning message displays")
    public void checkWarningMessage() {
        diseaseTerminologyPage.validateWarningMessage();
    }

    @Then("The warning message does not display")
    public void checkWarningMessageNotDisplayed() {
        diseaseTerminologyPage.validateWarningMessageNotDisplayed();
    }

    @And("I search for a trial with Disease Terminology {string} and accrual reported {string}")
    public void iSearchForATrialWithAccrualReported(String diseaseTerminology, String status) {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForDropdown("Accruals Reported", status);
        trialSearchPage.filterSearchForDropdown("Accrual Disease Terminology", diseaseTerminology);
        trialSearchPage.clickApply();
    }

    @And("I search for a trial with Total Accrual Reported {string} and Accrual Type {string}")
    public void iSearchForATrialWithAccrualTotalReported(String totalAccrualReported, String accrualType) {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForDropdown("Accrual Type", accrualType);
        trialSearchPage.filterSearchForTextBox("Total Trial Accrual", totalAccrualReported);
        trialSearchPage.clickApply();
    }
}
