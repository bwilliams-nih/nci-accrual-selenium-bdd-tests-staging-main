@filtering_on_column_trial_search @regression
Feature: As a submitter, the user should be able to search trails with different types of search criteria

  Background: I am logged in as a BDD Test User
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

    # NCI Trial Identifier

  @filter_by_trialID @AC-416
  Scenario:  The user can search Trails with valid NCI Trial ID
    Given I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00078" in the field name "NCI Trial Identifier"
    Then the Trails list should be displayed


    # ClinicalTrials.gov Identifier

  @filter_by_clinical_trials_gov_ID @AC-416
  Scenario:  The user can search Trails with valid ClinicalTrials.gov Identifier
    Given I navigate to the 'Trial Search' page
    And I search for a trial with "1673697" in the field name "ClinicalTrials.gov Identifier"
    Then the Trails list should be displayed

    # Lead Org Trial Identifier

  @filter_by_lead_org_trialID @AC-416
  Scenario:  The user can search Trails with valid Lead Org Trial Identifier
    Given I navigate to the 'Trial Search' page
    And I search for a trial with "TD60709" in the field name "Lead Org Trial Identifier"
    Then the Trails list should be displayed

    # Local Trial ID

#  Scenario:  The user can search Trails with valid Local Trial ID
#    Given I navigate to the 'Trial Search' page
#    And I search for a trial with "123" in the field name "Local Trial ID"
    #Then the Trails list should be displayed
    #uncomment above line  when we have valid Local Trial ID in system , also change the value for it.

  # Lead Organization

  @filter_by_lead_org @AC-416
  Scenario:  The user can search Trails with valid Lead Organization
    Given I navigate to the 'Trial Search' page
    And I search for a trial with "Novartis Pharmaceuticals" in the field name "Lead Organization"
    Then the Trails list should be displayed

    # Official Title

  @filter_by_official_title @AC-416
  Scenario:  The user can search Trails with valid Official Title
    Given I navigate to the 'Trial Search' page
    And I search for a trial with "Evolution was beside him as I had occasion?" in the field name "Official Title"
    Then the Trails list should be displayed

    # Trial Status

#  Scenario:  The user can search Trails with valid Current Trial Status
#    Given I navigate to the 'Trial Search' page
#    Given I navigate to the 'Trial Search' page
#    And I select the value "Complete" in the dropdown of "Current Trial Status"
#    Then the Trails list should be displayed

    # Trial Type

  @filter_by_lead_trial_type @AC-416
  Scenario:  The user can search Trails with valid Trial Type
    Given I navigate to the 'Trial Search' page
    And I select the value "Non-interventional" in the dropdown of "Trial Type"
    Then the Trails list should be displayed

    # Trial Category

  @filter_by_lead_trial_category @AC-416
  Scenario:  The user can search Trails with valid Trial Category
    Given I navigate to the 'Trial Search' page
    And I select the value "Complete" in the dropdown of "Trial Category"
    Then the Trails list should be displayed

    # Accrual Type

  @filter_by_accrual_type @AC-416
  Scenario:  The user can search Trails with valid Accrual Type
    Given I navigate to the 'Trial Search' page
    And I select the value "Summary" in the dropdown of "Accrual Type"
    Then the Trails list should be displayed

    # Accrual Disease Terminology

  @filter_by_disease_terminology @AC-416
  Scenario:  The user can search Trails with valid Accrual Disease Terminology
    Given I navigate to the 'Trial Search' page
    And I select the value "SDC" in the dropdown of "Accrual Disease Terminology"
    Then the Trails list should be displayed

    # Total Trial Accrual

  @filter_by_total_trial_accrual @AC-416
  Scenario:  The user can search Trails with valid Total Trial Accrual
    Given I navigate to the 'Trial Search' page
    And I search for a trial with "10" in the field name "Total Trial Accrual"
    Then the Trails list should be displayed

  #TODO: For each of the above scenarios, validate the trials display that match the critria searched for
