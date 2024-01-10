package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.testng.Assert.*;


public class TrialSearchSteps extends BaseStep {
    public TrialSearchSteps(TestContext context) {
        super(context);
    }

    @Given("I navigate to the 'Trial Search' page")
    public void navigateToTrial() {
        trialSearchPage.navigateToTrial();
    }

    @Given("I click on NCI Trial Identifier")
    public void iClickOnNCITrialIdentifier() {
        trialSearchPage.clickNCITrailIdentifierSearchBox();
    }

    @Then("the Trails list should be displayed")
    public void theTrailsListShouldBeDisplayed() {
        assertNotEquals(trialSearchPage.getTrailsListCount(), 0);
    }

    @Then("The results should not be displayed")
    public void theResultsShouldNotBeDisplayed() {
        assertEquals(trialSearchPage.getTotalRecordCount(), 0);
    }

    @Then("I click on search filter by {string}")
    public void iClickOnSearchFilterBy(String inputSearchType) {
        trialSearchPage.clickOnFilterSearchBox(inputSearchType);
    }

    @And("I enter valid input on {string} is {string}")
    public void iEnterValidInputOnIs(String searchFilterType, String searchInput) {
        trialSearchPage.enterFilterInput(searchFilterType, searchInput);
    }

    @And("I search for a trial with {string} in the field name {string}")
    public void iSearchForATrialWithInTheFieldName(String searchInput, String fieldName) {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox(fieldName, searchInput);
        trialSearchPage.clickApply();
    }

    @And("I validate the search results contains {string}")
    public void iValidateTheSearchResultsContains(String searchResultsExpected) {
        trialSearchPage.validateSearchResults(searchResultsExpected);
    }

    @And("I select the value {string} in the dropdown of {string}")
    public void iSelectTheValueInTheDropdownOf(String selectedValue, String fieldName) {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForDropdown(fieldName, selectedValue);
        trialSearchPage.clickApply();
    }

    @Then("I select orgFamily filter as {string}")
    public void iSelectOrgFamilyFilterAs(String orgInput) {
        trialSearchPage.selectOrgFamily(orgInput);
    }

    @Then("I select orgFamily value as {string}")
    public void iSelectOrgFamilyValueAs(String orgFamilyInpt) {
        trialSearchPage.inputOrgFamily(orgFamilyInpt);
    }

    @And("I Sorted Trials List By {string}")
    public void iSortedTrialsListBy(String columnName) {
        priorSubmissionPage.validateIndividualColumnSortByDsc(columnName);
    }
}
