package com.app.spring.springselenium.steps;

import com.app.spring.springselenium.page.goguardian.GoguardianHome;
import com.app.spring.springselenium.utils.annotation.LazyAutowired;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

public class GoguardianSteps {

    @LazyAutowired
    private GoguardianHome goguardianHome;

    @Value("${page.title}")
    private String pageTitle;

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
        Assert.assertEquals(this.goguardianHome.getTitle(), this.pageTitle, "Page title doesn't match");
        Assert.assertTrue(this.goguardianHome.isAt());
    }


}
