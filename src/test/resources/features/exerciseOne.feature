Feature: Exercise 1 - all test scenarios for exercise 1 testing

  Background:
    Given user is on exercise number 1 page

  Scenario: Positive scenario - user clicks correct sequence of buttons
    When user clicks correct sequence of buttons
    And user clicks submit button to check solution
    Then information about correct answer should be displayed

  Scenario: Negative scenario - user clicks opposite buttons
    When user click opposite then expected sequence
    And user clicks submit button to check solution
    Then information about incorrect answer should be displayed

  Scenario Outline: Negative scenario - user clicks incorrect sequence of buttons (single additional click)
    When user skips <LackOfClickPlacement> click
    And user clicks submit button to check solution
    Then information about incorrect answer should be displayed

    Examples:
      | LackOfClickPlacement |
      | first                |
      | last                 |

  Scenario Outline: Negative scenario - user clicks incorrect sequence of buttons (single additional click)
    When user clicks incorrect sequence of buttons with single additional click in the <AdditionalClickPlacement>
    And user clicks submit button to check solution
    Then information about incorrect answer should be displayed

    Examples:
      | AdditionalClickPlacement |
      | beginning                |
      | end                      |

  Scenario: Negative scenario - user clicks incorrect sequence of buttons (additional click on beginning and at the end)
    When user clicks incorrect sequence of buttons with two additional clicks in the beggining and in the end
    And user clicks submit button to check solution
    Then information about incorrect answer should be displayed
