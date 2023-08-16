Feature: News validation by a news in various domains

  @goguardian @singlenews
  Scenario Outline: I want to search a news validity on "<domain name>" site
    Given I am on the Goguardian news webpage
      And I checked Goguardian homepage is loaded and displayed
      And I fetched all the main news displayed in webpage
    Then I went to "<domain url>" site
      And I searched the "<news index>" news validity in "<domain name>"
      And I should see "<domain name>" search results page
      And I should see at least "<min expected count>" results in "<domain name>"

    Examples:
      | domain url                  | domain name | news index| min expected count |
      | https://www.google.com      | google      | 1         | 1                  |
      | https://www.google.com      | google      | 2         | 2                  |
      | https://www.google.com      | google      | 3         | 1                  |
      | https://www.yahoo.com/      | yahoo       | 2         | 1                  |
      | https://www.yahoo.com/      | yahoo       | 1         | 2                  |
      | https://www.yahoo.com/      | yahoo       | 4         | 3                  |
