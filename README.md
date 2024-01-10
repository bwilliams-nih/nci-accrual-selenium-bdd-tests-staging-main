# nci-accrual-selenium-bdd-tests
***

# Initial Setup
***

## Clone the Project
1. **Clone** the new (empty) selenium project to your local machine
2. **Clone** the selenium template project to your local machine
3. **Copy** the contents of the selenium template project to the new empty project
   1. src, .gitignore, .travis.yml, pom.xml, README.md
4. **Rename the project**:
   1. In Intellij, right-click nci-selenium-bdd-tests-template > Refactor > Rename
      1. Rename to nci-{appname}-bdd-tests
   
      > replace {appname} with the name of the application under test 
5. **Rename the package** `src/test/java/gov/nci/ctrp/{appname}` 
>replacing {appname} with the name of the application under test
4. Update the TestRunner - src/test/java/gov/nci/ctrp/{appname}/runner/TestRunner.java
   1. Update the glue directory location:
      1. gov/nci/ctrp/{appname}/steps
      
      > replace {appname} with the name of the application under test
      Update the TestRunner - src/test/java/gov/nci/ctrp/{appname}/runner/TestRunner.java
5. Update the pom file:
   1. `<artifactId>nci-{appname}-selenium-bdd-tests</artifactId>`

      > replace {appname} with the name of the application under test

## Run the sample tests:
1. Test can be run several ways
   * command line: `mvn test`
   * IDE: run the feature / scenario directly
2. Run the sample tests both ways and validate the tests run and pass
***
## Update the Properties Files:
   1. Update the environment.properties file to the environment under test: int, or uat
   2. Update the int.properties file or uat.properties file based on the desired test environment settings
   3. The properties file can be used to set:
* browser - which browser you want to run the tests on -- See options in the DriverManager class
* windowMaximize - encsures the browser is maximized so all elements can be "seen" by the driver 
* webdriverwait - implicit wait = how long the driver will wait while trying to perform and action until it times out
* app url
* user credentials
      
## Adding a new test user and credentials to the framework: 
       
1. Add the user to the getUserCredentials method located in LoginPage.java
   1. Add a case to the getUserCredentials method
      1. The string in the case will be passed in from the feature file used to get the user credentials from the properities file
      2. NOTE: the credentials from the properties file are what are input into the login screen and login to the app NOT the string used to get the credentials
   2. Use the getProperty(String property_name) method to get the email address and password from the properties file
      1. NOTE: the string passed in here should match the key of the key-value pair in the property file
      2. ex. `getProperty("bdd_test_user_email")` will look for the "bdd_test_user_email" key in the properites file and return it's value ie: ncictrpbddtestuser@nih.gov 
2. Encrypt a new password (if using something other than the default test password)
   1. Navigate to the @Encrypt scenario in the Login.feature
   2. Enter the password you want to encrypt in the quotes of "Enter password to encrpyt"
   3. Run the @Encrypt scenario
   4. Review the test output to view the encrypted password
   5. Update user password in the properties file with the new encrypted password
   
      
## Adding a new page to the framework:
1. Add the new page with name {PageName}Page.java to `src/test/java/gov/nci/ctrp/appname/pages`
   1. Add page objects as private WebElement(s)
   2. Add public methods that use the WebElements to preforms actions on the page
      1. NOTE: Be sure to extend the new page class to the BasePage Class
      2. NOTE: Make sure the constructor of the new page class initializes the BasePage super class and WebElements with WebDriver
      ```
      ex.
      public LoginPage(WebDriver driver){
           super(driver);
           PageFactory.initElements(driver, this);
       }
      ```
2. Add a public "get" method the PageObjectManager that will return the page object
3. Add a page object for the new page to the BaseStep class
   1. NOTE: Be sure to initialize the new page object in the constructor of BaseStep.java
4. The new page object can now be used in any step class (NOTE: Step class must extend BaseStep).

## Adding a step to the framework:
1. Add the new step with name {StepName}Steps.java to `src/test/java/gov/nci/ctrp/appname/steps`
2. Add cucumber steps. 
   1. HINT: Create the steps in the Feature File first and use the "Create step definition" action in your IDE
   2. NOTE: Be sure to extend the new step class to the BaseStep Class
   3. NOTE: Make sure the constructor of the new step class initializes the BaseStep super class with TestContext
3. Use page objects and action methods to perform the step actions on the page
