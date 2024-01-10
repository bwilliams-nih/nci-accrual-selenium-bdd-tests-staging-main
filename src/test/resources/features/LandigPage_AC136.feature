@landing_page @regression
Feature: As a submitter, I should have the option to show the first 10, 25, 50, or 100 within trials in the
  results grid of the Trial Search page

  Background: I am logged in as a BDD Test User
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

  @pagination_default @AC-136
  Scenario: I can validate the pagination with default size
    Given I navigate to the 'Trial Search' page
    Then only the first 10 trials display
    When I click the show dropdown
    And I validate page size options
    And I select "10" from the Show dropdown
    Then The first "10" trials display

  @pagination_different_display_option @AC-136
  Scenario: Selecting a different display option displays the correct number of rows
    Given I navigate to the 'Trial Search' page
    Then only the first 10 trials display
    When I click the show dropdown
    And I select "25" from the Show dropdown
    Then The first "25" trials display
    When I click the show dropdown
    #And I select "50" from the Show dropdown
    #Then The first "50" trials display