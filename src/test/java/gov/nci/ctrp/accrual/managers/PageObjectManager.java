package gov.nci.ctrp.accrual.managers;

import gov.nci.ctrp.accrual.pages.*;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private final WebDriver driver;
    private LoginPage loginPage;
    private SearchClinicalTrialsPage searchClinicalTrialsPage;
    private MenuBar menuBar;
    private RegisterTrialPage registerTrialPage;
    private TrialSearchPage trialSearchPage;

    private BatchUploadPage batchUploadPage;

    private ProtocolAbstractorPage protocolAbstractorPage;

    private PriorSubmissionPage priorSubmissionPage;

    private AddStudySubjectPage addStudySubjectPage;

    private DiseaseTerminologyPage diseaseTerminologyPage;

    private SelectDeselectColumns selectDeselectColumns;

    private DownloadPage downloadPage;


    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public SearchClinicalTrialsPage getSearchClinicalTrialsPage() {
        return (searchClinicalTrialsPage == null) ? searchClinicalTrialsPage = new SearchClinicalTrialsPage(driver) : searchClinicalTrialsPage;
    }

    public MenuBar getMenuBar() {
        return (menuBar == null) ? menuBar = new MenuBar(driver) : menuBar;
    }

    public RegisterTrialPage getRegisterTrialPage() {
        return (registerTrialPage == null) ? registerTrialPage = new RegisterTrialPage(driver) : registerTrialPage;
    }


    public BatchUploadPage getBatchUplaodPage() {
        return (batchUploadPage == null) ? batchUploadPage = new BatchUploadPage(driver) : batchUploadPage;
    }

    public TrialSearchPage getTrialSearchPage() {
        return (trialSearchPage == null) ? trialSearchPage = new TrialSearchPage(driver) : trialSearchPage;
    }


    public ProtocolAbstractorPage getProtocolAbstractorPage() { return ( protocolAbstractorPage== null) ? protocolAbstractorPage = new ProtocolAbstractorPage(driver) : protocolAbstractorPage;}

    public PriorSubmissionPage getPriorSubmissPage() {
        return (priorSubmissionPage == null) ? priorSubmissionPage = new PriorSubmissionPage(driver) : priorSubmissionPage;
    }

    public AddStudySubjectPage getAddStudySubjectPage() {
        return (addStudySubjectPage == null) ? addStudySubjectPage = new AddStudySubjectPage(driver) : addStudySubjectPage;
    }

    public DiseaseTerminologyPage getDiseaseTerminologyPage() {
        return (diseaseTerminologyPage == null) ? diseaseTerminologyPage = new DiseaseTerminologyPage(driver) : diseaseTerminologyPage;
    }

    public SelectDeselectColumns getAccuralSelectDeselectColumnsPage() { return ( selectDeselectColumns == null) ? selectDeselectColumns = new SelectDeselectColumns(driver) : selectDeselectColumns;}

    public DownloadPage getAccuralDownloadExcelPagePage() { return ( downloadPage == null) ? downloadPage = new DownloadPage(driver) : downloadPage;}


}
