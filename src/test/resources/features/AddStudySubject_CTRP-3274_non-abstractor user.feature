@add_study_subject_non_abstractor @regression

Feature: As a submitter, I should be able to add study subject

  Background: I am logged in as a family accrual submitter user
    Given I am on the CTRP login page
    When I login as a "site accrual submitter"

  @add_study_subject_subject_accrual_non_abstractor
  Scenario: I can add study subject for subject accrual
    Given I navigate to the 'Trial Search' page
  #  Then I select orgFamily filter as "Family"
  #  Then I select orgFamily value as "test_fam"
    When I search for a trial with accrual type "Subject"
    And I Sorted Trials List By "Total Trial Accrual "
    Then only trials with selected Accrual type Display
    And I click the TrialID link with trial "NCI-2023-00002"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Subject"
    And I click Add Subject
    And I close the success dialog
    Then The new study subject displays with the data that was entered

  @add_study_subject_Partial_subject_accrual_non_abstractor
  Scenario: I can add study subject for partial subject accrual
    Given I navigate to the 'Trial Search' page
    When I search for a trial with accrual type "Partial Subject"
    And I Sorted Trials List By "Total Trial Accrual "
    Then only trials with selected Accrual type Display
    And I click the TrialID link with trial "NCI-2023-00003"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Partial Subject"
    And I click Save Button
    And I close the success dialog
    Then The new study subject displays with the data that was entered

  @not_add_study_subject_non_abstractor
  Scenario: I can't add study subject for summary accrual
    Given I navigate to the 'Trial Search' page
    When I search for a trial with accrual type "Summary"
    And I Sorted Trials List By "Total Trial Accrual "
    Then only trials with selected Accrual type Display
    And I click the TrialID link with trial "NCI-2021-00040"
    Then I can not add new study

  @add_study_subject_Disease_Code_System_non_abstractor
  Scenario: I can add study subject with Disease Code System
    Given I navigate to the 'Trial Search' page
    When I search for a trial with accrual type "Subject"
    And I Sorted Trials List By "Total Trial Accrual "
    And I click the TrialID link with trial "NCI-2023-00002"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Subject"
    And I click Add Subject
    And I close the success dialog
    Then The new study subject displays with the data that was entered

  @add_study_subject_with_specific_disease_code_non_abstractor
  Scenario: Add a study subject with Disease Code System ICD-O-3 to a trial with Disease Code ICD10
    Given I navigate to the 'Trial Search' page
    And I search for a trial with accrual type "Subject"
    And I search for a trial with trial type "Interventional"
    And I search for a trial with trial category "Complete"
    And I search for a trial with accrual disease terminology "ICD10"
    And I Sorted Trials List By "Total Trial Accrual "
    And I click the TrialID link with trial "NCI-2021-00045"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Subject" and disease code "8253/2" and site code "C34"
    And I click Add Subject
    And I close the success dialog
    Then The new study subject displays with the data that was entered

  @update_disease_code_on_existing_subject_non_abstractor
  Scenario: Update the disease code system on an existing subject accrual patient to be different than the trial disease code
    Given I navigate to the 'Trial Search' page
    And I search for a trial with accrual type "Subject"
    And I search for a trial with trial type "Interventional"
    And I search for a trial with trial category "Complete"
    And I search for a trial with accrual disease terminology "ICD10"
    And I Sorted Trials List By "Total Trial Accrual "
    And I click the TrialID link with trial "NCI-2021-00045"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Subject" and disease code "8253/2" and site code "C34"
    And I click Add Subject
    And I close the success dialog
    Then The new study subject displays with the data that was entered
    When I click update study subject icon
    And I update the Study Subject is modified with the correct Disease Code System "SDC" and Disease Code "10025032"
    And I click Update Study Subject
    When I click update study subject icon
    Then Validate the Study Subject is modified with the correct Disease Code System "SDC"
    And I click Cancel Button
    And I delete all subjects

  @update_disease_code_on_no_subject_trial_non_abstractor
  Scenario: Update the disease code system on a trial that has no subject level accrual
    Given I navigate to the 'Trial Search' page
    And I search for a trial with accrual type "Partial Subject"
    And I search for a trial with trial type "Interventional"
    And I search for a trial with trial category "Complete"
    And I search for a trial with accrual disease terminology "ICD10"
    And I Sorted Trials List By "Total Trial Accrual "
    And I click the TrialID link with trial "NCI-2017-02797"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Partial Subject" and disease code "8253/2" and site code "C34"
    And I click Save Button
    And I close the success dialog
    Then The new study subject displays with the data that was entered
    When I click update study subject icon
    And I update the Study Subject is modified with the correct Disease Code System "SDC" and Disease Code "10025032"
    And I click Update Study Subject
    When I click update study subject icon
    Then Validate the Study Subject is modified with the correct Disease Code System "SDC"
    And I click Cancel Button
    And I delete all subjects

  @update_disease_code_on_existing_partial_subject_non_abstractor
  Scenario: Update the disease code system on an existing partial subject accrual patient to be different than the trial disease code
    Given I navigate to the 'Trial Search' page
    And I search for a trial with accrual type "Partial Subject"
    And I search for a trial with trial type "Interventional"
    And I search for a trial with trial category "Complete"
    And I search for a trial with accrual disease terminology "ICD10"
    And I Sorted Trials List By "Total Trial Accrual "
    And I click the TrialID link with trial "NCI-2020-00041"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Partial Subject" and disease code "8253/2" and site code "C34"
    And I click Add Subject
    And I close the success dialog
    Then The new study subject displays with the data that was entered
    When I click update study subject icon
    And I update the Study Subject is modified with the correct Disease Code System "SDC" and Disease Code "10025032"
    And I click Update Study Subject
    When I click update study subject icon
    Then Validate the Study Subject is modified with the correct Disease Code System "SDC"
    And I click Cancel Button
    And I delete all subjects

  @update_disease_code_on_no_partial_subject_trial_non_abstractor
  Scenario: Update the disease code system on a trial to be different than the existing partial subject accrual patients
    Given I navigate to the 'Trial Search' page
    And I search for a trial with accrual type "Partial Subject"
    And I search for a trial with trial type "Interventional"
    And I search for a trial with trial category "Complete"
    And I search for a trial with accrual disease terminology "ICD10"
    And I Sorted Trials List By "Total Trial Accrual "
    And I click the TrialID link with trial "NCI-2020-00041"
    Then The Study Subject page displays
    When I click Add Study Subject
    And I enter valid data into the required fields for "Partial Subject" and disease code "8253/2" and site code "C34"
    And I click Save Button
    And I close the success dialog
    Then The new study subject displays with the data that was entered
    When I click update study subject icon
    And I update the Study Subject is modified with the correct Disease Code System "SDC" and Disease Code "10025032"
    And I click Update Study Subject
    When I click update study subject icon
    Then Validate the Study Subject is modified with the correct Disease Code System "SDC"
    And I click Cancel Button
    And I delete all subjects