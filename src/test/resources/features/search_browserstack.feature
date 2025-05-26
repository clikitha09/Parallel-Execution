Feature: Search Functionality

  Scenario Outline: Search for a product
    Given I open the website for Browser Stack
    When I search for "<product>" for Browser Stack
    Then I should see search results for "<product>" for Browser Stack

    Examples:
      | product  |
      | Laptop   |
      | Phone    |
