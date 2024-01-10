@prior_submission @regression
Feature: I should be able to search within my results on the Prior Submissions page

  Background: I am logged in as a bdd test user
    Given I am on the CTRP login page
    When I login as a "trail submitter"

  @all_columns_display @AC-37
  Scenario: All columns display
    Given I navigate to the Prior Submission page
    Then All columns display

  @filter_column @AC-37
  Scenario: Filter results by TrialID
    Given I navigate to the Prior Submission page
    When I filter the list of trials by a specific trial ID
    Then only records display that match that trial ID



