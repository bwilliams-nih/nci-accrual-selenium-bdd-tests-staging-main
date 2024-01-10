@batch_upload @regression
Feature: As a submitter, I should be able to perform batch upload

  Background: I am logged in as a submitter
    Given I am on the CTRP login page
    When I login as a "trail submitter"

  @batch_upload_accepted
  Scenario: Upload a batch upload file that should be ‘accepted’ via the Modern Accrual UI
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "abbreviated_TrialBatch_Int" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status

  @batch_upload_rejected
  Scenario: Upload a batch upload file that should be ‘rejected’ via the Modern Accrual UI
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "rejected_Int" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then I validate uploaded batch file is rejected

  @batch_upload_disease_code
  Scenario: Upload a batch upload file that should be ‘accepted’ via the Modern Accrual UI
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "abbreviated_TrialBatch_Int" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status

  @batch_upload_with_disease_code_and_disease_terminology
  Scenario: Add a study subject with Disease Code System ICD-O-3 to a trial with Disease Code ICD10 via batch
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "disease_code_and_site_code" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload

  @batch_upload_no_disease_code
  Scenario: Add a study subject with no disease code system specified via batch upload
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "no_disease_code" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload

  @batch_upload_update_disease_code
  Scenario: Update the disease code system on an existing subject accrual patient to be different than the trial disease code
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "no_disease_code" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload
    When I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I update existing file with "8253/2;C34" for batch upload and upload it
    Then the success message displays
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload

  @batch_upload_update_disease_code_no_subject_level_accrual
  Scenario: Update the disease code system on a trial that has no subject level accrual
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "no_disease_code" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload
    When I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I update existing file with "10025032" for batch upload and upload it
    Then the success message displays
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload

  @batch_upload_update_disease_code_existing_partial_subject_level_accrual
  Scenario: Update the disease code system on an existing partial subject accrual patient to be different than the trial disease code
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "no_disease_code" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload
    When I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I update existing file with "10025032" for batch upload and upload it
    Then the success message displays
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload

  @batch_upload_update_disease_code_on_trail_to_be_different_than_existing_subject_level_accrual
  Scenario: Update the disease code system on a trial to be different than the existing subject accrual patients
    Given I hover to Upload Icon on the left menu bar
    When I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I create a file "no_disease_code" for batch upload and upload it
    Then the success message displays
    When I navigate to the Prior Submission page
    And I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    When I navigate to the Prior Submission page
    Then The batch file displays with the correct data and upload status
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload
    When I hover to Upload Icon on the left menu bar
    And I click Batch Upload
    Then The Accrual Batch Upload page displays
    When I update existing file with "10025032" for batch upload and upload it
    Then the success message displays
    When I navigate to the 'Trial Search' page
    And I search for a trial with "NCI-2020-00012" in the field name "NCI Trial ID"
    And I click the TrialID link
    Then Validate that the subject’s disease code system matches the data from the batch upload

