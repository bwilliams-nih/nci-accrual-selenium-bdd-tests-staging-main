package gov.nci.ctrp.accrual.pages;

import com.github.javafaker.Faker;
import gov.nci.ctrp.accrual.utils.ConfigFileReader;
import gov.nci.ctrp.accrual.utils.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.*;

public class BatchUploadPage extends BasePage {

    @FindBy(xpath = "//div[@class='v-navigation-drawer__content']/div/a[@href='/batchUpload']")
    private WebElement uploadIcon;

    @FindBy(xpath = "//div[@class='v-sheet v-theme--CustomLightTheme entity-logo batchUploadHeader']//following-sibling::div/h1")
    private WebElement pageHeader;

//    @FindBy(xpath="/html/body/div[1]/div/div/div/main/div/div/div[3]/div/div[2]/div[2]/button")
//    private WebElement chooseFileBtn;

    @FindBy(id = "file-input")
    private WebElement chooseFileBtn;

    @FindBy(xpath = "//div[@class='v-alert__content'][text()='Click SUBMIT to upload the file.']")
    private WebElement alertContent;

    @FindBy(xpath = "//button/span[text()='Submit']")
    private WebElement submitBtn;

    @FindBy(xpath = "//*[text()='File Successfully Uploaded - Currently being processed. Email will be received when Processing is complete.']")
    private WebElement successMessage;

    private String submittedMethod = "//*[text()='%s']//ancestor::*[@col-id='submissionTypeDetail']//following-sibling::*[@col-id='submissionMethod']";
    private String submittedBy = "//*[text()='%s']//ancestor::*[@col-id='submissionTypeDetail']//following-sibling::*[@col-id='submittedBy']";
    private String uploadStatus = "//*[text()='%s']//ancestor::*[@col-id='submissionTypeDetail']//following-sibling::*[@col-id='uploadStatus']";
    private String rejectionReason = "//*[text()='%s']//ancestor::*[@col-id='submissionTypeDetail']//following-sibling::*[@col-id='rejectionReason']";
    private String fileName;
    private String testUser;
    private String filePath;

    public BatchUploadPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        if (new ConfigFileReader().getEnv().equalsIgnoreCase("int"))
            testUser = "Accrual Trial_Submitter";
        else
            testUser = "Test, Ncictrpbddtestuser";
    }

    public void clickUploadIcon() {
        waitForTimeInMilliSecs(5000);
        click(uploadIcon);
    }

    public void hoverUploadICon() {
        hoverTo(uploadIcon);
    }

    public void displays() {
        waitVisibility(pageHeader);
        Assert.assertEquals(pageHeader.getAttribute("innerHTML"), "Accrual Batch Upload <!---->");
    }

    public String uploadBatchFile(boolean accptedOrRejected) {
        File dir = new File("downloads");
        if (!dir.exists())
            dir.mkdir();
        if (accptedOrRejected) {
            if (new ConfigFileReader().getEnv().equalsIgnoreCase("uat")) {
                fileName = new Faker().name().firstName() + "_CompletetrialBatchUAT2.Txt";
                IOUtils.copyFile("src/test/resources/files/batchFiles/CompletetrialBatchUAT2.Txt", "downloads/" +
                        fileName);
            } else {
                fileName = new Faker().name().firstName() + "_AbbreviatedTrialBatchINT.Txt";
                IOUtils.copyFile("src/test/resources/files/batchFiles/AbbreviatedTrialBatchINT.Txt", "downloads/" +
                        fileName);
            }
        } else {
            if (new ConfigFileReader().getEnv().equalsIgnoreCase("uat")) {
                fileName = new Faker().name().firstName() + "_rejectedUAT.Txt";
                IOUtils.copyFile("src/test/resources/files/batchFiles/rejectedUAT.Txt", "downloads/" +
                        fileName);
            } else {
                fileName = new Faker().name().firstName() + "_rejectedINT.Txt";
                IOUtils.copyFile("src/test/resources/files/batchFiles/rejectedINT.Txt", "downloads/" +
                        fileName);
            }
        }
        String filePath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator + fileName;
        chooseFileBtn.sendKeys(filePath);
        click(submitBtn);
        return fileName;
    }

    public void validateSuccessMessage() {
        validateElementIsDisplayed(successMessage);
    }

    public void validateUploadedBatchAccepted() {
        waitForTimeInMilliSecs(3000);
        validateElementIsDisplayed(By.xpath(String.format(submittedMethod, fileName)));
        String user = get_text(By.xpath(String.format(submittedBy, fileName)));
        Assert.assertEquals(user, testUser);
        String status = get_text(By.xpath(String.format(uploadStatus, fileName)));
        Assert.assertEquals(status, "Accepted");
        String rejectReason = get_text(By.xpath(String.format(rejectionReason, fileName)));
        Assert.assertEquals(rejectReason, "");
    }

    public void validateUploadedBatchRejected() {
        waitForTimeInMilliSecs(3000);
        validateElementIsDisplayed(By.xpath(String.format(submittedMethod, fileName)));
        String user = get_text(By.xpath(String.format(submittedBy, fileName)));
        Assert.assertEquals(user, testUser);
        String status = get_text(By.xpath(String.format(uploadStatus, fileName)));
        Assert.assertEquals(status, "Rejected");
        String rejectReason = get_text(By.xpath(String.format(rejectionReason, fileName)));
        Assert.assertNotEquals(rejectReason, "Rejected");
    }

    public String createBatchUploadFile(String content) {
        File dir = new File("upload");
        if (!dir.exists())
            dir.mkdir();
        String name = new Faker().name().firstName().toUpperCase();
        fileName = name + "_BatchUpload.Txt";
        try {
            FileWriter fw = new FileWriter(dir + File.separator + fileName);
            fw.write(String.format(content, fileName));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePath = System.getProperty("user.dir") + File.separator + dir + File.separator + fileName;
        chooseFileBtn.sendKeys(filePath);
        waitForTimeInMilliSecs(3000);
        click(submitBtn);
        return fileName;
    }

    public void updateBatchUploadFile(String updatedContent) {
        File originalFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(originalFile));
            File tempFile = new File("tempfile.Txt");
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            pw.println(updatedContent);
            pw.flush();
            pw.close();
            br.close();
            if (!originalFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            if (!tempFile.renameTo(originalFile))
                System.out.println("Could not rename file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        chooseFileBtn.sendKeys(filePath);
        click(submitBtn);
    }
}
