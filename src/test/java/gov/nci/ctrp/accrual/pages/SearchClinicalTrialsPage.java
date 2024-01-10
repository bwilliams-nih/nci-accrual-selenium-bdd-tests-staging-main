package gov.nci.ctrp.accrual.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SearchClinicalTrialsPage extends BasePage {

    public SearchClinicalTrialsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void displays(){
        waitForPageToLoad("Search Clinical Trials");
        Assert.assertEquals(driver.getTitle(),"Search Clinical Trials");
    }


}

