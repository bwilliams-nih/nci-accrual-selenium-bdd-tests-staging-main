package gov.nci.ctrp.accrual.steps;

import com.github.javafaker.Faker;
import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DownloadSteps extends BaseStep {

    private String fileName;

    public DownloadSteps(TestContext context) {
        super(context);
    }

    @And("click export")
    public void clickExport() {
        downloadPage.clickExport();
    }

    @And("I validate the selection fields on the Export Trials modal")
    public void validateTheSelectionFieldsOnTheExportTrialsModal() {
        downloadPage.exportTrialsMenuOptionsDisplay();
    }

    @When("I select {string} under File Format")
    public void selectFileFormat(String string) {
        downloadPage.selectFileFormat(string);
    }

    @And("I select {string} under Column Options")
    public void selectColumnOptions(String string) {
        downloadPage.selectColumnOptions(string);
    }

    @Then("the downloaded Excel file displays all of the results from the Trial Search page")
    public void downloadedExcelDisplaysAllResults() {
        downloadPage.validateExcel(fileName, "");
    }

    @Then("the downloaded Excel file displays the {string} columns")
    public void validateExcelWithSpecificColumns(String string) {
        downloadPage.validateExcel(fileName, string);
    }

    @And("I enter a file name")
    public void enterFileName() {
        Faker fake = new Faker();
        fileName = "Auto_" + fake.name().firstName();
        downloadPage.enterFileName(fileName);
    }

    @And("I click Start Export")
    public void clickOnStartExport() {
        downloadPage.startExport();
    }

    @Then("the downloaded csv file displays all of the results from the Trial Search page")
    public void downloadedCsvDisplaysAllResults() {
        downloadPage.validateCSVHeaders(fileName, "");
    }

    @Then("the downloaded csv displays the {string} columns from the Trial Search page")
    public void validateCSVWithSpecificColumns(String string) {
        downloadPage.validateCSVHeaders(fileName, string);
    }

    @Then("the downloaded csv contains the {string} data from the Trial Search page")
    public void theDownloadedCsvContainsTheDataFromTheTrialSearchPage(String string) {
        downloadPage.validateCSVData(fileName, string);
    }

    @Then("the downloaded csv file should be deleted")
    public void theDownloadedCsvFileShouldBeDeleted() {
        downloadPage.deleteCSVFile(fileName);
    }

    @Then("the downloaded Excel file should be deleted")
    public void theDownloadedExcelFileShouldBeDeleted() {
        downloadPage.deleteExcelFile(fileName);
    }
}
