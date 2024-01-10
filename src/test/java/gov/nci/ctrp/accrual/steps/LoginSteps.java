package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.Encoder;
import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseStep {

    public LoginSteps(TestContext context) {
        super(context);
    }

    @Given("I am on the CTRP login page")
    public void iAmOnTheCTRPLoginPage() {
        loginPage.goTo(appURL);
    }

//    @When("I login to {string} as a {string}")
//    public void iLoginAsA(String user) {
//        loginPage.login(user);
//        loginPage.handleAcceptButton();
//    }


    @Then("the Search Clinical Trials page displays")
    public void theSearchClinicalTrialsPageDisplays() {
        searchClinicalTrialsPage.displays();
    }

    @Then("the Trial Search page displays")
    public void theTrialSearchPageDisplays() {
        trialSearchPage.displays();
    }


    @Given("I encrypt {string}")
    public void iEncrypt(String password) {
        String encryptedPassword = Encoder.encrypt(password);
        System.out.print("Encrypted password: " + encryptedPassword);
    }


    @When("I login as a {string}")
    public void iLoginAsA(String user) {
        loginPage.login(user);
    }
}
