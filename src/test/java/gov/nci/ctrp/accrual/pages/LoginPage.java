package gov.nci.ctrp.accrual.pages;

import gov.nci.ctrp.accrual.managers.FileReaderManager;
import gov.nci.ctrp.accrual.utils.Encoder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends BasePage {
    @FindBy(css = "#okta-signin-username")
    private WebElement Username;
    @FindBy(css = "#okta-signin-password")
    private WebElement Password;
    @FindBy(css = "#okta-signin-submit")
    private WebElement SignIn;
    @FindBy(css = "div.v-select__selections")
    private WebElement Please_choose_a_CTRP_site;

    @FindBy(xpath = "//*[text()='Protocol Abstraction']")
    private WebElement Protocol_Abstraction;

    @FindBy(xpath = "//*[text()='NCI CTRP Accrual']")
    private WebElement NCI_CTRP_Accrual;


    @FindBy(xpath = "//*[text()='New CTRP UI - Phase 3']")
    private WebElement New_CTRP_UI_Phase_3;


    @FindBy(xpath = "//*[text()='Accrual Modernization']")
    private WebElement Accrual_Modernization;

    @FindBy(xpath = "//*[text()='Clinical Trials Reporting Program Registration']")
    private WebElement Clinical_Trials_Reporting_Program_Registration;

    @FindBy(xpath = "//*[text()='New CTRP UI - Phase 3']")
    private WebElement CTRP_site_dropdown;

    @FindBy(xpath = "//*[text()='Accept']")
    private WebElement Accept;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goTo(String url) {
        navigateTo(url);
    }

    public void login(String user) {
        Map<String, String> userCredentials;

        userCredentials = getUserCredentials(user);

        input_text(Username, userCredentials.get("username"));
        input_text(Password, userCredentials.get("password"));
        click(SignIn);
        waitForTimeInMilliSecs(5000);
        waitVisibility(Please_choose_a_CTRP_site);
        click(Please_choose_a_CTRP_site);
        waitForTimeInMilliSecs(2000);
        click(CTRP_site_dropdown);
        waitForTimeInMilliSecs(3000);
        click(Accept);
    }


    private Map<String, String> getUserCredentials(String user) {
        Map<String, String> credentials = new HashMap<>();

        switch (user.toLowerCase()) {
            case "bdd test user" -> {
                String username = FileReaderManager.getInstance().getConfigReader().getProperty("bdd_test_user_email");
                String password = Encoder.decrypt(FileReaderManager.getInstance().getConfigReader().getProperty("bdd_test_user_password"));
                credentials.put("username", username);
                credentials.put("password", password);
                return credentials;
            }
            case "submitter" -> {
                String username = FileReaderManager.getInstance().getConfigReader().getProperty("submitter_user_email");
                String password = Encoder.decrypt(FileReaderManager.getInstance().getConfigReader().getProperty("submitter_user_password"));
                credentials.put("username", username);
                credentials.put("password", password);
                return credentials;
            }
            case "trail submitter" -> {
                String username = FileReaderManager.getInstance().getConfigReader().getProperty("trial_submitter_email");
                String password = Encoder.decrypt(FileReaderManager.getInstance().getConfigReader().getProperty("trial_submitter_password"));
                credentials.put("username", username);
                credentials.put("password", password);
                return credentials;
            }

            case "site accrual submitter" -> {
                String username = FileReaderManager.getInstance().getConfigReader().getProperty("accrual_submitter_email");
                String password = Encoder.decrypt(FileReaderManager.getInstance().getConfigReader().getProperty("accrual_submitter_password"));
                credentials.put("username", username);
                credentials.put("password", password);
                return credentials;
            }
            case "family accrual submitter" -> {
                String username = FileReaderManager.getInstance().getConfigReader().getProperty("family_submitter_email");
                String password = Encoder.decrypt(FileReaderManager.getInstance().getConfigReader().getProperty("family_submitter_password"));
                credentials.put("username", username);
                credentials.put("password", password);
                return credentials;
            }
            default -> throw new RuntimeException("Invalid user type provided: " + user);
        }

    }

}
