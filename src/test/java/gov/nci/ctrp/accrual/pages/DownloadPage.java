package gov.nci.ctrp.accrual.pages;

import gov.nci.ctrp.accrual.utils.CSVUtils;
import gov.nci.ctrp.accrual.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DownloadPage extends BasePage {
    @FindBy(xpath = "//*[text()='File Name']//following-sibling::input")
    private WebElement txtFileName;

    @FindBy(xpath = "//*[normalize-space()='Start Export']")
    private WebElement btnStartExport;

    private String commonText = "//*[text()='%s']";

    private static String[] allHeaders = {"NCI Trial Identifier", "ClinicalTrials.gov ID", "Lead Org Trial Identifier", "Local Trial ID", "Lead Organization", "Official Title", "Current Trial Status", "Trial Type", "Trial Category", "Accrual Type", "Accrual Disease Terminology", "Total Trial Accrual", "Last Accrual Update Submitted"};

    private static String[] rowDataExpected = {"NCI-2023-00002","1712423","9286697","Novartis Pharmaceuticals","TEST6095349","Interventional","Complete","SUBJECT","ICD10","5","9/28/23 16:51"};

    public DownloadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void clickExport() {
        click(By.xpath(String.format(commonText, "Export")));
    }

    public void exportTrialsMenuOptionsDisplay() {
        validateElementIsDisplayed(By.xpath(String.format(commonText, "Export data in CSV format")));
        validateElementIsDisplayed(By.xpath(String.format(commonText, "Export data in Excel format")));
        validateElementIsDisplayed(By.xpath(String.format(commonText, "Export data from visible columns")));
        validateElementIsDisplayed(By.xpath(String.format(commonText, "Export data from all columns")));
    }

    public void selectFileFormat(String string) {
        click(By.xpath(String.format(commonText, string)));
    }

    public void selectColumnOptions(String string) {
        click(By.xpath(String.format(commonText, string)));
    }

    public void validateExcel(String fileName, String columns) {
        waitForTimeInMilliSecs(5000);
        List<String> actualList = ExcelUtils.getCellHeaders("downloads" + File.separator + "trials" + fileName + ".xlsx");
        List<String> expectedList = new ArrayList<>();
        if (columns.isEmpty()) {
            expectedList.addAll(Arrays.asList(allHeaders));
        } else {
            String[] cols = columns.split("#");
            expectedList.add("NCI Trial Identifier");
            expectedList.addAll(Arrays.asList(cols));
        }
        if (!actualList.equals(expectedList)) Assert.fail();
    }

    public void validateCSVHeaders(String fileName, String columns) {
        waitForTimeInMilliSecs(5000);
        List<String> actualList = CSVUtils.getCellHeaders("downloads" + File.separator + "trials" + fileName + ".csv");
        List<String> expectedList = new ArrayList<>();
        if (columns.isEmpty()) {
            expectedList.addAll(Arrays.asList(allHeaders));
        } else {
            String[] cols = columns.split("#");
            expectedList.add("NCI Trial Identifier");
            expectedList.addAll(Arrays.asList(cols));
        }
        if (actualList.size() != expectedList.size()) Assert.fail();
    }

    public void validateCSVData(String fileName, String rowData)
    {
        waitForTimeInMilliSecs(5000);
        List<String> actualList = CSVUtils.getRowData("downloads" + File.separator + "trials" + fileName +".csv");
        List<String> expectedList = new ArrayList<>();
        if (rowData.isEmpty()) {
            expectedList.add(Arrays.toString(rowDataExpected));
        } else {
            String[] rows = rowData.split("#");
            expectedList.addAll(Arrays.asList(rows));
        }
        //if (actualList.size() != expectedList.size()) Assert.fail();
    }

    public void enterFileName(String fileName) {
        input_text(txtFileName, fileName);
    }

    public void startExport() {
        waitForTimeInMilliSecs(2000);
        click(btnStartExport);
    }

    public void deleteCSVFile(String fileName)
    {
        String filePathToDelete ="downloads" + File.separator + "trials" + fileName +".csv";

        // Delete the file
        try {
            // Perform file deletion
            java.io.File fileToDelete = new java.io.File(filePathToDelete);
            if (fileToDelete.delete()) {
                System.out.println(fileName + " has been deleted.");
            } else {
                System.out.println("Failed to delete " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteExcelFile(String fileName)
    {
        String filePathToDelete ="downloads" + File.separator + "trials" + fileName +".xlsx";

        // Delete the file
        try {
            // Perform file deletion
            java.io.File fileToDelete = new java.io.File(filePathToDelete);
            if (fileToDelete.delete()) {
                System.out.println(fileName + " has been deleted.");
            } else {
                System.out.println("Failed to delete " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
