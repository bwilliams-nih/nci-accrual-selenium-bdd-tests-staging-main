@accrual_study_select_deselect @regression
Feature: I should be able to add or remove table columns on the Trial Search page, Prior Submissions page and
  Study Subjects page

  Background: I am logged in as a site accrual submitter
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

  @AC-206
  Scenario: Select/Deselect columns on the Study Subjects page
    Given I navigate to the 'Trial Search' page
    When I search for a trial with accrual type "Subject"
    And I click the TrialID link
    When I deselect all columns in Study Subject page
    Then No columns display in the Study Subject table
    And I select "Participating Site"
    Then the "Participating Site" column displays in the grid

    #TODO: Scenario: Select/Deselect columns on the Prior Submission page
    #TODO: Scenario: Select/Deselect columns on the Trial Search page


