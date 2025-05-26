Feature: Product Search API

  Scenario Outline: Search API for a product by keyword
    Given I search for a product in api using "<keyword>"
    Then I should receive a valid response of api with products related to "<keyword>"

    Examples:
      | keyword |
      | laptop  |
      | samsung  |
      | watch   |