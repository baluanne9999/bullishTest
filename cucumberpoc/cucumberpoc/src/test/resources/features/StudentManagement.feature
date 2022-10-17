@Regression @APIRegression
Feature: Verify the Student Management api

  Scenario: Create new student and verify the response
    Given I have base uri for student management api
    And I set base path for student management 'post' call
    And I set the payload for Student Management post api with id as 223445 firstname 'Mike' lastname 'Wong' class '3 A' and nationality as 'Singapore'
    When I make a POST request
    Then I should get the status code 200
    And I verify the post response body with id as 223445

  Scenario: Update student records and verify the response
    Given I have base uri for student management api
    And I set base path for student management 'put' call
    And I set the payload for Student Management put api with id as 223445 and class '3 C'
    When I make a PUT request
    Then I should get the status code 200
    And I verify the put response body is returning id as 223445 firstname 'Mike' lastname 'Wong' class '3 C' and nationality as 'Singapore'

    # Here -1 means empty student id and for empty class we are not giving any value in examples
  Scenario Outline: Fetch student records with id as <id> and class <studentClass> and verify the response
    Given I have base uri for student management api
    And I set base path for student management "get" call
    When I make a GET request with "<id>" and "<studentClass>"
    Then I should get the status code 200
    And I verify the GET response body with "<id>" and "<studentClass>"
      Examples:
        | id     | studentClass |
        | -1     |              |
        | 223445 |              |
        | -1     |      3 A     |
        | 223445 |      3 C     |


  Scenario: Delete student records and verify the response
    Given I have base uri for student management api
    And I set base path for student management 'delete' call
    And I set the payload for Student Management delete with id as 223445
    When I make a delete request
    Then I should get the status code 200