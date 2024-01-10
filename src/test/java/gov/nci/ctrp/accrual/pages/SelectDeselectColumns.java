package gov.nci.ctrp.accrual.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectDeselectColumns extends BasePage {

    @FindBy(xpath = "//*[text()='Show/Hide']")
    private WebElement btnShowOrHide;

    private String commonText = "//*[text()='%s']";

    private String columnHeader = "//*[normalize-space(text())='%s' and @class='customHeaderLabel']";

    private String column = "//*[@class='v-input__control']//*[text()='%s']";

    private String selectedColumn = "//*[@class='v-input__control']//*[text()='%s']//preceding-sibling::div[contains(@class,'text-blue')]";

    private static final String[] COLUMN_LIST = {"Participating Site", "Subject ID", "Subject Registration Date", "Subject DOB",
            "Subject Sex", "Subject Race (multiple)", "Subject Ethnicity", "Subject Country", "Subject Zip Code",
            "Subject Disease Code System", "Subject Disease", "Subject Disease Code", "Subject Disease Site (for ICD-O-3)",
            "Subject Disease Site Code (for·ICD-O-3)", "Date Last Updated*", "User Last Created/Update", "Action"};

    public SelectDeselectColumns(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void validateGrid() {
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Participating Site")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject ID")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Registration Date")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject DOB")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Sex")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Race (multiple)")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Ethnicity")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Country")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Zip Code")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Disease Code System")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Disease")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Disease Code")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Disease Site (for ICD-O-3)")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Subject Disease Site Code (for·ICD-O-3)")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "Date Last Updated*")));
        validateElementIsNotDisplayed(By.xpath(String.format(columnHeader, "User Last Created/Update")));
    }

    public void validateParticularColumnInGrid(String columnName) {
        String[] columns = columnName.split("#");
        for (String s : columns) {
            validateElementIsDisplayed(By.xpath(String.format(columnHeader, s)));
        }
    }

    public void selectParticularColumn(String columnName) {
        click(btnShowOrHide);
        String[] columns = columnName.split("#");
        for (String s : columns) {
            waitForTimeInMilliSecs(1000);
            click(By.xpath(String.format(column, s)));
        }
        input_keys(Keys.ESCAPE);
    }

    public void deselectAllColumns() {
        waitForTimeInMilliSecs(5000);
        click(btnShowOrHide);
        for (String string : COLUMN_LIST) {
            waitForTimeInMilliSecs(1000);
            if (isDisplayed(By.xpath(String.format(selectedColumn, string))))
                click(By.xpath(String.format(column, string)));
        }
        input_keys(Keys.ESCAPE);
    }
}
