package gov.nci.ctrp.accrual.steps;

import gov.nci.ctrp.accrual.utils.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Collection;
import java.util.Iterator;

public class Hooks {

    TestContext testContext;

    public Hooks(TestContext context){
        this.testContext = context;
    }

    @Before
    public void BeforeScenario(Scenario scenario) {
        testContext.getDriverManager().getDriver();
        linkJiraTicket(scenario);
    }
    @After
    public void AfterScenario(Scenario scenario) {
        takeScreenshot(scenario);
        testContext.getDriverManager().closeDriver();
    }

    public void takeScreenshot(Scenario scenario){
        TakesScreenshot ts = (TakesScreenshot) testContext.getDriverManager().getDriver();
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,"image/png", scenario.getName());
    }

    public Object getLastElement(final Collection <?> c) {
        final Iterator <?> itr = c.iterator();
        Object lastElement = itr.next();
        while(itr.hasNext()) {
            lastElement = itr.next();
        }
        return lastElement;
    }

    public void linkJiraTicket(Scenario scenario){
        String tag = getLastElement(scenario.getSourceTagNames()).toString();
        if(tag.contains("AC") || tag.contains("CTRP")) {
            tag = tag.replace("@","");
            embedLink(tag, scenario);
        }
    }

    public void embedLink(String tag, Scenario scenario){
        String jiraURL = "https://bioappdev.atlassian.net/browse/" + tag;
        //display URL link"
        String hyperLink = "<a href=\"" + jiraURL + "\">" + jiraURL + "</a>";
        //display Jira Ticket # link:
        //String hyperLink = "<a href=\"" + jiraURL + "\">" + " JIRA Ticket: " + tag + "</a>"; //
        scenario.log(hyperLink);
    }
}
