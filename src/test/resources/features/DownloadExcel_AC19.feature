@accrual_download_excel @regression
Feature: As a submitter, I should be able to download a .XLSX file of my searched results on the Trial Search page

  Background: I am logged in as a Family Accrual Submitter
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

  @export_all_columns_in_excel @AC-19
  Scenario: Export all trials in Excel format
    Given I navigate to the 'Trial Search' page
    And click export
    And I validate the selection fields on the Export Trials modal
    And I select "Export data in Excel format" under File Format
    And I select "Export data from all columns" under Column Options
    And I enter a file name
    When I click Start Export
    Then the downloaded Excel file displays all of the results from the Trial Search page
    Then the downloaded Excel file should be deleted

  @export_selected_columns_in_excel @AC-19
  Scenario:I should be able to export selected column data in excel format
    Given I navigate to the 'Trial Search' page
    And I deselect all columns
    And I select the "Accrual Disease Terminology#Total Trial Accrual" columns
    And click export
    And I validate the selection fields on the Export Trials modal
    When I select "Export data in Excel format" under File Format
    And I select "Export data from visible columns" under Column Options
    And I enter a file name
    When I click Start Export
    Then the downloaded Excel file displays the "Accrual Disease Terminology#Total Trial Accrual" columns
    Then the downloaded Excel file should be deleted
