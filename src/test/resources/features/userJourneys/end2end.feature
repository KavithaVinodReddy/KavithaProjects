Feature: end to end functionality testing

  As an Admin
  I want to perform actions
  So that employee can use the application

  Background:
    Given Admin logsIn

  @end2end
  Scenario: Admin assigns a new user role
    Given admin creates a new user with below details
      | FirstName | MiddleName | LastName | Location | UserName | Password | ConfirmPassword | Status  |
      | Danny     |            | Harrison | America  | test1    | testEmp1 | testEmp1        | Enabled |
    And admin creates a new user role "SuperVisorIT"
    When admin assigns the new user role "SuperVisorIT" to newly created user
    Then "SuperVisorIT" role should be added to the user
    When admin deletes role "SuperVisorIT"
    Then "SuperVisorIT" role should be removed from user
    When admin deletes new user "Danny Harrison"
    Then new user "Danny Harrison" should not visible in the employee list
    And new user "Danny Harrison" should not visible in the system users list


  Scenario: tetsing
    When admin deletes new user "Danny Harrison"
    Then new user "Danny Harrison" should not visible in the employee list
    And new user "Danny Harrison" should not visible in the system users list