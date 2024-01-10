@assigned_trials @regression
Feature: As a submitter I should see my assigned trial records on the Trial Search pages.

  Background: I am logged in as a site accrual submitter
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

  @AC-198
  Scenario: Trials that I have access to display
    Given I navigate to the 'Trial Search' page
    Then my assigned trial is displayed
    And all columns are displayed