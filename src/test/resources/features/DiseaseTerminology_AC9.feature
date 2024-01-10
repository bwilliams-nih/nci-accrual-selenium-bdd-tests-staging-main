@disease_terminology @regression
Feature: As a submitter on the Trial Search page, I should get a warning when I change the
  Accrual Disease Terminology on a trial that has accrual reported

  Background: I am logged in as a bdd test user
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

  @disease_terminology_warning @defect @AC-666
  Scenario: Validate Disease Terminology warning message displays when accrual is reported
    Given I navigate to the 'Trial Search' page
    When I search for a trial with Total Accrual Reported "1" and Accrual Type "Subject"
    And Click the Accrual Disease Terminology dropdown
    When I update disease terminology for the first trial in the trial search list with total trial "1"
    Then The warning message displays

  @disease_terminology_no_warning @defect @AC-666
  Scenario: Validate Disease Terminology warning message does NOT display when no accrual is reported
    Given I navigate to the 'Trial Search' page
    And I search for a trial with Total Accrual Reported "0" and Accrual Type "Subject"
    And Click the Accrual Disease Terminology dropdown
    When I update disease terminology for the first trial in the trial search list with total trial "0"
    Then The warning message does not display