package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.ConfigFileReader;
import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PriorSubmissionSteps extends BaseStep {

    public String trialIdText;

    public PriorSubmissionSteps(TestContext context) {
        super(context);
    }

    @Given("I navigate to side menu bar")
    public void navigateLeftBar() {
        priorSubmissionPage.navigateLeftMenu();
    }

    @Then("I validate all menu options display")
    public void validateLeftBar() {
        priorSubmissionPage.validateLeftMenu();
    }

    @When("I navigate to the Prior Submission page")
    public void navigateToThePriorSubmissionPage() {
        priorSubmissionPage.navigatePriorSubmissionMenu();
    }

    @Then("All columns display")
    public void allColumnsDisplay() {
        priorSubmissionPage.validatePriorSubmissionColumns();
    }

    @When("I filter the list of trials by a specific trial ID")
    public void enterTrialIdInTrialIDFilterTextBox() {
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("int"))
            trialIdText = "NCI-2020-00044";
        else trialIdText = "NCI-2023-00200";
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("Trial ID", trialIdText);
        trialSearchPage.clickApply();
    }

    @Then("only records display that match that trial ID")
    public void canSeeRecordsWhichContainsSearchedText() {
        priorSubmissionPage.validateTrialIDRecords(trialIdText);
    }
}
