Feature: Open and search google

  @test @web
  Scenario: Search for a keyword on Google
    Given the Google Homepage has been opened
    When the user search for the keyword "cucumber"
    Then the list of search result must be displayed with over 301000000 results