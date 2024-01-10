package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

public class BatchUploadSteps extends BaseStep {

    private String studySubjectId;

    private String disease_code_and_site_code_content = "\"COLLECTIONS\",\"NCI-2020-00012\",,,,,,,,,\n" +
            "\"PATIENTS\",\"NCI-2020-00012\",\"%s\",,,,,,,\"20090506\",,\"7434\",\"8253/2;C34\",,,,,,,,,,,\n" +
            "\"PATIENT_RACES\",\"NCI-2020-00012\",\"CQ0001u\",\"Asian\"\n";


    private String contentUpdate = "\"COLLECTIONS\",\"NCI-2020-00012\",,,,,,,,,\n" +
            "\"PATIENTS\",\"NCI-2020-00012\",\"%s\",,,,,,,\"20090506\",,\"7434\",\"10025032\",,,,,,,,,,,\n" +
            "\"PATIENT_RACES\",\"NCI-2020-00012\",\"CQ0001u\",\"Asian\"\n";

    private String no_disease_code_content = "\"COLLECTIONS\",\"NCI-2020-00012\",,,,,,,,,\n" +
            "\"PATIENTS\",\"NCI-2020-00012\",\"%s\",,,,,,,\"20090506\",,\"7434\",,,,,,,,,,,,\n" +
            "\"PATIENT_RACES\",\"NCI-2020-00012\",\"CQ0001u\",\"Asian\"\n";

    private String abbreviatedTrialBatchInt ="\"COLLECTIONS\",\"NCI-2020-00012\",,,,,,,,,\n" +
            "\"PATIENTS\",\"NCI-2020-00012\",\"CQ0001u\",,,,,,,\"20090506\",,\"7434\",,,,,,,,,,,,\n" +
            "\"PATIENT_RACES\",\"NCI-2020-00012\",\"CQ0001u\",\"Asian\"\n";

    private String rejectedInt ="\"COLLECTIONS\",\"NCI-2016-01375\",,,,,,,,,\n" +
            "\"PATIENTS\",\"NCI-2016-01375\",\"AZ0001\",\"33789\",\"US\",\"200101\",\"Female\",\"Not Hispanic or Latino\",,\"20230911\",,\"297752576\",,,,,,,,,,\"V20.\",,\n" +
            "\"PATIENT_RACES\",\"NCI-2016-01375\",\"AZ0001\",\"Asian\"\n";


    public BatchUploadSteps(TestContext context) {
        super(context);
    }

    @Given("I hover to Upload Icon on the left menu bar")
    public void iHoverUploadIcon() {
        batchUploadPage.hoverUploadICon();
    }

    @And("I click Batch Upload")
    public void iClickUploadIcon() {
        batchUploadPage.clickUploadIcon();
    }

    @Then("The Accrual Batch Upload page displays")
    public void theUploadPageDisplays() {
        batchUploadPage.displays();
    }

    @When("I upload a batch file that should be accepted")
    public void iUploadTheBatchFileAccepted() {
        studySubjectId = batchUploadPage.uploadBatchFile(true);
    }

    @Then("I upload a batch file that should be rejected")
    public void uploadBatchFileThatShouldBeRejected() {
        studySubjectId = batchUploadPage.uploadBatchFile(false);
    }

    @Then("the success message displays")
    public void theSuccessMessageDisplays() {
        batchUploadPage.validateSuccessMessage();
    }

    @Then("The batch file displays with the correct data and upload status")
    public void validateUploadedBatchAccepted() {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("File/Subject", studySubjectId);
        trialSearchPage.clickApply();
        batchUploadPage.validateUploadedBatchAccepted();
    }

    @And("I validate uploaded batch file is rejected")
    public void validateUploadedBatchRejected() {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("File/Subject", studySubjectId);
        trialSearchPage.clickApply();
        batchUploadPage.validateUploadedBatchRejected();
    }

    @Then("Validate that the subject’s disease code system matches the data from the batch upload")
    public void validateStudySubjectViaBatch() {
        trialSearchPage.clickFilterOption();
        trialSearchPage.filterSearchForTextBox("Study Subject ID", studySubjectId);
        trialSearchPage.clickApply();
        addStudySubjectPage.validateStudySubjectViaBatch(studySubjectId);
    }

    @When("I create a file {string} for batch upload and upload it")
    public void iCreateAFileForBatchUploadAndUploadIt(String inputString) {
        if (inputString.equalsIgnoreCase("disease_code_and_site_code"))
            studySubjectId = batchUploadPage.createBatchUploadFile(disease_code_and_site_code_content);
        else if (inputString.equalsIgnoreCase("contentUpdate"))
            studySubjectId = batchUploadPage.createBatchUploadFile(contentUpdate);
        else if (inputString.equalsIgnoreCase("no_disease_code"))
            studySubjectId = batchUploadPage.createBatchUploadFile(no_disease_code_content);
        else if (inputString.equalsIgnoreCase("abbreviated_TrialBatch_Int"))
            studySubjectId = batchUploadPage.createBatchUploadFile(abbreviatedTrialBatchInt);
        else if (inputString.equalsIgnoreCase("rejected_Int"))
            studySubjectId = batchUploadPage.createBatchUploadFile(rejectedInt);
    }

    @When("I update existing file with {string} for batch upload and upload it")
    public void iUpdateExistingFileForBatchUploadAndUploadIt(String diseaseCode) {
        if (diseaseCode.equalsIgnoreCase("8253/2;C34"))
            batchUploadPage.updateBatchUploadFile(disease_code_and_site_code_content);
        else if (diseaseCode.equalsIgnoreCase("10025032"))
            batchUploadPage.updateBatchUploadFile(contentUpdate);
    }
}