package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LandingPageSteps extends BaseStep {

    public LandingPageSteps(TestContext context) {
        super(context);
    }

    @Given("I'm on the Trial Search page")
    public void displayLandingPage() {
        trialSearchPage.displays();
    }

    @Then("only the first 10 trials display")
    public void columnsCheck() {
        trialSearchPage.verifyRowsCount("10");
    }

    @When("I click the show dropdown")
    public void clickOnShowDropdown() {
        trialSearchPage.clickPageSizeDropDown();
    }

    @And("I select {string} from the Show dropdown")
    public void iCanSelectPageSizeAs(String pageSize) {
        trialSearchPage.selectPageSize(pageSize);
    }

    @Then("The first {string} trials display")
    public void verifyTheTrailCountAs(String inputSize) {
        trialSearchPage.verifyRowsCount(inputSize);
    }

    @And("I validate page size options")
    public void validatePageSizeOption(){
        trialSearchPage.validatePageSize();
    }
}
