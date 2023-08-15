Feature: New validation by searching in various domains

  @goguardian
  Scenario Outline: I want to search news validity on "<domain name>" site
    Given I am on the Goguardian news webpage
      And I checked Goguardian homepage is loaded and displayed
      And I fetched all the main news displayed in webpage
    Then I went to "<domain url>" site
      And searched the news validity in "<domain name>"

    Examples:
      | domain url                  | domain name |
      | https://www.google.com      | google      |
      | https://www.yahoo.com/      | yahoo       |
