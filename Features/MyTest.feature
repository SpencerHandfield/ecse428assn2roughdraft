Feature: Test the sending of an email with an image attachment
  @Test1
  Scenario: Sending an appropriate sized image to a recipient
    Given I am logged into a gmail account
    And I click the compose button
    Then I enter a recipient email and subject
    Then I attach an image file
    Then The image will be attached
    Then I click send with the image attached

  @Test2
  Scenario: Sending multiple appropriate sized image to a recipient

    Given I am logged into a gmail account
    Then I click the “compose” button
    Then I enter a recipient email in the "To:" field
    Then I attach an image file
    Then I attach another
    Then The images will be attached
    Then I click send with the multiple images attached

  @Test3
  Scenario: Attempting to send an image attachment to no recipient

    Given I am logged into a gmail account
    And I click the “compose” button
    And I attach an image
    Then The image will be attached
    And I do not fill in the "To:" field for recipient
    Then I click send
    Then the email with attachment does not send
    And I am prompted to fill in the “To:”

  @Test4
  Scenario: Attempting to send an image attachment that is too large
    Given I am logged into a gmail account
    Then I click the “compose” button
    Then I enter a recipient email
    Then I attach an image over 25MB in size
    Then I click send
    Then the email will not send
    And I will be prompted the file is too large



