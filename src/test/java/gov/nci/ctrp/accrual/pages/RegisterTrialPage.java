package gov.nci.ctrp.accrual.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegisterTrialPage extends BasePage{

    @FindBy(css = "#okta-signin-username")
    private WebElement Username;

    public RegisterTrialPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void displays(){
        Assert.assertEquals(driver.getTitle(),"Register Trial");
    }

}
