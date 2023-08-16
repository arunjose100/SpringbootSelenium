package com.app.spring.springselenium.steps;

import com.app.spring.springselenium.page.Base;
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

import java.util.ArrayList;

import static org.testng.Assert.assertTrue;

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
            assertTrue(this.googlePage.isAt());
        }else{
            this.yahooPage.goTo(site);
            assertTrue(this.yahooPage.isAt());
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

    @And("I searched the news validity in {string}")
    public void newsValidityCheck(String siteName) {
        this.goguardianHome.getNewsHeaders().stream().forEach(e->{
            if(siteName.contains("google")){
                this.googlePage.getSearchComponent()
                        .clearSearch()
                        .search("\""+e+"\"");
                assertTrue(this.googlePage.getSearchResult().isAt());
                assertTrue(this.googlePage.getSearchResult().getCount() > 1);
                assertTrue(this.googlePage.getSearchResult().matchedResults.size()>= 1,
                        "Less results are displayed than expected:: "+this.googlePage.getSearchResult().matchedResults.size());
                this.googlePage.reload();
            } else{
                this.yahooPage.getSearchComponent()
                        .clearSearch()
                        .search("\""+e+"\"");
                assertTrue(this.yahooPage.getSearchResult().isAt());
                assertTrue(this.yahooPage.getSearchResult().getCount() > 1);
                assertTrue(this.yahooPage.getSearchResult().matchedResults.size()>=1,
                        "Less results displayed :: "+this.yahooPage.getSearchResult().matchedResults.size());
                this.yahooPage.reload();
            }

        });
    }

    @And("I searched the {string} news validity in {string}")
    public void newsValidityCheck(String index, String siteName) {
        String e = new ArrayList<>(this.goguardianHome.getNewsHeaders()).get(Integer.valueOf(index));
        if (siteName.contains("google")) {
            this.googlePage.getSearchComponent()
                    .clearSearch()
                    .search("\""+e+"\"");
            assertTrue(this.googlePage.getSearchResult().isAt());
            assertTrue(this.googlePage.getSearchResult().getCount() > 1);
            assertTrue(this.googlePage.getSearchResult().matchedResults.size()>= 1,
                    "Less results are displayed than expected:: "+this.googlePage.getSearchResult().matchedResults.size());

        } else {
            this.yahooPage.getSearchComponent()
                    .clearSearch()
                    .search("\""+e+"\"");
            assertTrue(this.yahooPage.getSearchResult().isAt());
            assertTrue(this.yahooPage.getSearchResult().getCount() > 1);
            assertTrue(this.yahooPage.getSearchResult().matchedResults.size()>=1,
                    "Less results displayed :: "+this.yahooPage.getSearchResult().matchedResults.size());
        }
    }

    @Then("I should see {string} search results page")
    public void clickSearch(String siteName) {
        if (siteName.contains("google")) {
            assertTrue(this.googlePage.getSearchResult().isAt());
        }else{
            assertTrue(this.yahooPage.getSearchResult().isAt());
        }
    }

    @Then("I should see at least {string} results in {string}")
    public void verifyResults(String results, String siteName) {
        int count = Integer.valueOf(results);
        if (siteName.contains("google")) {
            assertTrue(this.googlePage.getSearchResult().getCount() >= count);
            this.googlePage.reload();
        }else{
            assertTrue(this.yahooPage.getSearchResult().getCount() >= count);
            this.yahooPage.reload();
        }
    }

}
