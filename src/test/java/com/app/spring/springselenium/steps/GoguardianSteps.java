package com.app.spring.springselenium.steps;

import com.app.spring.springselenium.page.goguardian.GoguardianHome;
import com.app.spring.springselenium.page.google.GooglePage;
import com.app.spring.springselenium.page.yahoo.YahooPage;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class GoguardianSteps {

    @LazyAutowired
    private GooglePage googlePage;

    @LazyAutowired
    private YahooPage yahooPage;

    @LazyAutowired
    private GoguardianHome goguardianHome;

    @Given("I am on the Goguardian news webpage")
    public void launchGoguardianSite() {
        goguardianHome.goTo();
    }

    @And("I fetched all the main news displayed in webpage")
    public void fetchAllMainNewsHeaders() {
        this.goguardianHome.captureMainNewsDetails();
    }

    @And("I checked Goguardian homepage is loaded and displayed")
    public void checkHomePageIsDisplayed() {
        Assert.assertEquals(this.goguardianHome.getTitle(), "News | The Guardian", "Page title doesn't match");
        Assert.assertTrue(this.goguardianHome.isAt());
    }

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

    @And("searched the news validity in {string}")
    public void newsValidityCheck(String siteName) {
        this.goguardianHome.getNewsHeaders().stream().forEach(e->{
            if(siteName.contains("google")){
                this.googlePage.getSearchComponent().clearSearch();
                this.googlePage.getSearchComponent().search("\""+e+"\"");
                Assert.assertTrue(this.googlePage.getSearchResult().isAt());
                Assert.assertTrue(this.googlePage.getSearchResult().getCount() > 0);
                Assert.assertTrue(this.googlePage.getSearchResult().matchedResults.size()>0,
                        "Less results displayed :: "+this.googlePage.getSearchResult().matchedResults.size());
                this.googlePage.reload();
            } else{
                this.yahooPage.getSearchComponent().clearSearch();
                this.yahooPage.getSearchComponent().search("\""+e+"\"");
                Assert.assertTrue(this.yahooPage.getSearchResult().isAt());
                Assert.assertTrue(this.yahooPage.getSearchResult().getCount() > 0);
                Assert.assertTrue(this.yahooPage.getSearchResult().matchedResults.size()>0,
                        "Less results displayed :: "+this.yahooPage.getSearchResult().matchedResults.size());
                this.yahooPage.reload();
            }

        });
    }

}
