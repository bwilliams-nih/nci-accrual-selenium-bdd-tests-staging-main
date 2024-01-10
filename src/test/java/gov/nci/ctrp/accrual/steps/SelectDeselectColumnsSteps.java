package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SelectDeselectColumnsSteps extends BaseStep {

    private String studyName;

    public SelectDeselectColumnsSteps(TestContext context) {
        super(context);
    }

    @When("I deselect all columns in Study Subject page")
    public void deselectAllColumns() {
        selectDeselectColumns.deselectAllColumns();
    }

    @Then("No columns display in the Study Subject table")
    public void validateGrid() {
        selectDeselectColumns.validateGrid();
    }

    @And("I select the {string} columns")
    public void selectPerticularColumn(String columnName) {
        selectDeselectColumns.selectParticularColumn(columnName);
    }

    @And("I deselect all columns")
    public void deselctAllColumns() {
        diseaseTerminologyPage.deselctAllColumns();
    }

    @Then("the {string} column displays in the grid")
    public void columnDisplaysInGrid(String columnName) {
        selectDeselectColumns.validateParticularColumnInGrid(columnName);
    }

    @And("I select {string}")
    public void iSelect(String columnName) {
        selectDeselectColumns.selectParticularColumn(columnName);
    }
}
