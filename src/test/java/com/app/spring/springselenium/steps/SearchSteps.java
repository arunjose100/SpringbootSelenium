package com.app.spring.springselenium.steps;

import com.app.spring.springselenium.page.goguardian.GoguardianHome;
import com.app.spring.springselenium.page.google.GooglePage;
import com.app.spring.springselenium.page.yahoo.YahooPage;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import io.cucumber.java.en.And;
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

    @LazyAutowired
    private YahooPage yahooPage;

    @LazyAutowired
    private GoguardianHome goguardianHome;

    @Value("${application.google}")
    private String url;

    @Then("I went to {string} site")
    public void iWentToSite(String site) {
        if(site.contains("google")){
            this.googlePage.goTo(site);
            Assert.assertTrue(this.googlePage.isAt());
        }else{
            this.yahooPage.goTo(site);
            Assert.assertTrue(this.yahooPage.isAt());
        }
    }

    @Given("I am on the google site")
    public void launchSite() {
        this.googlePage.goTo(url);
    }

    @When("I enter {string} as a keyword")
    public void enterKeyword(String keyword) {
        this.googlePage.getSearchComponent().search(keyword);
    }

    @And("searched the news validity in {string}")
    public void newsValidityCheck(String siteName) {
        this.goguardianHome.getNewsHeaders().stream().forEach(e->{
            if(siteName.contains("google")){
                this.googlePage.getSearchComponent().clearSearch();
                this.googlePage.getSearchComponent().search("\""+e+"\"");
                Assert.assertTrue(this.googlePage.getSearchResult().isAt());
                Assert.assertTrue(this.googlePage.getSearchResult().getCount() > 1);
                Assert.assertTrue(this.googlePage.getSearchResult().matchedResults.size()>=1,
                        "Less results displayed :: "+this.googlePage.getSearchResult().matchedResults.size());
                this.googlePage.reload();
            } else{
                this.yahooPage.getSearchComponent().clearSearch();
                this.yahooPage.getSearchComponent().search("\""+e+"\"");
                Assert.assertTrue(this.yahooPage.getSearchResult().isAt());
                Assert.assertTrue(this.yahooPage.getSearchResult().getCount() > 1);
                Assert.assertTrue(this.yahooPage.getSearchResult().matchedResults.size()>=1,
                        "Less results displayed :: "+this.yahooPage.getSearchResult().matchedResults.size());
                this.yahooPage.reload();
            }

        });
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
