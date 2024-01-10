Feature: Login to CTRP Accrual

  @login
  Scenario: Login
    Given I am on the CTRP login page
    When I login as a "submitter"
    Then the Trial Search page displays

  @Encrypt #Only needed when bdd_test_user_password has changed and needs updated in the int.properties file
  Scenario: Encrypt Password
    Given I encrypt "Enter password to encrypt"

