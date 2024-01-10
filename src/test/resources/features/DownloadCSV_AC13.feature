@accrual_download_csv @regression @AC-13
Feature: As a submitter, I should be able to download a .CSV file of my searched results on the Trial Search page

  Background: I am logged in as a Family Accrual Submitter
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

  @accrual_download_csv_all @AC-13
  Scenario: Export all trials in csv format
    Given I navigate to the 'Trial Search' page
    And click export
    And I validate the selection fields on the Export Trials modal
    And I select "Export data in CSV format" under File Format
    And I select "Export data from all columns" under Column Options
    And I enter a file name
    When I click Start Export
    Then the downloaded csv file displays all of the results from the Trial Search page
    Then the downloaded csv file should be deleted

  @accrual_download_csv_selected @AC-13
  Scenario:I should be able to export selected trial in csv format
    Given I navigate to the 'Trial Search' page
    And I deselect all columns
    And I select the "Accrual Disease Terminology#Total Trial Accrual" columns
    And click export
    And I validate the selection fields on the Export Trials modal
    And I select "Export data in CSV format" under File Format
    And I select "Export data from visible columns" under Column Options
    And I enter a file name
    When I click Start Export
    Then the downloaded csv displays the "Accrual Disease Terminology#Total Trial Accrual" columns from the Trial Search page
    Then the downloaded csv contains the "NCI-2023-00002#1712423#9286697#Novartis Pharmaceuticals" data from the Trial Search page
    Then the downloaded csv file should be deleted



