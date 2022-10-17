@Regression @UIRegression
  Feature: Verify the Form Authentication page functionality

    Scenario: Verify the login feature in Form Authentication page
      Given I am on the internet page
      And I navigate to Form Authentication page
      Then I verify login page

      Scenario: Verify error message on empty fields
      When username and password field is empty
      And I click login button
      Then I should see 'Your username is invalid!' message

    Scenario: Verify error message on invalid username
      When I type 'invalidusername' in username field
      And I type 'SuperSecretPassword!' in password field
      And I click login button
      Then I should see 'Your username is invalid!' message

    Scenario: Verify error message on invalid password
      When I type 'tomsmith' in username field
      And I type 'invalidpassword' in password field
      And I click login button
      Then I should see 'Your password is invalid!' message

    Scenario: Verify login with valid credentials
      When I type 'tomsmith' in username field
      And I type 'SuperSecretPassword!' in password field
      And I click login button
      Then I should see 'You logged into a secure area!' message
      And I should see welcome to secure area message
      And I click on logout
      Then I should see 'You logged out of the secure area!' message
      And I verify login page
      And I close browser