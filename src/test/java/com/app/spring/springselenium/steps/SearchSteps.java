package com.app.spring.springselenium.steps;

import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import com.app.spring.springselenium.page.google.GooglePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@CucumberContextConfiguration
@SpringBootTest
public class SearchSteps {

    @LazyAutowired
    private GooglePage googlePage;

    @Value("${application.google}")
    private String url;

    @Given("I am on the google site")
    public void launchSite() {
        this.googlePage.goTo(url);
    }

    @When("I enter {string} as a keyword")
    public void enterKeyword(String keyword) {
        this.googlePage.getSearchComponent().search(keyword);
    }

    @Then("I should see search results page")
    public void clickSearch() {
        Assert.assertTrue(this.googlePage.getSearchResult().isAt());
    }

    @Then("I should see at least {int} results")
    public void verifyResults(int count) {
        Assert.assertTrue(this.googlePage.getSearchResult().getCount() >= count);
    }


}
