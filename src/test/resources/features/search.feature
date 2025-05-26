Feature: Search Functionality

  Scenario Outline: Search for product on Scrum-based website
    Given I open the website
    When I search for "<product>"
    Then I should see search results for "<product>"

    Examples:
      | product  |
      | Laptop   |
      | Mobile   |