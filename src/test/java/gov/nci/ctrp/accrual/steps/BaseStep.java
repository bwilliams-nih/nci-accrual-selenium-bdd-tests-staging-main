package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.managers.FileReaderManager;
import gov.nci.ctrp.accrual.pages.*;
import gov.nci.ctrp.accrual.utils.TestContext;

public class BaseStep {
    TestContext testContext;
    MenuBar menuBar;
    LoginPage loginPage;
    RegisterTrialPage registerTrialPage;
    SearchClinicalTrialsPage searchClinicalTrialsPage;


    BatchUploadPage batchUploadPage;

    TrialSearchPage trialSearchPage;

    ProtocolAbstractorPage protocolAbstractorPage;

    PriorSubmissionPage priorSubmissionPage;

    AddStudySubjectPage addStudySubjectPage;

    DiseaseTerminologyPage diseaseTerminologyPage;

    SelectDeselectColumns selectDeselectColumns;

    DownloadPage downloadPage;

    public BaseStep(TestContext context) {
        this.testContext = context;
        this.loginPage = testContext.getPageObjectManager().getLoginPage();
        this.searchClinicalTrialsPage = testContext.getPageObjectManager().getSearchClinicalTrialsPage();
        this.menuBar = testContext.getPageObjectManager().getMenuBar();
        this.registerTrialPage = testContext.getPageObjectManager().getRegisterTrialPage();
        this.batchUploadPage = testContext.getPageObjectManager().getBatchUplaodPage();
        this.trialSearchPage = testContext.getPageObjectManager().getTrialSearchPage();
        this.protocolAbstractorPage = testContext.getPageObjectManager().getProtocolAbstractorPage();
        this.priorSubmissionPage = testContext.getPageObjectManager().getPriorSubmissPage();
        this.addStudySubjectPage = testContext.getPageObjectManager().getAddStudySubjectPage();
        this.diseaseTerminologyPage = testContext.getPageObjectManager().getDiseaseTerminologyPage();
        this.selectDeselectColumns = testContext.getPageObjectManager().getAccuralSelectDeselectColumnsPage();
        this.downloadPage = testContext.getPageObjectManager().getAccuralDownloadExcelPagePage();
    }

    public static final String appURL = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();

    public void storeVariable(String variableName, Object obj) {
        testContext.getScenarioContext().setContext(variableName, obj);
    }

    public Object getVariable(String varName) {
        return testContext.getScenarioContext().getContext(varName);
    }

}
