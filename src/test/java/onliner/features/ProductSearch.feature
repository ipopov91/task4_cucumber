Feature: Onliner product search
  This feature is for verifying that search results displayed
  on the Catalog Products Page meet specified search criteria

  Background:
    Given Onliner Home Page is opened
    And I click 'Каталог' on Onliner Home Page
    And select 'Электроника' on Onliner Category Page
    And move to 'Телевидение' and 'Телевизоры' on Onliner Category Page


  Scenario: Check several filters
    Given I am on Onliner Products Page
    When I check the following filters
      | MANUFACTURER    | Samsung   |
      | PRICETO         | 2000      |
      | RESOLUTION      | 1920x1080 |
      | DIAGONALFROM    | 40        |
      | DIAGONALTO      | 50        |
    Then correct search results are displayed