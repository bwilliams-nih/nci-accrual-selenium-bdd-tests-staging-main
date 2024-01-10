package gov.nci.ctrp.accrual.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuBar extends BasePage {
    @FindBy(linkText = "Register Trial")
    private WebElement Register_Trial;
    @FindBy(linkText = "Externally Peer-Reviewed")
    private WebElement Externally_Peer_Reviewed;
    @FindBy(linkText = "National")
    private WebElement National;

    public MenuBar(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public MenuBar clickRegisterTrial() {
        click(Register_Trial);
        return this;
    }
    public MenuBar clickExternallyPeerReviewed(){
        click(Externally_Peer_Reviewed);
        return this;
    }

    public MenuBar clickNational() {
        click(National);
        return this;
    }
}