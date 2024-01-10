package gov.nci.ctrp.accrual.utils;

import gov.nci.ctrp.accrual.managers.DriverManager;
import gov.nci.ctrp.accrual.managers.PageObjectManager;

public class TestContext {
    private final DriverManager driverManager;
    private final PageObjectManager pageObjectManager;

    private final ScenarioContext scenarioContext;

    public TestContext(){
        driverManager = new DriverManager();
        pageObjectManager = new PageObjectManager(driverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
