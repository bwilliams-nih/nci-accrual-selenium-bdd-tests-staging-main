package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.ConfigFileReader;
import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class AssignedTrialsSteps extends BaseStep {
    public static String trialID = "";

    public AssignedTrialsSteps(TestContext context) {
        super(context);
    }

    @Given("I Accept PA")
    public void accept() {
        protocolAbstractorPage.acceptProtocolAbstraction();
    }

    @When("I select Processing status as a {string}")
    public void selectProcessingStatus(String status) {
        protocolAbstractorPage.selectStatus(status);
    }

    @And("I search for the result")
    public void searchResult() {
        protocolAbstractorPage.searchRecords();
    }

    @Then("I able to see records")
    public void verifyRecords() {
        protocolAbstractorPage.verifySearch();
    }

    @And("I click on first NCI Trial Identifier")
    public void selectFirstIdentifier() {
        protocolAbstractorPage.selectFirstNCIIdentifier();
    }

    @Then("I get Trial ID")
    public void getTrialId() {
        trialID = protocolAbstractorPage.getTrialId();
    }

    @And("I select Assignee as a {string}")
    public void selectAssignee(String assignee) {
        protocolAbstractorPage.selectAssignee(assignee);
    }

    @And("I click on Save button")
    public void clickOnSave() {
        protocolAbstractorPage.clickOnSave();
    }

    @Then("I validate record is updated")
    public void verifySuccessMessage() {
        protocolAbstractorPage.verifySuccessMsg();
    }

    @And("I logout")
    public void logout() {
        protocolAbstractorPage.logout();
    }

    @And("my assigned trial is displayed")
    public void validateTrialId() {
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("int"))
            trialID = "NCI-2020-00053";
        else trialID = "NCI-2020-01752";
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("NCI Trial Identifier", trialID);
        trialSearchPage.clickApply();
        protocolAbstractorPage.validateTrialId(trialID);
    }

    @Then("all columns are displayed")
    public void validateAllColumns() {
        protocolAbstractorPage.validateAllColumns();
    }
}
